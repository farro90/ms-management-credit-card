package com.nttdata.bc19.msmanagementcreditcard.repository;

import com.nttdata.bc19.msmanagementcreditcard.model.CreditCardPerson;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ICreditCardPersonRepository extends ReactiveMongoRepository<CreditCardPerson, String> {
    Flux<CreditCardPerson> findByIdPersonClient(String id);

    Mono<Long> countByIdPersonClient(String id);

    Flux<CreditCardPerson> findByIdPersonClientAndIdActiveProduct(String idPersonClient, String idPasiveProduct);

    Mono<Long> countByIdPersonClientAndIdActiveProduct(String idPersonClient, String idPasiveProduct);
}
