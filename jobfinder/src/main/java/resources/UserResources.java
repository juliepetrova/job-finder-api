package resources;

import Controller.UserController;
import model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;
import java.util.List;

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

    @GET //GET at http://localhost:XXXX/students?
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users;
        UserController controller = new UserController();


            users = controller.getAllUseres();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {  };
        return Response.ok(entity).build();
    }
}
