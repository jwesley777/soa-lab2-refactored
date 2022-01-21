package soa.repository;

import soa.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {
    Optional<Location> findById(Integer id);

    List<Location> findAll();
}
