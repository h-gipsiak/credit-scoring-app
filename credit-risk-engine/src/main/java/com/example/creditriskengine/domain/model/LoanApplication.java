package com.example.creditriskengine.domain.model;

import java.math.BigDecimal;

public class LoanApplication {

    private final int age;
    private final BigDecimal monthlyIncome;
    private final EmploymentType employmentType;
    private final int creditHistoryYears;
    private final int existingLoansCount;
    private final int latePaymentsLastYear;
    private final BigDecimal requestedAmount;

    public LoanApplication(int age,
                           BigDecimal monthlyIncome,
                           EmploymentType employmentType,
                           int creditHistoryYears,
                           int existingLoansCount,
                           int latePaymentsLastYear,
                           BigDecimal requestedAmount) {
        if (!this.isAdult()) {
            throw new IllegalArgumentException("Age must be at least 18");
        }
        this.age = age;
        if (monthlyIncome == null) {
            throw new IllegalArgumentException("MonthlyIncome must not be null");
        } else if (monthlyIncome.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("MonthlyIncome must be greater than zero");
        }
        this.monthlyIncome = monthlyIncome;
        if (employmentType == null) {
            throw new IllegalArgumentException("EmploymentType must not be null");
        }
        this.employmentType = employmentType;
        if (creditHistoryYears < 0) {
            throw new IllegalArgumentException("CreditHistoryYears must not be negative");
        } else if (creditHistoryYears > age - 18) {
            throw new IllegalArgumentException("CreditHistoryYears must not be greater than age");
        }
        this.creditHistoryYears = creditHistoryYears;
        if (existingLoansCount < 0) {
            throw new IllegalArgumentException("ExistingLoans count must not be negative");
        }
        this.existingLoansCount = existingLoansCount;
        if (latePaymentsLastYear < 0) {
            throw new IllegalArgumentException("LatePaymentsLastYear must not be negative");
        }
        this.latePaymentsLastYear = latePaymentsLastYear;
        if (requestedAmount == null) {
            throw new IllegalArgumentException("RequestedAmount must not be null");
        } else if (requestedAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("RequestedAmount must be greater than zero");
        }
        this.requestedAmount = requestedAmount;
    }

    public boolean isAdult() {
        return age > 18;
    }

    public boolean hasCreditHistory() {
        return creditHistoryYears > 0;
    }

    public boolean hasLatePaymentsLastYear() {
        return latePaymentsLastYear > 0;
    }

    public int getAge() {
        return age;
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public int getLatePaymentsLastYear() {
        return latePaymentsLastYear;
    }

    public int getExistingLoansCount() {
        return existingLoansCount;
    }

    public int getCreditHistoryYears() {
        return creditHistoryYears;
    }
}
