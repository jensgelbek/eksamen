/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import dtos.DogDTO;
import dtos.OwnerDTO;
import facades.DogFacade;
import facades.OwnerFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
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
@Path("owner")
public class OwnerResource extends Provider{
Gson gson = new Gson();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final OwnerFacade Facade = OwnerFacade.getInstance(EMF);
    
    
    @Context
    private UriInfo context;

    
    
    /**
     * Creates a new instance of OwnerResource
     */
    public OwnerResource() {
    }

    @Path("/all")
    //@RolesAllowed({"user","admin"})
    @GET
    public Response getAll(){
        List<OwnerDTO> ownerdtos=Facade.getAll();
        return Response.ok(GSON.toJson(ownerdtos)).build();
        
    }
    
    
}
