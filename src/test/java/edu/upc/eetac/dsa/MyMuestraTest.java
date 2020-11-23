package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MyMuestraTest {

    final static Logger log = Logger.getLogger(MyMuestraTest.class.getName());

    private MyMuestra mb;

    @Before
    public void setUp() throws Exception {
        this.mb = MyMuestraImpl.getInstance();
        this.mb.addUser("user1", "Sergio", "Garcia", "A",37);
        this.mb.addUser("user2", "David", "Chaparra", "C",16);
        //he añadido dos personas en el sistema

        this.mb.addLab("Lab1","LAB Gava");
        this.mb.addLab("Lab2","LAB Barcelona");
        //He creado dos labs

        Assert.assertEquals(2, this.mb.numLabs());
        Assert.assertEquals(0, this.mb.numMuestras("Lab1"));

        try {
            this.mb.addMuestras("muestra1", "id1", "user1", "Lab1",100);
            this.mb.addMuestras("muestra2", "id2", "user2", "Lab1",103);
            this.mb.addMuestras("muestra3", "id3", "user3", "Lab1",101);
            this.mb.addMuestras("muestra4", "id4", "user4", "Lab2",102);
        }
        catch (LabNotFoundException e){
            log.error("The laboratory doesn't exist: " +e.getMessage());
        }
        Assert.assertEquals(3, this.mb.numMuestras("Lab2"));

        try {
            this.mb.addMuestras("muestra1", "id1", "user1", "Lab1",100);
            this.mb.addMuestras("muestra2", "id2", "user2", "Lab1",103);
            this.mb.addMuestras("muestra3", "id3", "user3", "Lab1",101);
            this.mb.addMuestras("muestra4", "id4", "user4", "Lab2",102);
        }
        catch (LabNotFoundException e){
            log.error("The laboratory doesn't exist: " +e.getMessage());
        }
        Assert.assertEquals(3, this.mb.numMuestras("Lab2"));
    }

    @After
    public void tearDown(){
        this.mb.clear();
    }

    @Test
    public void testAddUser() {
        this.mb.addUser("3334445ZX", "Sergio", "Garcia","A",37);
        Assert.assertEquals(2, this.mb.numUsers());
    }


    @Test
    public void testAddLab() {
        this.mb.addLab("Lab2","LAB Barcelona");
        Assert.assertEquals(1, this.mb.numLabs());
    }

    @Test
    public void testAddMuestra() throws Exception {

        try {
            this.mb.addMuestras("muestra1", "id1", "user1", "Lab1",100);
            this.mb.addMuestras("muestra2", "id2", "user2", "Lab1",103);
            this.mb.addMuestras("muestra3", "id3", "user3", "Lab1",101);
            this.mb.addMuestras("muestra4", "id4", "user4", "Lab2",102);
        }
        catch (LabNotFoundException e){
            log.error("The laboratory doesn't exist: " +e.getMessage());
        }

        Assert.assertEquals(5, this.mb.numMuestras("Lab1"));


    }

    @Test(expected = LabFullException.class)
    public void testAddMuestrasAndLabFull() throws Exception {
        this.mb.addLab("Lab1","LAB Gava" );
        Assert.assertEquals(3, this.mb.numLabs());
        Assert.assertEquals(0, this.mb.numLabs());

        this.mb.addMuestras("muestra1", "id1", "user1", "Lab1",100);
        this.mb.addMuestras("muestra2", "id2", "user2", "Lab1",103);
        this.mb.addMuestras("muestra3", "id3", "user3", "Lab1",101);
        this.mb.addMuestras("muestra4", "id4", "user4", "Lab2",102);

    }


    @Test(expected = LabNotFoundException.class)
    public void testAddMuestrasAndLabNotFound() throws Exception {
        this.mb.addMuestras("muestra1", "id1", "user1", "Lab1",100);
    }



    @Test
    public void testMuestrasByLab() throws Exception {

        try {
            List<Muestra> muestra = this.mb.muestrasOrderbyOrden("Station1");
            Assert.assertEquals("muestra1", muestra.get(0).getMuestraId());
            Assert.assertEquals(10, muestra.get(0).getFecha(),1);

            Assert.assertEquals("muestra2", muestra.get(1).getMuestraId());
            Assert.assertEquals(25, muestra.get(1).getFecha(),1);

            Assert.assertEquals("muestra3", muestra.get(2).getMuestraId());
            Assert.assertEquals(70, muestra.get(2).getFecha(),1);
        }
        catch(LabNotFoundException e){
            log.error("The laboratory doesn't exist: " +e.getMessage());
        }
    }

    @Test
    public void testGetMuestras() throws Exception {

        try {
            Muestra b1 = this.mb.getMuestras("muestra1","id2","user2","Lab1",1);
            Assert.assertEquals("muestra2", b1.getMuestraId());
            Assert.assertEquals(2, this.mb.numMuestras("Lab1"));

            Muestra b2 = this.mb.getMuestras( "muestra1","id2","user2","Lab1",1);
            Assert.assertEquals("muestra1", b2.getIdMuestra());
            Assert.assertEquals(2, this.mb.numMuestras("Lab2"));

            List<Muestra> muestras = this.mb.muestrasOrderbyOrden("user1");

            Assert.assertEquals("muestra1", muestras.get(0).getMuestraId());
            Assert.assertEquals("muestra3", muestras.get(1).getMuestraId());
        }
        catch(UserNotFoundException e){
            log.error("The user doesn't exist: " +e.getMessage());
        }
        catch(LabNotFoundException e){
            log.error("The laboratory doesn't exist: " +e.getMessage());
        }
    }


}
