package com.codebygaurav.lovable_ai.service.impl;

import com.codebygaurav.lovable_ai.dto.subscription.PlanResponse;
import com.codebygaurav.lovable_ai.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlan() {
        return List.of();
    }
}
