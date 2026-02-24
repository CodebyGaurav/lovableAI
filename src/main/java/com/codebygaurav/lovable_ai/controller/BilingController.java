package com.codebygaurav.lovable_ai.controller;


import com.codebygaurav.lovable_ai.dto.subscription.*;
import com.codebygaurav.lovable_ai.entity.Plan;
import com.codebygaurav.lovable_ai.service.PlanService;
import com.codebygaurav.lovable_ai.service.SubscriptionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BilingController {

    private final PlanService planService;
    private final SubscriptionService subscriptionService;

    @GetMapping("/plans")
    public ResponseEntity<List<PlanResponse>> getAllPlans(){
        return  ResponseEntity.ok(planService.getAllActivePlan());
    }

    @GetMapping("/me/subscription")
    public ResponseEntity<SubscriptonResponse> getMySubscription(){
        Long userId=1L;
        return  ResponseEntity.ok(subscriptionService.getCurrentSubscription(userId));
    }

    @PostMapping("/stripe/checkout")
    public ResponseEntity<CheckoutResponse> createCheckoutResponse(
            @RequestBody CheckoutRequest request
    ){
        Long userId =1L;
        return ResponseEntity.ok(subscriptionService.createCheckoutSessionUrl(request,userId));
    }

    @PostMapping("/stripe/portal")
    public ResponseEntity<PortalResponse> openCustomerPortal(){
        Long userId=1L;
        return  ResponseEntity.ok(subscriptionService.openCustomerPortal(userId));
    }

}
