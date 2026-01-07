package be.thomasmore.bookserver.repositories;

import be.thomasmore.bookserver.model.Member;
import org.springframework.data.repository.CrudRepository;


public interface MemberRepository extends CrudRepository<Member, Integer> {


}