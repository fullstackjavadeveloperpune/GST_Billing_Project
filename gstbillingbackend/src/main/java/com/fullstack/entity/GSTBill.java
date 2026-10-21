package com.fullstack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gst_bill")
public class GSTBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;

    private String custName;
    
    private String custAddress;

    private long custContactNumber;

    private LocalDate invoiceDate;

    private double invoiceAmount;

    private String custEmailId;

    private String custGSTNumber;
}
