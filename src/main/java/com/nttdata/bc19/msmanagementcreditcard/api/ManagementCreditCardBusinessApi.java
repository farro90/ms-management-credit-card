package com.nttdata.bc19.msmanagementcreditcard.api;

import com.nttdata.bc19.msmanagementcreditcard.model.CreditCardBusiness;
import com.nttdata.bc19.msmanagementcreditcard.model.CreditCardPerson;
import com.nttdata.bc19.msmanagementcreditcard.request.CreditCardBusinessRequest;
import com.nttdata.bc19.msmanagementcreditcard.service.IManagementCreditCardBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credit-card/business")
public class ManagementCreditCardBusinessApi {

    @Autowired
    private IManagementCreditCardBusinessService managementCreditCardBusinessService;

    @PostMapping()
    public Mono<CreditCardBusiness> create(@RequestBody CreditCardBusinessRequest creditCardBusinessRequest){
        return managementCreditCardBusinessService.create(creditCardBusinessRequest);
    }

    @PutMapping()
    public Mono<CreditCardBusiness> update(@RequestBody CreditCardBusiness creditCardBusiness){
        return managementCreditCardBusinessService.update(creditCardBusiness);
    }

    @GetMapping()
    public Flux<CreditCardBusiness> findAll(){
        return managementCreditCardBusinessService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<CreditCardBusiness> findById(@PathVariable String id){ return managementCreditCardBusinessService.findById(id); }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteSA(@PathVariable String id){
        return managementCreditCardBusinessService.deleteById(id);
    }

    @GetMapping("findByIdBusinessClient/{idBusinessClient}")
    public Flux<CreditCardBusiness> findByIdPersonClient(@PathVariable String idBusinessClient){ return managementCreditCardBusinessService.findByIdBusinessClient(idBusinessClient); }

    @GetMapping("countByIdBusinessClient/{idBusinessClient}")
    public Mono<Long> countByIdPersonClient(@PathVariable String idBusinessClient){ return managementCreditCardBusinessService.countByIdBusinessClient(idBusinessClient); }

}
