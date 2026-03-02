package com.example.creditriskengine.domain.rule;

import com.example.creditriskengine.domain.model.LoanApplication;

public interface RiskRule {

    RuleResult evaluate(LoanApplication loanApplication);

}
