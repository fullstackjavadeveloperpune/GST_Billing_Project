package com.fullstack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

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

    @Pattern(
            regexp = "^[a-zA-Z ]+$",
            message = "Customer name must contain only letters and spaces"
    )
    private String custName;
    
    private String custAddress;

    @Range(min = 100000000, max = 9999999999L, message = "Contact Number must be 10 Digit")
    private long custContactNumber;

    private LocalDate invoiceDate;

    private double invoiceAmount;

    @Email(message = "Email ID must be valid")
    private String custEmailId;

    @Pattern(
            regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z][1-9A-Z]Z[0-9A-Z]$",
            message = "Invalid GST number format"
    )
    private String custGSTNumber;
}
