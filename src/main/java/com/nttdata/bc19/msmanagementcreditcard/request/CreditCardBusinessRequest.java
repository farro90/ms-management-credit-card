package com.nttdata.bc19.msmanagementcreditcard.request;

import lombok.Data;

@Data
public class CreditCardBusinessRequest {
    private double creditLine;
    private int AnnualCommission;
    private int cutoffDate;
    private int payLimitDate;
    private String idBusinessClient;
    private String idActiveProduct;
}
