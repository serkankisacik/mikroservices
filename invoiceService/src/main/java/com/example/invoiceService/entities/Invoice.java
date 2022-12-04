package com.example.invoiceService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "number")
    private String number;

    @Column(name = "userId")
    private String userId;

    @Column(name = "paymentId")
    private String paymentId;

    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "referenceNumber")
    private String referenceNumber;

}
