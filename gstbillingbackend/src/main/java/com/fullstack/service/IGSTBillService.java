package com.fullstack.service;

import com.fullstack.entity.GSTBill;

import java.util.List;
import java.util.Optional;

public interface IGSTBillService {

    GSTBill createGSTInvoice(GSTBill gstBill);

    Optional<GSTBill> findById(long invoiceId);

    List<GSTBill> findAll();

    GSTBill update(long invoiceId, GSTBill gstBill);

    List<GSTBill> searchByAnyInput(String input);

    List<GSTBill> sortBy(String input);

    void deleteById(long invoiceId);

}
