package resources;

import Controller.UserController;
import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/users")
public class UserResources {

    @Context
    private UriInfo uriInfo;
    private UserController controller = new UserController();


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
                    users = controller.getAllUseres();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {  };
        return Response.ok(entity).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int stNr) throws SQLException {

        User user = controller.getUserById(stNr);
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid user id.").build();
        } else {
            return Response.ok(user).build();
        }
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUserId(@PathParam("email") String email) throws SQLException {

        int id = controller.getUserIdByEmail(email);
        if (id <= -1) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid user email.").build();
        } else {
            return Response.ok(id).build();
        }
    }

}
