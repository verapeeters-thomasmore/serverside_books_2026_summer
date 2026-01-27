package be.thomasmore.bookserver.repositories;

import be.thomasmore.bookserver.model.Award;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface AwardRepository extends CrudRepository<Award, Integer> {
    @NonNull
    List<Award> findAll();

    Optional<Award> findByAwardName(String awardName);
}
