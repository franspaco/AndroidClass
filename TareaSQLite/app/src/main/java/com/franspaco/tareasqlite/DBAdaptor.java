package com.franspaco.tareasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//
public class DBAdaptor {

    static final String KEY_ID = "id";
    static final String KEY_NAME = "name";
    static final String KEY_LAST_NAME = "last_name";
    static final String KEY_EMAIL = "email";
    static final String KEY_SEX = "sex";
    static final String KEY_ADDRESS = "address";
    static final String TAG = "DBAdaptor";

    static final String DB_NAME = "CRM";
    static final String DB_TABLE = "clients";
    static final int DB_VERSION = 2;

    static final String CREAR_BD =
            "CREATE TABLE " + DB_TABLE +
            "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                KEY_NAME + " VARCHAR(50) NOT NULL," +
                KEY_LAST_NAME + " VARCHAR(50) NOT NULL," +
                KEY_EMAIL + " VARCHAR(60) NOT NULL," +
                KEY_SEX + " INT," +
                    KEY_ADDRESS + " VARCHAR(400)" +
            ");" +
            "CREATE UNIQUE INDEX " + DB_TABLE + "_id_uindex ON table_name (" + KEY_ID + ");";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdaptor(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
        Log.v("DEBUG", "CONSTRUCTOR DONE");
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            Log.v("DEBUG", "DBHELPER onCreate");
            try {
                Log.v("SQLite", "Creating table!");
                db.execSQL(CREAR_BD);
            } catch (SQLException e) {
                Log.e("SQLite", e.toString());
                //e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.v("DEBUG", "DBHLPER onUpgrade");
            Log.w(TAG, "Actualizando la version de la Base de Datos de " + oldVersion + " a "
                    + newVersion + ", este proceso eliminará los regustros de la versión anterior");
            db.execSQL("DROP TABLE IF EXISTS clientes");
            onCreate(db);
        }
    }

    //--- Abrimos la BD ---
    public DBAdaptor open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //--- Cerramos la BD ---
    public void close()
    {
        DBHelper.close();
    }

    //--- Insertamos registros a la tabla clientes ---
    public long insertClient(String name, String lastName, String email, int sex, String address) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_LAST_NAME, lastName);
        initialValues.put(KEY_EMAIL, email);
        initialValues.put(KEY_SEX, sex);
        initialValues.put(KEY_ADDRESS, address);
        return db.insert(DB_TABLE, null, initialValues);
    }

    //--- Borra un cliente en particular ---
    public boolean borraCliente(long idFila) {
        return db.delete(DB_TABLE, KEY_ID + "=" + idFila, null) > 0;
    }

    //--- Recuperamos todos los registros de la tabla ---
    public Cursor obtenTodosLosClientes()
    {
        return db.query(DB_TABLE, 
                new String[] {KEY_NAME, KEY_LAST_NAME,
                KEY_EMAIL, KEY_SEX, KEY_ADDRESS}, 
                null, null, null, null, null);
    }

    /**
     * FIND BY NAME
     * @param input
     * @return
     * @throws SQLException
     */
    public Cursor clientByName(String input) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DB_TABLE, 
                        new String[] {KEY_NAME, KEY_LAST_NAME,
                                KEY_EMAIL, KEY_SEX, KEY_ADDRESS}, 
                        KEY_NAME + "= '" + input + "'", null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    /**
     * FIND BY LAST NAME
     * @param input
     * @return
     * @throws SQLException
     */
    public Cursor clientByLastName(String input) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DB_TABLE,
                        new String[] {KEY_NAME, KEY_LAST_NAME,
                                KEY_EMAIL, KEY_SEX, KEY_ADDRESS},
                        KEY_LAST_NAME + "= '" + input + "'", null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //--- Actualizamos un registro  ---
    public boolean actualizaCliente(long idRow, String name, String lastName, String email, int sex, String address)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_LAST_NAME, lastName);
        args.put(KEY_EMAIL, email);
        args.put(KEY_SEX, sex);
        args.put(KEY_ADDRESS, address);
        return db.update(DB_TABLE, args, KEY_ID + "=" + idRow, null) > 0;
    }

}
