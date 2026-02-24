package com.codebygaurav.lovable_ai.service;

import com.codebygaurav.lovable_ai.dto.subscription.PlanResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface PlanService {
    List<PlanResponse> getAllActivePlan();
}
