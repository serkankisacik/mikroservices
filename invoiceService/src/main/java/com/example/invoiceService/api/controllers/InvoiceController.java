package com.example.invoiceService.api.controllers;

import com.example.invoiceService.business.abstracts.InvoiceService;
import com.example.invoiceService.business.requests.create.CreateInvoiceRequest;
import com.example.invoiceService.business.requests.update.UpdateInvoiceRequest;
import com.example.invoiceService.business.responses.create.CreateInvoiceResponse;
import com.example.invoiceService.business.responses.get.GetAllInvoiceResponse;
import com.example.invoiceService.business.responses.get.GetInvoiceResponse;
import com.example.invoiceService.business.responses.update.UpdateInvoiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping
    public List<GetAllInvoiceResponse> getAll() {
        return invoiceService.getAll();
    }

    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable String id) {
        return invoiceService.getById(id);
    }

    @PostMapping
    public CreateInvoiceResponse add(@Valid @RequestBody CreateInvoiceRequest request) {
        return invoiceService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateInvoiceResponse update(@Valid @RequestBody UpdateInvoiceRequest request, @PathVariable String id) {
        return invoiceService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        invoiceService.delete(id);
    }
}
