package com.codebygaurav.lovable_ai.dto.subscription;

public record PlanResponse(
        Long id,
        String name,
        Integer maxProjects,
        Integer maxTokensPerDay,
        Boolean unlimitedAi, //unlimited access to LLM maxTokenPerDay if true
        String price

) {
}
