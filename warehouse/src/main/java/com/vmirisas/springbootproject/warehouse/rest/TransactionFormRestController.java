package com.vmirisas.springbootproject.warehouse.rest;

import com.vmirisas.springbootproject.warehouse.dto.TransactionFormDTO;
import com.vmirisas.springbootproject.warehouse.service.TransactionFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
@RestController
@RequestMapping("/form")
public class TransactionFormRestController {

    @Autowired
    private TransactionFormService transactionFormService;

    // expose "/form" and return list of transaction forms
    @GetMapping("")
    public List<TransactionFormDTO> findAll() {
        return this.transactionFormService.toDtoList(this.transactionFormService.findAll());
    }

    //  add mapping for GET /form/{transactionFormId}
    @GetMapping("/{transactionFormId}")
    public TransactionFormDTO getTransactionForm(@PathVariable Long transactionFormId) {
        return new TransactionFormDTO(this.transactionFormService.findById(transactionFormId));
    }

    // add mapping for POST /form/add = add new transaction form
    @PostMapping("/add")
    public TransactionFormDTO addTransactionForm(@RequestBody TransactionFormDTO theTransactionForm) {
        // also just in case the pass an ID in JSON ... set id to null
        theTransactionForm.setTransactionFormId(null);
        return new TransactionFormDTO(this.transactionFormService.save(theTransactionForm));
    }

    // add mapping for PUT /form/update = update existing transaction form
    @PutMapping("/update")
    public TransactionFormDTO updateTransactionForm(@RequestBody TransactionFormDTO theTransactionForm) {
        return new TransactionFormDTO(this.transactionFormService.save(theTransactionForm));
    }

    // add mapping for DELETE /form/remove/{transactionFormId} = delete existing transaction form
    @DeleteMapping("/delete/{transactionFormId}")
    public void deleteTransactionForm(@PathVariable Long transactionFormId) {
        transactionFormService.deleteById(transactionFormId);
//        return "Deleted Transaction Form with id  '" + transactionFormId + "'";
    }
}
