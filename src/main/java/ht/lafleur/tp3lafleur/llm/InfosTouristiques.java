package ht.lafleur.tp3lafleur.llm;

import dev.langchain4j.model.output.structured.Description;
import jakarta.json.bind.annotation.JsonbPropertyOrder;

import java.util.List;

@JsonbPropertyOrder({ "lieu", "endroitsAVisiter", "prixMoyenRepas" })
public record InfosTouristiques(
        @Description("Nom d'une ville ou d'un pays")
        String lieu,
        @Description("Endroits à visiter dans la ville ou le pays")
        List endroitsAVisiter,
        @Description("Prix moyen d'un repas avec le devise de la ville ou du pays")
        String prixMoyenRepas
) { }
