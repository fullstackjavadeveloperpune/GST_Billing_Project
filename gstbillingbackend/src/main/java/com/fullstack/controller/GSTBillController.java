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

}
