/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.user.UserDTO;

import facades.UserFacade;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import rest.provider.Provider;
import static rest.provider.Provider.EMF;

/**
 * REST Web Service
 *
 * @author peter
 */
@Path("user")
public class UserResource extends Provider {
    
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();  
    private static final UserFacade REPO =  UserFacade.getUserFacade(EMF);

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    
   @GET
   @RolesAllowed("user")
   public Response hent(){
       return Response.ok("Fantastiak").build();
   }
   
  

  @POST
     public Response create(String user) {
        UserDTO u = GSON.fromJson(user, UserDTO.class);
        UserDTO newUser = REPO.create(u);
        return Response.ok(GSON.toJson(newUser)).build();
     }
}
