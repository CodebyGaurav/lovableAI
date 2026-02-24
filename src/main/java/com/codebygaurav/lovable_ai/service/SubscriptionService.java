package com.codebygaurav.lovable_ai.service;

import com.codebygaurav.lovable_ai.dto.subscription.CheckoutRequest;
import com.codebygaurav.lovable_ai.dto.subscription.CheckoutResponse;
import com.codebygaurav.lovable_ai.dto.subscription.PortalResponse;
import com.codebygaurav.lovable_ai.dto.subscription.SubscriptonResponse;
import org.jspecify.annotations.Nullable;

public interface SubscriptionService {
    SubscriptonResponse getCurrentSubscription(Long userId);

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
