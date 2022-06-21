package com.nttdata.bc19.msmanagementcreditcard.service;

import com.nttdata.bc19.msmanagementcreditcard.model.CreditCardBusiness;
import com.nttdata.bc19.msmanagementcreditcard.model.CreditCardPerson;
import com.nttdata.bc19.msmanagementcreditcard.request.CreditCardBusinessRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IManagementCreditCardBusinessService {

    Mono<CreditCardBusiness> create(CreditCardBusinessRequest CreditCardBusinessRequest);
    Mono<CreditCardBusiness> update(CreditCardBusiness CreditCardBusiness);
    Mono<Void>deleteById(String id);
    Mono<CreditCardBusiness> findById(String id);
    Flux<CreditCardBusiness> findAll();

    Flux<CreditCardBusiness> findByIdBusinessClient(String idBusinessClient);

    Mono<Long> countByIdBusinessClient(String idBusinessClient);

}
