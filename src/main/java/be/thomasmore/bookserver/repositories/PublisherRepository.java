package be.thomasmore.bookserver.repositories;

import be.thomasmore.bookserver.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Integer> {

}
