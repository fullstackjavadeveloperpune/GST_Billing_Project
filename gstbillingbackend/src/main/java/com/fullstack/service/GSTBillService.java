package com.fullstack.service;

import com.fullstack.entity.GSTBill;
import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.repository.GSTBillRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GSTBillService implements IGSTBillService {

    private final GSTBillRepository gstBillRepository;

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public GSTBill createGSTInvoice(GSTBill gstBill) {

        log.info("Invoice Amount: " + gstBill.getInvoiceAmount());

        // Calculate 18% GST
        double gstAmount = gstBill.getInvoiceAmount() * 18 / 100;

        // Add GST to the base amount
        double totalAmount = gstBill.getInvoiceAmount() + gstAmount;

        log.info("After GST Invoice Amount: " + totalAmount);
        gstBill.setInvoiceAmount(totalAmount);

        GSTBill createdGSTBill = gstBillRepository.save(gstBill);
        sendEmail(createdGSTBill);
        return createdGSTBill;
    }

    @Override
    public Optional<GSTBill> findById(long invoiceId) {
        return Optional.of(gstBillRepository.findById(invoiceId).orElseThrow(() -> new RecordNotFoundException("GST Bill Invoice Does Not Exist")));

    }

    @Override
    public List<GSTBill> findAll() {
        return gstBillRepository.findAll();
    }

    private void sendEmail(GSTBill gstBill) {

        // Email
        // Invoice Id, Customer Name, Invoice Amount, Date, Subject, Email Body, etc

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(fromEmail);

            mimeMessageHelper.setTo(gstBill.getCustEmailId());

            mimeMessageHelper.setSubject("GST Invoice");

            mimeMessageHelper.setText("Invoice ID: "+ gstBill.getInvoiceId() + "\n Customer Name: " + gstBill.getCustName() + "\n Customer Address: " + gstBill.getCustAddress() + "\n Invoice Date: " + gstBill.getInvoiceDate() + "\n Invoice Amount: " + gstBill.getInvoiceAmount());


            javaMailSender.send(mimeMessage);

            log.info("Email Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
