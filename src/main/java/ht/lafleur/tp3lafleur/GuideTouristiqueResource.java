package ht.lafleur.tp3lafleur;

import dev.langchain4j.service.Result;
import ht.lafleur.tp3lafleur.llm.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/guide")
public class GuideTouristiqueResource {

    // Injecte le client LLM pour Claude
    @Inject
    private LlmClientPourClaude clientPourClaude;

    // Injecte le client LLM pour Gemini
    @Inject
    private LlmClientPourGemini clientPourGemini;

    @GET
    @Produces("application/json")
    @Path("lieu/{ville_ou_pays}")
    public Response getLieu(@PathParam("ville_ou_pays") String villeOuPays, @QueryParam("nb") @DefaultValue("2") int nb) {
        Result<InfosTouristiques> result = clientPourClaude.envoyerRequete(villeOuPays, nb);
//        Result<InfosTouristiques> result = clientPourGemini.envoyerRequete(villeOuPays, nb);

        InfosTouristiquesAvecUsage reponse = new InfosTouristiquesAvecUsage(
                result.content(),
                TokenUsageDto.from(result.tokenUsage())
        );

        Response.ResponseBuilder responseBuilder = Response.ok(reponse);
        responseBuilder.header("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        responseBuilder.header("Pragma", "no-cache");
        responseBuilder.header("Expires", "0");
        responseBuilder.header("Access-Control-Allow-Origin", "*");
        return responseBuilder.build();
    }
}
