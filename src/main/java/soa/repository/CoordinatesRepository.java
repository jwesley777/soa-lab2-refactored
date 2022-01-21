package soa.repository;

import soa.entity.Coordinates;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CoordinatesRepository extends CrudRepository<Coordinates, Integer> {
    Optional<Coordinates> findById(Integer id);

    List<Coordinates> findAll();
}
