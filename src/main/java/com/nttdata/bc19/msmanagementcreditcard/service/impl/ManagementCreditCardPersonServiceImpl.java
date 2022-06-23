package com.nttdata.bc19.msmanagementcreditcard.service.impl;

import com.nttdata.bc19.msmanagementcreditcard.exception.ModelNotFoundException;
import com.nttdata.bc19.msmanagementcreditcard.model.CreditCardPerson;
import com.nttdata.bc19.msmanagementcreditcard.model.responseWC.ActiveProduct;
import com.nttdata.bc19.msmanagementcreditcard.model.responseWC.PersonClient;
import com.nttdata.bc19.msmanagementcreditcard.repository.ICreditCardPersonRepository;
import com.nttdata.bc19.msmanagementcreditcard.request.CreditCardPersonRequest;
import com.nttdata.bc19.msmanagementcreditcard.service.IManagementCreditCardPersonService;
import com.nttdata.bc19.msmanagementcreditcard.util.ActiveProductType;
import com.nttdata.bc19.msmanagementcreditcard.webclient.impl.ServiceWCImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ManagementCreditCardPersonServiceImpl implements IManagementCreditCardPersonService {

    @Autowired
    ICreditCardPersonRepository creditCardPersonRepository;

    @Autowired
    private ServiceWCImpl clientServiceWC;

    @Override
    public Mono<CreditCardPerson> create(CreditCardPersonRequest creditCardPersonRequest) {
        return clientServiceWC.findPersonClientById(creditCardPersonRequest.getIdPersonClient())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(personClientResponse ->
                        clientServiceWC.findActiveProductById(creditCardPersonRequest.getIdActiveProduct())
                                .switchIfEmpty(Mono.error(new Exception()))
                                .flatMap(creditCardResponse -> this.businessLogicCurrentAccountPerson(personClientResponse, creditCardResponse, creditCardPersonRequest))
                        );
    }

    @Override
    public Mono<CreditCardPerson> update(CreditCardPerson creditCardPerson) {
        creditCardPerson.setUpdatedAt(LocalDateTime.now());
        return creditCardPersonRepository.save(creditCardPerson);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return creditCardPersonRepository.deleteById(id);
    }

    @Override
    public Mono<CreditCardPerson> findById(String id) {
        return creditCardPersonRepository.findById(id);
    }

    @Override
    public Flux<CreditCardPerson> findByIdPersonClient(String idPersonClient) {
        return creditCardPersonRepository.findByIdPersonClient(idPersonClient);
    }

    @Override
    public Mono<Long> countByIdPersonClient(String idPersonClient) {
        return creditCardPersonRepository.countByIdPersonClient(idPersonClient);
    }

    @Override
    public Flux<CreditCardPerson> findAll() {
        return creditCardPersonRepository.findAll();
    }

    private Mono<CreditCardPerson> businessLogicCurrentAccountPerson(PersonClient personClient, ActiveProduct creditCard, CreditCardPersonRequest creditCardPersonRequest){
        CreditCardPerson creditCardPerson = new CreditCardPerson();
        creditCardPerson.setId(new ObjectId().toString());
        creditCardPerson.setCreatedAt(LocalDateTime.now());
        creditCardPerson.setCreditLine(creditCardPersonRequest.getCreditLine());
        creditCardPerson.setAnnualCommission(creditCardPersonRequest.getAnnualCommission());
        creditCardPerson.setCutoffDate(creditCardPersonRequest.getCutoffDate());
        creditCardPerson.setPayLimitDate(creditCardPersonRequest.getPayLimitDate());
        creditCardPerson.setCreditCardNumber(creditCardPersonRequest.getCreditCardNumber());
        creditCardPerson.setIdPersonClient(creditCardPersonRequest.getIdPersonClient());
        creditCardPerson.setIdActiveProduct(creditCardPersonRequest.getIdActiveProduct());
        creditCardPerson.setPersonClient(personClient);
        creditCardPerson.setActiveProduct(creditCard);

        if(creditCard.getName().equals(ActiveProductType.TARJETACREDITO.name())){
            return Mono.error(new ModelNotFoundException("The active is not CreditCard."));
        }
        if(!creditCard.getAllowPersonClient()) {
            return Mono.error(new ModelNotFoundException("Type of active not allowed for person client"));
        }
        else{
            return creditCardPersonRepository.save(creditCardPerson);
        }
    }
}
