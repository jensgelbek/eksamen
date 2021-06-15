package rest;

import dtos.RenameMeDTO;
import entities.renameme.RenameMe;
import entities.renameme.RenameMeRepository;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Response;
import rest.provider.Provider;
import facades.FacadeExample;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

//Todo Remove or change relevant parts before ACTUAL use
@Path("xxx")
public class RenameMeResource extends Provider {

    private static final RenameMeRepository REPO =  FacadeExample.getFacadeExample(EMF);

    @Path("/{id}")
    @GET
    public Response getById(@PathParam("id") int id) {
        RenameMeDTO renameMeDTO = REPO.getById(id);
        return Response.ok(GSON.toJson(renameMeDTO)).build();
    }

    @GET
    public Response getAll() {
        List<RenameMeDTO> renameMeDTOS = REPO.getAll();
        return Response.ok(GSON.toJson(renameMeDTOS)).build();
    }

    @POST
    public Response create(String jsonBody) {
        RenameMeDTO renameMeDTO = GSON.fromJson(jsonBody, RenameMeDTO.class);
        RenameMeDTO createdRenameMe = REPO.createRenameMe(renameMeDTO);
        return Response.ok(createdRenameMe).build();
    }


    @RolesAllowed("admin")
    public Response update(int id, String jsonBody) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }


    @RolesAllowed("admin")
    public Response delete(int id) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}
