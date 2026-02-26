package com.codebygaurav.lovable_ai.service.impl;

import com.codebygaurav.lovable_ai.dto.subscription.PlanLimitResponse;
import com.codebygaurav.lovable_ai.dto.subscription.UsageTodayResponse;
import com.codebygaurav.lovable_ai.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsageOfUser(Long userId) {
        return null;
    }

    @Override
    public PlanLimitResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
