package com.franspaco.tareacomponentes;

import android.os.Parcel;
import android.os.Parcelable;

public class Responses implements Parcelable{
    String name = "";
    String email = "";
    String phone = "";
    byte p1 = 0;  // Malo: 3 | Bueno: <= 1
    byte p2 = 0;  // Malo: 3 | Bueno: <= 1
    String p3 = "";
    byte p4 = 0;  // Malo: 4 | Bueno: <= 1
    byte p5 = 0;  // Malo: 0 | Bueno: 1
    byte p6 = 0;  // Malo: <= 2 | Bueno: >=4
    byte p7 = 0;  // Malo: <= 2 | Bueno: >=4
    byte p8 = 0;  // Malo: 0 | Bueno: 1
    String p9 = "";

    Responses(){}

    protected Responses(Parcel in) {
        name = in.readString();
        email = in.readString();
        phone = in.readString();
        p1 = in.readByte();
        p2 = in.readByte();
        p3 = in.readString();
        p4 = in.readByte();
        p5 = in.readByte();
        p6 = in.readByte();
        p7 = in.readByte();
        p8 = in.readByte();
        p9 = in.readString();
    }

    public static final Creator<Responses> CREATOR = new Creator<Responses>() {
        @Override
        public Responses createFromParcel(Parcel in) {
            return new Responses(in);
        }

        @Override
        public Responses[] newArray(int size) {
            return new Responses[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeByte(p1);
        dest.writeByte(p2);
        dest.writeString(p3);
        dest.writeByte(p4);
        dest.writeByte(p5);
        dest.writeByte(p6);
        dest.writeByte(p7);
        dest.writeByte(p8);
        dest.writeString(p9);
    }
}
