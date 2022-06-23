package com.nttdata.bc19.msmanagementcreditcard.request;

import lombok.Data;

@Data
public class CreditCardBusinessRequest {
    private double creditLine;
    private int annualCommission;
    private String creditCardNumber;
    private int cutoffDate;
    private int payLimitDate;
    private String idBusinessClient;
    private String idActiveProduct;
}
