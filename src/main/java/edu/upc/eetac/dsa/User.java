package edu.upc.eetac.dsa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class User {
    //Attributes
    String idUser;
    String name;
    String surname;
    String salud;
    int edad;

    //@JsonIgnore
    //@ApiModelProperty(hidden = true)
    LinkedList<Muestra> muestrasused;

    //Empty constructor for json deserializer
    public User(){

    }

    //Constructor
    public User(String idUser, String name, String surname,String salud,int edad) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.salud = salud;
        this.edad = edad;

        this.muestrasused = new LinkedList();
    }


    //Getters and setters
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void addMuestras(Muestra muestra){
        muestrasused.add(muestra);
    }

    public LinkedList<Muestra> getMuestrasused() {
        return muestrasused;
    }

    public void setMuestrasused(LinkedList<Muestra> muestrasused) {
        this.muestrasused = muestrasused;
    }

    public void setSalud(String salud) {
        this.salud = salud;
    }

    public String getSalud() {return salud; }

    public int getEdad() {     return edad;    }

    public void setEdad(int edad) {        this.edad = edad;    }
}
