package edu.upc.eetac.dsa;

import java.util.LinkedList;
import java.util.List;

public interface MyMuestra {

    /**
     * number of laboratories
     */
    public static final int S = 10; //integer that is constant for all instances of a certain class at all times.

    /**
     * Add a new User
     *
     * idUser: identifier of the user
     * name: name of the user
     * surname: surname of the user
     * salud
     * edad
     */
    public void addUser(String idUser, String name, String surname, String salud ,int edad);


    /**
     * Add a new Laboratory
     *
     * idStation: identifier of the station
     * nombre
     */
    public void addLab(String idLab, String nombre);


    /**
     * Add a new muestra into a Lab
     *
     * idMuestra: identifier of the muestra
     * description: description
     * kms: kilometers
     * idStation: identifier of the station
     * StationFullException:  if the station is full
     * StationNotFoundException: if the station doesn't exist
     */
    public void addMuestras(String idMuestra, String idClinico, String idUser, String idLab,int fecha) throws LabFullException, LabNotFoundException;


    /**
     * Get the bikes of a station ordered by kilometers
     *
     * idStation: identifier
     * returns the list of bikes
     * StationNotFoundException: if the station doesn't exist
     */
     //public LinkedList<Muestra> bikesByStationOrderByKms(String idStation) throws StationNotFoundException;

    /**
     * get the first Bike of the station
     *
     * stationId: identifier of the station
     * userId: identifier of the user
     * returns the first bike of the station
     * UserNotFoundException: if the user doesn't exist
     * StationNotFoundException: if the station doesn't exist
     */
    public Muestra getMuestras(String idMuestra, String idClinico, String idUser, String idLab,int fecha) throws UserNotFoundException, LabNotFoundException;

    /**
     * get the bikes used by the user
     *
     * userId: identifier of the user
     * returns the list of bikes used by the user
     * UserNotFoundException: if the user doesn't exist
     */
     public LinkedList<Muestra> muestrasByUser(String userId) throws UserNotFoundException;

     public LinkedList<Muestra> muestrasOrderbyOrden(String idLab) throws LabNotFoundException;

    /**
     * get the number of users
     *
     * returns the number of users
     */
    public int numUsers();

    /**
     * get the number of stations
     *
     * returns the number of stations
     */
    public int numLabs();

    /**
     * get the number of bikes in a station
     *
     * idStation: identifier of the station
     * returns the number of bikes of the station
     * StationNotFoundException: if the station doesn't exist
     */
    public int numMuestras(String idLab) throws LabNotFoundException;

    /**
     * clear all the data structures
     */
    public void clear();
}
