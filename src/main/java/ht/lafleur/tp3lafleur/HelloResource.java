package ht.lafleur.tp3lafleur;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/hello-world")
public class HelloResource {

    @GET
    @Produces("application/json")
    @Path("personnes/{nom}")
    public Response hello(@PathParam("nom") String nom) {
        // Créer un message de bienvenue
        String Message = "Bonjour, " + nom + '!';
        // return Response.ok(Message).build();

        return Response.serverError().build();
    }
}