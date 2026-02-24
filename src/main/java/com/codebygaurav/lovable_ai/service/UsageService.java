package com.codebygaurav.lovable_ai.service;

import com.codebygaurav.lovable_ai.dto.subscription.PlanLimitResponse;
import com.codebygaurav.lovable_ai.dto.subscription.UsageTodayResponse;
import org.jspecify.annotations.Nullable;

public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
