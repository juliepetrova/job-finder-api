package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/users")
public class UserResources {

    @Context
    private UriInfo uriInfo;


    @GET
    @Path("/welcome")
    @Produces(MediaType.TEXT_HTML)
    public Response GetWelcomeMessage(){
        String msg = "<h1><i> Hello to the newly made website </i> </h1>";
        return Response.ok(msg).build();
    }
}
