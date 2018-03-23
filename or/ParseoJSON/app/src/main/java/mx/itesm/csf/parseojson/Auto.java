package mx.itesm.csf.parseojson;

import java.io.Serializable;

// Estamos implementando la interfaz Serializable ya que
// pasaremos el objeto Auto de una Actividad a otra.
public class Auto implements Serializable{

    private String Marca;
    private String Auto;
    private String image;

    public String getMarca() {

        return Marca;
    }

    public void setMarca(String Marca) {

        this.Marca = Marca;
    }

    public String getAuto() {

        return Auto;
    }
    public void setAuto(String Auto) {

        this.Auto = Auto;
    }

    public String getimage() {

        return image;
    }
    public void setimage(String image) {

        this.image = image;
    }
}
