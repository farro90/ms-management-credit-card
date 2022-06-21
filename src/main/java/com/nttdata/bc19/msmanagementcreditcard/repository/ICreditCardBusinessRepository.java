package com.nttdata.bc19.msmanagementcreditcard.repository;

import com.nttdata.bc19.msmanagementcreditcard.model.CreditCardBusiness;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ICreditCardBusinessRepository extends ReactiveMongoRepository<CreditCardBusiness, String> {
    Flux<CreditCardBusiness> findByIdBusinessClient(String id);

    Mono<Long> countByIdBusinessClient(String id);

    Flux<CreditCardBusiness> findByIdBusinessClientAndIdActiveProduct(String idBusinessClient, String idPasiveProduct);

    Mono<Long> countByIdBusinessClientAndIdActiveProduct(String idBusinessClient, String idPasiveProduct);
}
