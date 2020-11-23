package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;

import java.util.*;

public class MyMuestraImpl implements MyMuestra {//Implementa la interfaz MyMuestra
    //We call the log4j properties file
    final static Logger log = Logger.getLogger(MyMuestraImpl.class.getName());

    //We implement the Singleton pattern
    private static MyMuestra instance;

    private int numlabs;
    private Laboratory arrayLabs[];
    private LinkedList<Muestra> muestrasLab;
    private LinkedList<Muestra> muestrasUser;
    //Initialize the hashmap(key: string; value: User) of users
    private HashMap<String, User> users;

    private void MyMuestraImpl(){
        numlabs = 0;
        arrayLabs = new Laboratory[S];
        muestrasLab = new LinkedList<>();
        muestrasUser = new LinkedList<>();
        users = new HashMap<>(); //contenedor usuarios
    }

    public static MyMuestra getInstance(){ //Initialize the instance
        if(instance==null){
            instance = new MyMuestraImpl();
        }
        return instance;
    }

    //Clear all the data structures
    public void clear(){
        instance = null;
        arrayLabs = null;
        arrayLabs = new Laboratory[S];
        muestrasLab.clear();
        muestrasUser.clear();
        users.clear();
    }

    //Get the number of users
    public int numUsers(){
        log.info("Number of users: " +this.users.size());
        return this.users.size();
    }

    //Get the number of labs
    public int numLabs(){
        log.info("Number of Stations: " +this.numlabs);
        return this.numlabs;
    }

    @Override
    public int numMuestras(String idLab) throws LabNotFoundException {
        return 0;
    }

    //Get the number of bikes in a station
    public int numBikes(String idLab) throws LabNotFoundException{
        //We get the Station that has this idStation
        Laboratory lab = null;
        int numMuestras;
        for(int i = 0; i<this.numlabs; i++) {
            if(idLab.equals(this.arrayLabs[i].idLab)){
                lab = this.arrayLabs[i];
            }
        }

        log.info("idStation of this Station: " +lab.idLab);

        if (lab != null){
            numMuestras = lab.getNumMuestras();
            log.info("Number of muestras of this station: " +numMuestras);
        }
        else{
            log.error("The lab doesn't exist");
            throw new LabNotFoundException();
        }

        return numMuestras;
    }

    //Get the bikes used by the user
    public LinkedList<Muestra> muestrasByUser(String userId) throws UserNotFoundException{
        LinkedList<Muestra> muestras;

        //We want to find the given user
        User theUser = this.users.get(userId);

        if(theUser!=null){
           log.info("User: " +theUser);
           muestras = theUser.getMuestrasused();
        }
        else{
            log.error("The user doesn't exist");
            throw new UserNotFoundException();
        }
        log.info("Bikes used by the user: " +muestras);
        return muestras;
    }

    //Get the first bike of the station
    public Muestra getMuestras(String idMuestra, String idClinico, String idUser, String idLab,int fecha) throws UserNotFoundException, LabNotFoundException{
        Laboratory lab = null;
        Muestra firstMuestra;
        //We want to know if the user exists or not
        User theUser = this.users.get(idUser);
        log.info("User: " +theUser);

        //We want to know if the station exists or not
        for(int i = 0; i<this.numlabs; i++) {
            if(idLab.equals(this.arrayLabs[i].idLab)){
                lab = this.arrayLabs[i];
            }
        }

        if(theUser != null) {
            if (lab != null) {
                firstMuestra = lab.muestras.removeFirst();
                theUser.addMuestras(firstMuestra);
            }
            else {
                log.error("The station doesn't exist");
                throw new LabNotFoundException();
            }
        }
        else{
            log.error("The user doesn't exist");
            throw new UserNotFoundException();
        }
        log.info("First muestra of the station: " +firstMuestra);
        return firstMuestra;
    }

    //Get the bikes of a station ordered by kilometers
    public LinkedList<Muestra> muestrasOrderbyOrden(String idLab) throws LabNotFoundException{
        Laboratory lab = null;
        LinkedList<Muestra> ret;
        for(int i = 0; i<this.numlabs; i++) {
            if(idLab.equals(this.arrayLabs[i].idLab)){
                lab = this.arrayLabs[i];
            }
        }

        if(lab != null) {
            //We create a copy of the list
            log.info("List before changes: " + lab.getMuestras());
            ret = lab.getMuestras();

            //We have to tell to the sort method, which criteria we want to apply
            Collections.sort(ret, new Comparator<Muestra>() {
                @Override
                public int compare(Muestra o1, Muestra o2) {
                    return (int) (o1.getFecha() - o2.getFecha());
                }
            });
        }
        else{
            log.error("The laboratory doesn't exist");
            throw new LabNotFoundException();
        }
        log.info("List after changes: " + ret);
        return ret;
    }

    //Add a new Lab
    public void addLab(String idStation, String nombre){
        this.arrayLabs[this.numlabs] = new Laboratory(idStation,nombre);
        this.numlabs++;
    }

    //Add muestra into a Lab
    @Override
    public void addMuestras(String idMuestra, String idClinico, String idUser, String idLab, int fecha) throws LabFullException, LabNotFoundException {
        Laboratory lab = null;
        Muestra muestra;
        for(int i = 0; i<this.numlabs; i++) {
            if(idLab.equals(this.arrayLabs[i].idLab)){
                lab = this.arrayLabs[i];
            }
        }

        log.info("Laboratory: " +lab);

        if(lab != null){
            LinkedList<Muestra> muestras = lab.getMuestras();
            int maxmuestras = lab.getNumMuestras();
            if(muestras.size() < maxmuestras){
                lab.addMuestras(new Muestra(idMuestra,idClinico, idUser, idLab,fecha));
            }
            else{
                log.error("The laboratory that you want to add a new bike is FULL");
                throw new LabFullException();
            }
        }
        else{
            log.error("The laboratory doesn't exist");
            throw new LabNotFoundException();
        }
    }

    //Add a new User
    public void addUser(String idUser, String name, String surname, String salud, int edad){
        this.users.put(idUser, new User(idUser, name, surname, salud, edad));
    }


}
