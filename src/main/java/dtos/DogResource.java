/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import com.google.gson.Gson;
import facades.DogFacade;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rest.provider.Provider;
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
   // @RolesAllowed("admin")
    @POST
    public Response addDog(String jsonBody){
        DogDTO dogDTO=GSON.fromJson(jsonBody,DogDTO.class);
       
        DogDTO returnedDogDTO=Facade.create(dogDTO);
        
    return Response.ok(GSON.toJson(returnedDogDTO)).build();
    }
    
}


/*
 @Path("/add")
    @RolesAllowed("admin")
    @POST
    public Response addBook(String jsonBody) {
        BookDTO bookDTO = GSON.fromJson(jsonBody, BookDTO.class);
        System.out.println("before"+bookDTO);
        BookDTO returnedBookDTO = Facade.create(bookDTO);
        System.out.println("Returned"+returnedBookDTO); 
        return Response.ok(GSON.toJson(returnedBookDTO)).build();
    }
    
*/