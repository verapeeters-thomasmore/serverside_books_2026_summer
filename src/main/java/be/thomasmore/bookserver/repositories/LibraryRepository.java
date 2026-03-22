package be.thomasmore.bookserver.repositories;

import be.thomasmore.bookserver.model.Library;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;

public interface LibraryRepository extends CrudRepository<Library, Integer> {
    @NonNull
    List<Library> findAll();

    @Query("select l from Library l where " +
            "(:location is NULL or l.location ILIKE %:location%) AND " +
            "(:manager is NULL or l.managerName ILIKE %:manager%)" )
    List<Library> findByFilter(@Param("location") String location,
                               @Param("manager") String manager);
}
