package com.nttdata.bc19.msmanagementcreditcard.api;

import com.nttdata.bc19.msmanagementcreditcard.model.CreditCardPerson;
import com.nttdata.bc19.msmanagementcreditcard.request.CreditCardPersonRequest;
import com.nttdata.bc19.msmanagementcreditcard.service.IManagementCreditCardPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credit-card/person")
public class ManagementCreditCardPersonApi {

    @Autowired
    private IManagementCreditCardPersonService managementCreditCardPersonService;

    @PostMapping()
    public Mono<CreditCardPerson> create(@RequestBody CreditCardPersonRequest creditCardPersonRequest){
        return managementCreditCardPersonService.create(creditCardPersonRequest);
    }

    @PutMapping()
    public Mono<CreditCardPerson> update(@RequestBody CreditCardPerson creditCardPerson){
        return managementCreditCardPersonService.update(creditCardPerson);
    }

    @GetMapping()
    public Flux<CreditCardPerson> findAll(){
        return managementCreditCardPersonService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<CreditCardPerson> findById(@PathVariable String id){ return managementCreditCardPersonService.findById(id); }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return managementCreditCardPersonService.deleteById(id);
    }

    @GetMapping("findByIdPersonClient/{idPersonClient}")
    public Flux<CreditCardPerson> findByIdPersonClient(@PathVariable String idPersonClient){ return managementCreditCardPersonService.findByIdPersonClient(idPersonClient); }

    @GetMapping("countByIdPersonClient/{idPersonClient}")
    public Mono<Long> countByIdPersonClient(@PathVariable String idPersonClient){ return managementCreditCardPersonService.countByIdPersonClient(idPersonClient); }
}
