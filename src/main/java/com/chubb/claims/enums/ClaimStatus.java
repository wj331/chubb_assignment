package com.chubb.claims.enums;

import java.util.EnumSet;
import java.util.Set;

import com.chubb.claims.exception.InvalidClaimOperationException;

public enum ClaimStatus {

    SUBMITTED(false),
    UNDER_REVIEW(false),
    WAITING_FOR_INFORMATION(false),
    APPROVED(true),
    REJECTED(true),
    SETTLED(false),
    CLOSED(false);

    private Set<ClaimStatus> allowedTransitions;
    private final boolean requiresDecisionReason;

    ClaimStatus(boolean requiresDecisionReason) {
        this.requiresDecisionReason = requiresDecisionReason;
    }

    static {
        SUBMITTED.allowedTransitions =
                EnumSet.of(UNDER_REVIEW);

        UNDER_REVIEW.allowedTransitions =
                EnumSet.of(
                        WAITING_FOR_INFORMATION,
                        APPROVED,
                        REJECTED);

        WAITING_FOR_INFORMATION.allowedTransitions =
                EnumSet.of(UNDER_REVIEW);

        APPROVED.allowedTransitions =
                EnumSet.of(SETTLED);

        REJECTED.allowedTransitions =
                EnumSet.of(CLOSED);

        SETTLED.allowedTransitions =
                EnumSet.of(CLOSED);

        CLOSED.allowedTransitions =
                EnumSet.noneOf(ClaimStatus.class);
    }

    public boolean requiresDecisionReason() {
        return requiresDecisionReason;
    }

    public void validateTransition(ClaimStatus next) {

        if (!allowedTransitions.contains(next)) {

            throw new InvalidClaimOperationException(
                    String.format(
                            "Invalid status transition from %s to %s.",
                            this,
                            next));

        }

    }
}