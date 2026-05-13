package ht.lafleur.tp3lafleur;

import ht.lafleur.tp3lafleur.llm.InfosTouristiques;
import ht.lafleur.tp3lafleur.llm.LlmClientPourClaude;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/guide")
public class GuideTouristiqueResource {

    // Injecte le client LLM pour Claude
    @Inject
    private LlmClientPourClaude clientPourClaude;

    @GET
    @Produces("application/json")
    @Path("lieu/{ville_ou_pays}")
    public Response getLieu(@PathParam("ville_ou_pays") String villeOuPays, @QueryParam("nb") @DefaultValue("2") int nb) {
        InfosTouristiques response = clientPourClaude.envoyerRequete(villeOuPays, nb);
        return Response.ok(response).build();
    }
}
