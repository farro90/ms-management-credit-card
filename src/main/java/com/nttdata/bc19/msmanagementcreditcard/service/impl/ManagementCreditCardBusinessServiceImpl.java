package com.nttdata.bc19.msmanagementcreditcard.service.impl;

import com.nttdata.bc19.msmanagementcreditcard.exception.ModelNotFoundException;
import com.nttdata.bc19.msmanagementcreditcard.model.CreditCardBusiness;
import com.nttdata.bc19.msmanagementcreditcard.model.responseWC.ActiveProduct;
import com.nttdata.bc19.msmanagementcreditcard.model.responseWC.BusinessClient;
import com.nttdata.bc19.msmanagementcreditcard.repository.ICreditCardBusinessRepository;
import com.nttdata.bc19.msmanagementcreditcard.request.CreditCardBusinessRequest;
import com.nttdata.bc19.msmanagementcreditcard.service.IManagementCreditCardBusinessService;
import com.nttdata.bc19.msmanagementcreditcard.util.ActiveProductType;
import com.nttdata.bc19.msmanagementcreditcard.webclient.impl.ServiceWCImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ManagementCreditCardBusinessServiceImpl implements IManagementCreditCardBusinessService {

    @Autowired
    ICreditCardBusinessRepository creditCardBusinessRepository;

    @Autowired
    private ServiceWCImpl clientServiceWC;

    @Override
    public Mono<CreditCardBusiness> create(CreditCardBusinessRequest creditCardBusinessRequest) {
        return clientServiceWC.findBusinessClientById(creditCardBusinessRequest.getIdBusinessClient())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(businessClientResponse ->
                        clientServiceWC.findActiveProductById(creditCardBusinessRequest.getIdActiveProduct())
                                .switchIfEmpty(Mono.error(new Exception()))
                                .flatMap(creditCardResponse -> this.businessLogicCurrentAccountBusiness(businessClientResponse, creditCardResponse, creditCardBusinessRequest))
                );
    }

    @Override
    public Mono<CreditCardBusiness> update(CreditCardBusiness creditCardBusiness) {
        creditCardBusiness.setUpdatedAt(LocalDateTime.now());
        return creditCardBusinessRepository.save(creditCardBusiness);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return creditCardBusinessRepository.deleteById(id);
    }

    @Override
    public Mono<CreditCardBusiness> findById(String id) {
        return creditCardBusinessRepository.findById(id);
    }

    @Override
    public Flux<CreditCardBusiness> findAll() {
        return creditCardBusinessRepository.findAll();
    }

    @Override
    public Flux<CreditCardBusiness> findByIdBusinessClient(String idBusinessClient) {
        return creditCardBusinessRepository.findByIdBusinessClient(idBusinessClient);
    }

    @Override
    public Mono<Long> countByIdBusinessClient(String idBusinessClient) {
        return creditCardBusinessRepository.countByIdBusinessClient(idBusinessClient);
    }

    private Mono<CreditCardBusiness> businessLogicCurrentAccountBusiness(BusinessClient businessClient, ActiveProduct creditCard, CreditCardBusinessRequest creditCardBusinessRequest){
        CreditCardBusiness creditCardBusiness = new CreditCardBusiness();
        creditCardBusiness.setId(new ObjectId().toString());
        creditCardBusiness.setCreatedAt(LocalDateTime.now());
        creditCardBusiness.setCreditLine(creditCardBusinessRequest.getCreditLine());
        creditCardBusiness.setAnnualCommission(creditCardBusinessRequest.getAnnualCommission());
        creditCardBusiness.setCutoffDate(creditCardBusinessRequest.getCutoffDate());
        creditCardBusiness.setPayLimitDate(creditCardBusinessRequest.getPayLimitDate());
        //creditCardBusiness.setCreditCardNumber();
        creditCardBusiness.setIdBusinessClient(creditCardBusinessRequest.getIdBusinessClient());
        creditCardBusiness.setIdActiveProduct(creditCardBusinessRequest.getIdActiveProduct());
        creditCardBusiness.setBusinessClient(businessClient);
        creditCardBusiness.setActiveProduct(creditCard);

        if(creditCard.getName().equals(ActiveProductType.TARJETACREDITO.name())){
            return Mono.error(new ModelNotFoundException("The active is not CreditCard."));
        }
        if(!creditCard.getAllowBusinessClient()) {
            return Mono.error(new ModelNotFoundException("Type of active not allowed for Business client"));
        }
        else{
            return creditCardBusinessRepository.save(creditCardBusiness);
        }
    }
}
