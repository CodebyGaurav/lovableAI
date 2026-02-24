package com.codebygaurav.lovable_ai.dto.subscription;

public record UsageTodayResponse(
        int tokenUsed,
        int tokenLimit,
        int previewsRunning,
        int previewsLimit
) {
}
