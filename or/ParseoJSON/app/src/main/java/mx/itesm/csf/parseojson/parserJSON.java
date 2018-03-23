package mx.itesm.csf.parseojson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class parserJSON {

    public static ArrayList<Auto> Autos = new ArrayList<>();

    // paseaObjeto toma in objeto JSON como un parámetro y establece
    // todos los atributos del objeto Auto.
    public static Auto paseaObjeto(JSONObject obj) {

        try {
            Auto auto = new Auto();

            auto.setMarca(obj.getString("Marca"));
            auto.setAuto(obj.getString("Auto"));
            auto.setimage(obj.getString("image"));
            return auto;

        } catch (JSONException e1) {
            e1.printStackTrace();
            return null;
        }
    }


    // parseaArreglo toma un objetoArray como parametro y regersa un
    // ArrayList de Auto
    public static ArrayList<Auto> parseaArreglo(JSONArray arr) {

        JSONObject obj=null;
        Auto auto = null;
        Autos.clear();

        try {
            for(int i = 0;i<arr.length();i++) {

                obj = arr.getJSONObject(i);
                auto = new Auto();

                auto.setMarca(obj.getString("Marca"));
                auto.setAuto(obj.getString("Auto"));
                auto.setimage(obj.getString("image"));

                Autos.add(auto);
            }
            return Autos;

        } catch (JSONException e1) {
            e1.printStackTrace();
            return null;
        }
    }
}