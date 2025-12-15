package be.thomasmore.bookserver.services;

import be.thomasmore.bookserver.model.Publisher;
import be.thomasmore.bookserver.model.converters.PublisherDetailedDTOConverter;
import be.thomasmore.bookserver.model.dto.PublisherDetailedDTO;
import be.thomasmore.bookserver.repositories.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private PublisherDetailedDTOConverter publisherDetailedDTOConverter;


    public PublisherDetailedDTO findOne(int id) {
        final Optional<Publisher> publisher = publisherRepository.findById(id);
        if (publisher.isEmpty())
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Publisher with id %d does not exist.", id));
        return publisherDetailedDTOConverter.convertToDto(publisher.get());
    }

    public double findInvoicePrice(int id, double basePrice, int quantity) {
        final Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (publisherOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Publisher with id %d does not exist.", id));
        Publisher publisher = publisherOptional.get();
        Double discountPercentage = publisher.getBaseDiscountPercentage();
        if (quantity >= publisher.getBulkPurchaseQuantity()) {
            discountPercentage = publisher.getBulkDiscountPercentage();
        }
        log.info(String.format("##### findInvoicePrice discount %f", discountPercentage));
        double calculatedPrice = basePrice * quantity * (1 - discountPercentage / 100);
        double roundedPrice = Math.floor(calculatedPrice*100)/100;
        return roundedPrice;
    }
}
