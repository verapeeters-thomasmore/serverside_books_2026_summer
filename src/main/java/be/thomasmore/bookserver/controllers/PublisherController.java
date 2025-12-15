package be.thomasmore.bookserver.controllers;

import be.thomasmore.bookserver.model.dto.PublisherDetailedDTO;
import be.thomasmore.bookserver.model.dto.SerieDTO;
import be.thomasmore.bookserver.model.dto.SerieDetailedDTO;
import be.thomasmore.bookserver.services.PublisherService;
import be.thomasmore.bookserver.services.SerieService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publishers")
@Slf4j
public class PublisherController {

    @Autowired
    private PublisherService publisherService;



    @Operation(summary = "get 1 publisher from the database.",
            description = "Publisher with id is fetched from database. ")
    @GetMapping("{id}")
    public PublisherDetailedDTO findOne(@PathVariable int id) {
        log.info(String.format("##### findOne publisher %d", id));
        return publisherService.findOne(id);
    }

    @Operation(summary = "get price for 1 publisher from the database.",
            description = "Publisher with id is fetched from database. ")
    @GetMapping("{id}/invoiceprice")
    public double invoicePrice(@PathVariable int id,
                               @RequestParam double basePrice,
                               @RequestParam int quantity) {
        log.info(String.format("##### invoicePrice publisher %d", id));
        return publisherService.findInvoicePrice(id, basePrice, quantity);
    }

}
