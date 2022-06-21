package com.nttdata.bc19.msmanagementcreditcard.service;

import com.nttdata.bc19.msmanagementcreditcard.model.CreditCardPerson;
import com.nttdata.bc19.msmanagementcreditcard.request.CreditCardPersonRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IManagementCreditCardPersonService {

    Mono<CreditCardPerson> create(CreditCardPersonRequest CreditCardPersonRequest);
    Mono<CreditCardPerson> update(CreditCardPerson CreditCardPerson);
    Mono<Void>deleteById(String id);
    Mono<CreditCardPerson> findById(String id);
    Flux<CreditCardPerson> findAll();

    Flux<CreditCardPerson> findByIdPersonClient(String idPersonClient);

    Mono<Long> countByIdPersonClient(String idPersonClient);

}
