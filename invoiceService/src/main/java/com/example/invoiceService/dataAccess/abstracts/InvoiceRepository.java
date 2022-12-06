package com.example.invoiceService.dataAccess.abstracts;

import com.example.invoiceService.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {

}
