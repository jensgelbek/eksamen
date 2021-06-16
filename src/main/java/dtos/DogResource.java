/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import com.google.gson.Gson;
import facades.DogFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rest.provider.Provider;
import static rest.provider.Provider.GSON;
import utils.EMF_Creator;


/**
 * REST Web Service
 *
 * @author PC
 */
@Path("dog")
public class DogResource extends Provider{
 Gson gson = new Gson();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final DogFacade Facade = DogFacade.getInstance(EMF);
    
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DogResource
     */
    public DogResource() {
    }
    
@Path("/add")
    @RolesAllowed("admin")
    @POST
    public Response addDog(String jsonBody) {
        DogDTO returnedDogDTO;

        DogDTO dogDTO = GSON.fromJson(jsonBody, DogDTO.class);

        returnedDogDTO = Facade.create(dogDTO);

        return Response.ok(GSON.toJson(returnedDogDTO)).build();
    }
    
    @Path("/edit")
    @RolesAllowed("admin")
    @PUT
    public Response editDog(String jsonBody) {
        DogDTO returnedDogDTO;

        
                
        DogDTO dogDTO = GSON.fromJson(jsonBody, DogDTO.class);
System.out.println(dogDTO+"----------------------");
        returnedDogDTO = Facade.edit(dogDTO);

        return Response.ok(GSON.toJson(returnedDogDTO)).build();
    }
    
    @Path("/all")
    @RolesAllowed({"user","admin"})
    @GET
    public Response getAll(){
        List<DogDTO> dogdtos=Facade.getAll();
        return Response.ok(GSON.toJson(dogdtos)).build();
        
    }
    @Path("/{id}")
    @RolesAllowed("admin")
    @DELETE
    public Response delete(@PathParam("id") int id) {
        Facade.delete(new Long(id));
        return Response.ok(GSON.toJson(id)).build();

    } 
   
    @Path("/{id}")
    @RolesAllowed("admin")
    @GET
    public Response get(@PathParam("id") int id) {
        DogDTO dogDTO=Facade.get(new Long(id));
        return Response.ok(GSON.toJson(dogDTO)).build();

    } 
}
