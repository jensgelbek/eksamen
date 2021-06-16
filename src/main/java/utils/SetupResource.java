/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import utils.SetupData;
import utils.SetupTestUsers;

/**
 * REST Web Service
 *
 * @author PC
 */
@Path("setup")
public class SetupResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SetupResource
     */
    public SetupResource() {
    }

   @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/generatedata")
    public String generateData() {
       boolean isGenerated = SetupData.SetupData();
       if (!isGenerated) {
           throw new WebApplicationException("Failed generation", 500);
       }
       return "{\"msg\": \"Yay it worked\"}";
    }
    
      @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/generateusers")
    public String generateUsers() {
       boolean isGenerated = SetupTestUsers.generateUsers();
       if (!isGenerated) {
           throw new WebApplicationException("Failed generation", 500);
       }
       return "{\"msg\": \"Yay it worked\"}";
    }
}
