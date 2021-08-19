package uk.tw.energy.domain;

import java.math.BigDecimal;

public class Cost {

    private BigDecimal value;
    private String errorMessage;

    public Cost(){

    }
    public Cost(BigDecimal value, String errorMessage){
        this.value = value;
        this.errorMessage = errorMessage;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
