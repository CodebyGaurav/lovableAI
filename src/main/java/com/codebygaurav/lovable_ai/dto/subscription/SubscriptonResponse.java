package com.codebygaurav.lovable_ai.dto.subscription;

import java.time.Instant;

public record SubscriptonResponse(
         PlanResponse plan,
         String status,
         Instant periodEnd,
         Long tokensUserThisCycle
) {
}
