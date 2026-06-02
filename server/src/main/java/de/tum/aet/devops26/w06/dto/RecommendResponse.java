package de.tum.aet.devops26.w06.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RecommendResponse(
    @JsonProperty("recommendation") String recommendation
) {}
