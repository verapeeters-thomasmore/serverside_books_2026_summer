package be.thomasmore.bookserver.repositories;

import be.thomasmore.bookserver.model.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Integer> {
    Optional<Member> findByUsername(String username);
}
