package edu.upc.eetac.dsa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;

public class Laboratory {
    //Attributes
    String idLab;
    String nombre;

    //@JsonIgnore
    //@ApiModelProperty(hidden = true)
    LinkedList<Muestra> muestras;

    //Empty constructor for json deserializer
    public Laboratory(){

    }

    //Constructor
    public Laboratory(String idLab, String nombre) {
        this.idLab = idLab;
        this.nombre = nombre;
    }


    //Getters and setters


    public String getIdLab() {
        return idLab;
    }

    public void setIdLab(String idLab) {
        this.idLab = idLab;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<Muestra> getMuestras() {
        return muestras;
    }

    public void setMuestras(LinkedList<Muestra> muestras) {
        this.muestras = muestras;
    }


    //Add a muestra
    public void addMuestras(Muestra muestras){
       this.muestras.add(muestras);
  }
    //Get the number of bikes of the station
    //@JsonIgnore
    //@ApiModelProperty(hidden = true)
    public int getNumMuestras() {
        return this.muestras.size();
    }
}
