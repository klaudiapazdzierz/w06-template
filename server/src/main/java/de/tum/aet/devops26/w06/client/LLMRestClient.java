package de.tum.aet.devops26.w06.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import de.tum.aet.devops26.w06.dto.RecommendRequest;
import de.tum.aet.devops26.w06.dto.RecommendResponse;

import java.util.List;

@Component
public class LLMRestClient {

    private final RestClient restClient;

    public LLMRestClient(RestClient.Builder builder, @Value("${llm.service.url:http://localhost:5001}") String llmServiceUrl) {
        this.restClient = builder
                .baseUrl(llmServiceUrl)
                .build();
    }

    /**
     * Get a recommendation from the LLM via the REST service.
     * @param favoriteMenu list of user's favorite meal names
     * @param todaysMenu list of today's available meal names
     * @return recommendation string
     */
    public String getRecommendationFromLLM(List<String> favoriteMenu, List<String> todaysMenu) {
        try {
            // TODO Create request body

            // TODO Make REST call using this.restClient

            // TODO Extract the recommendation
            return "";

        } catch (Exception e) {
            System.err.println("Error calling LLM REST service: " + e.getMessage());
            return "";
        }
    }
}
