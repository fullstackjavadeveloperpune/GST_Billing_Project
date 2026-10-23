package com.fullstack.controller;

import com.fullstack.entity.GSTBill;
import com.fullstack.service.IGSTBillService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/gstbills")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "Bearer Auth")

@CrossOrigin(origins = "*")

public class GSTBillController {

    private final IGSTBillService gstBillService;

    @PostMapping("/createnewinvoice")
    public ResponseEntity<GSTBill> createNewInvoice(@RequestBody @Valid GSTBill gstBill) {

        log.info("@@@@@@@Trying to create GST Invoice for Customer:: " + gstBill.getCustName());
        return new ResponseEntity<>(gstBillService.createGSTInvoice(gstBill), HttpStatus.CREATED);
    }

    @GetMapping("/findbyinvoiceid/{invoiceId}")
    public ResponseEntity<Optional<GSTBill>> findById(@PathVariable long invoiceId) {

        return new ResponseEntity<>(gstBillService.findById(invoiceId), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<GSTBill>> findAll() {

        return new ResponseEntity<>(gstBillService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/searchbyanyinput/{input}")
    public ResponseEntity<List<GSTBill>> searchByAnyInput(@PathVariable String input) {

        return new ResponseEntity<>(gstBillService.searchByAnyInput(input), HttpStatus.OK);
    }

    @GetMapping("/sortBy")
    public ResponseEntity<List<GSTBill>> sortBy(@RequestParam(defaultValue = "") String input) {

        return new ResponseEntity<>(gstBillService.sortBy(input), HttpStatus.OK);
    }


    @PutMapping("/update/{invoiceId}")
    public ResponseEntity<GSTBill> update(@PathVariable long invoiceId, @RequestBody @Valid GSTBill gstBill) {

        return new ResponseEntity<>(gstBillService.update(invoiceId, gstBill), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid/{invoiceId}")
    public ResponseEntity<Void> deleteById(@PathVariable long invoiceId) {

        gstBillService.deleteById(invoiceId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
