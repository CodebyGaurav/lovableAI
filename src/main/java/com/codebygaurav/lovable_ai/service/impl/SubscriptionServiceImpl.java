package com.codebygaurav.lovable_ai.service.impl;

import com.codebygaurav.lovable_ai.dto.subscription.CheckoutRequest;
import com.codebygaurav.lovable_ai.dto.subscription.CheckoutResponse;
import com.codebygaurav.lovable_ai.dto.subscription.PortalResponse;
import com.codebygaurav.lovable_ai.dto.subscription.SubscriptonResponse;
import com.codebygaurav.lovable_ai.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptonResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
