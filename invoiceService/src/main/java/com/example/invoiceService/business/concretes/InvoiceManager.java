package com.example.invoiceService.business.concretes;

import com.example.invoiceService.business.abstracts.InvoiceService;
import com.example.invoiceService.business.requests.create.CreateInvoiceRequest;
import com.example.invoiceService.business.requests.update.UpdateInvoiceRequest;
import com.example.invoiceService.business.responses.create.CreateInvoiceResponse;
import com.example.invoiceService.business.responses.get.GetAllInvoiceResponse;
import com.example.invoiceService.business.responses.get.GetInvoiceResponse;
import com.example.invoiceService.business.responses.update.UpdateInvoiceResponse;
import com.example.invoiceService.dataAccess.abstracts.InvoiceRepository;
import com.example.invoiceService.entities.Invoice;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ModelMapperService mapper;


    @Override
    public List<GetAllInvoiceResponse> getAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<GetAllInvoiceResponse> responses = invoices.stream().map(invoice -> mapper.forResponse().map(invoice, GetAllInvoiceResponse.class)).toList();
        return responses;
    }

    @Override
    public GetInvoiceResponse getById(String id) {
        checkIfInvoiceExists(id);
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapper.forResponse().map(invoice, GetInvoiceResponse.class);
        return response;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        Invoice invoice = mapper.forRequest().map(request, Invoice.class);
        invoice.setId(UUID.randomUUID().toString());
        invoiceRepository.save(invoice);
        CreateInvoiceResponse response = mapper.forResponse().map(invoice, CreateInvoiceResponse.class);
        return response;
    }

    @Override
    public UpdateInvoiceResponse update(UpdateInvoiceRequest request, String id) {
        checkIfInvoiceExists(id);
        Invoice invoice = mapper.forRequest().map(request, Invoice.class);
        invoice.setId(id);
        invoiceRepository.save(invoice);
        UpdateInvoiceResponse response = mapper.forResponse().map(invoice, UpdateInvoiceResponse.class);
        return response;
    }

    @Override
    public void delete(String id) {
        checkIfInvoiceExists(id);
        invoiceRepository.deleteById(id);

    }

    @Override
    public void createInvoice(Invoice invoice) {
        invoice.setId(UUID.randomUUID().toString());
        invoiceRepository.save(invoice);
    }

    private void checkIfInvoiceExists(String id) {
        if (!invoiceRepository.existsById(id)) {
            throw new BusinessException("INVOICE_NOT_FOUND");
        }
    }
}
