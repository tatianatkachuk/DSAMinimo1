package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/muestras", description = "Endpoint to Text Service")
@Path("/muestras")
public class Service {

    private MyMuestra mb;

    public Service(){
        this.mb = MyMuestraImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "add user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User u) {
        String idUser = u.getIdUser();
        String name = u.getName();
        String surname = u.getSurname();
        String salud = u.getSalud();
        int edad = u.getEdad();
        this.mb.addUser(idUser, name, surname, salud, edad);

        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "add lab", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/addlab")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLab(Laboratory s) {
        String idLab= s.getIdLab();
        String nombre = s.getNombre();
        this.mb.addLab(idLab,nombre);

        return Response.status(201).build();

    }

    @POST
    @ApiOperation(value = "add muestra", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "LabFullException"),
            @ApiResponse(code = 402, message = "LabNotFoundException")
    })
    @Path("/addmuestra")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMuestra(Muestra b) {
        String idMuestra = b.getIdMuestra();
        String idClinico = b.getIdClinico();
        String idUser = b.getIdUser();
        String idLab = b.getIdLab();
        int fecha = b.getFecha();

        try {
            this.mb.addMuestras(idMuestra,idClinico, idUser, idLab, fecha);
            return Response.status(201).build();
        } catch (LabFullException e) {
            e.printStackTrace();
            return Response.status(402).build();
        } catch (LabNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "get muestras of a lab sorted by orden", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Muestra.class, responseContainer = "List of Muestras sorted by orden"),
            @ApiResponse(code = 404, message = "LabFullException"),

    })
    @Path("/sortbyorden/{idLab}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response muestrasOrderbyOrden(@PathParam("idLab") String idLab) {
        try {
            LinkedList<Muestra> muestras = this.mb.muestrasOrderbyOrden("idLab");
            GenericEntity<LinkedList<Muestra>> entity = new GenericEntity<LinkedList<Muestra>>(muestras){};
            return Response.status(201).entity(entity).build();
        } catch (LabNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "get muestras", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Muestra.class, responseContainer = "Muestra"),
            @ApiResponse(code = 404, message = "UserNotFoundException"),
            @ApiResponse(code = 402, message = "LabNotFoundException")
    })
    @Path("/getmuestras/{idLab}/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getMuestras(@PathParam("idLab") String idLab, @PathParam("idUser") String idUser) {
        try {
            Muestra muestras = this.mb.getMuestras("idMuestra", "idClinico", "idUser", "idLab",2);
            return Response.status(201).entity(muestras).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (LabNotFoundException e) {
            e.printStackTrace();
            return Response.status(402).build();
        }
    }

    @GET
    @ApiOperation(value = "get muestras by user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Muestra.class, responseContainer = "List of muestras sorted by order"),
            @ApiResponse(code = 404, message = "UserNotFoundException"),

    })
    @Path("/getmuestras/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response muestrasByUser(@PathParam("idUser") String userId) {
        LinkedList<Muestra> muestras = null;
        try {
            muestras = this.mb.muestrasByUser(userId);
            GenericEntity<LinkedList<Muestra>> entity = new GenericEntity<LinkedList<Muestra>>(muestras){};
            return Response.status(201).entity(entity).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

}
