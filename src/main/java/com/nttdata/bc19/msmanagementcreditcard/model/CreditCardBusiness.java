package com.nttdata.bc19.msmanagementcreditcard.model;

import com.nttdata.bc19.msmanagementcreditcard.model.responseWC.ActiveProduct;
import com.nttdata.bc19.msmanagementcreditcard.model.responseWC.BusinessClient;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class CreditCardBusiness extends BaseModel{
    private String creditCardNumber;
    private double creditLine;
    private double amountConsumed;
    private double minimumPayment;
    private int annualCommission;
    private int cutoffDate;
    private int payLimitDate;
    private LocalDateTime openingDate;
    private LocalDateTime deliveryDate;
    private String idBusinessClient;
    private String idActiveProduct;
    private BusinessClient businessClient;
    private ActiveProduct activeProduct;
}
