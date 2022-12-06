package com.example.invoiceService.business.abstracts;

import com.example.invoiceService.business.requests.create.CreateInvoiceRequest;
import com.example.invoiceService.business.requests.update.UpdateInvoiceRequest;
import com.example.invoiceService.business.responses.create.CreateInvoiceResponse;
import com.example.invoiceService.business.responses.get.GetAllInvoiceResponse;
import com.example.invoiceService.business.responses.get.GetInvoiceResponse;
import com.example.invoiceService.business.responses.update.UpdateInvoiceResponse;
import com.example.invoiceService.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoiceResponse> getAll();
    GetInvoiceResponse getById(String id);
    CreateInvoiceResponse add(CreateInvoiceRequest request);
    UpdateInvoiceResponse update(UpdateInvoiceRequest request, String id);
    void delete(String id);
    void createInvoice(Invoice invoice);
}
