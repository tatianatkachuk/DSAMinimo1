package edu.upc.eetac.dsa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

public class Muestra {
    //Attributes
    //@JsonIgnore
    //@ApiModelProperty(hidden = true)
    private int id;

    String idMuestra;
    String idClinico;
    String idUser;
    String idLab;
    int fecha;//La fecha será el orden de llegada

    //Empty constructor for json deserializer
    public Muestra(){

    }

    //Constructor
    public Muestra(String idMuestra,String idClinico,String idUser,String idLab,int fecha) {
        this.idMuestra = idMuestra;
        this.idClinico = idClinico;
        this.idUser = idUser;
        this.idLab = idLab;
        this.fecha = fecha;
        }

    //Getters and setters


    public String getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(String idMuestra) {
        this.idMuestra = idMuestra;
    }

    public String getIdClinico() {
        return idClinico;
    }

    public void setIdClinico(String idClinico) {
        this.idClinico = idClinico;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdLab() {
        return idLab;
    }

    public void setIdLab(String idLab) {
        this.idLab = idLab;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    //@JsonIgnore
    //@ApiModelProperty(hidden = true)
    public String getMuestraId() {
        return this.idMuestra;
    }
}
