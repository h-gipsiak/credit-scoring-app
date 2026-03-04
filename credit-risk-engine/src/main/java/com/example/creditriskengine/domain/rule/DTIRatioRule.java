package com.example.creditriskengine.domain.rule;

import com.example.creditriskengine.domain.model.LoanApplication;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DTIRatioRule implements RiskRule {

    private final static String RULE_ID = "DTIRatioRule";
    private final BigDecimal threshold;
    private final int points;
    private final int scale;

    public DTIRatioRule(BigDecimal threshold, int points, int scale) {
        if (threshold == null) {
            throw new IllegalArgumentException("Threshold cannot be null");
        } else if (threshold.compareTo(BigDecimal.ONE) > 0 || threshold.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Threshold must be between zero and one");
        }
        this.threshold = threshold;
        this.points = points;
        if (scale <= 0) {
            throw new IllegalArgumentException("Scale must be greater than zero");
        }
        this.scale = scale;
    }

    public RuleResult evaluate(LoanApplication application) {
        BigDecimal monthlyInstalment = application.getRequestedAmount()
                .divide(new BigDecimal(application.getMonthsToPay()), scale, RoundingMode.HALF_UP);
        BigDecimal dtiRatio = monthlyInstalment.add(application.getMonthlyObligations())
                .divide(application.getMonthlyIncome(), scale, RoundingMode.HALF_UP);
        String description;
        if (dtiRatio.compareTo(threshold) > 0) {
            description = "Debt-to-income ratio exceeded threshold(".concat(dtiRatio.toString())
                    .concat(" > ").concat(threshold.toString()).concat(")");
            return new RuleResult(points, description, RULE_ID);
        }
        description = "Debt-to-income ratio lower than threshold(".concat(dtiRatio.toString())
                .concat(" < ").concat(threshold.toString()).concat(")");
        return new RuleResult(0, description, RULE_ID);
    }
}
