package com.example.creditriskengine.domain.rule;

public class RuleResult {

    private final int points;
    private final String description;
    private final String ruleId;

    public RuleResult(int points,
                      String description,
                      String ruleId) {
        this.points = points;
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        this.description = description;
        if (ruleId == null) {
            throw new IllegalArgumentException("Rule ID cannot be null");
        }
        this.ruleId = ruleId;
    }

    public int getPoints() {
        return points;
    }

    public String getDescription() {
        return description;
    }

    public String getRuleId() {
        return ruleId;
    }
}
