package soa.services;


import soa.entity.Coordinates;
import org.springframework.stereotype.Service;
import soa.repository.CoordinatesRepository;
import soa.validation.EntityValidator;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CoordinatesService {

    private final CoordinatesRepository coordinatesRepository;
    private final EntityValidator entityValidator;

    public CoordinatesService(CoordinatesRepository coordinatesRepository, EntityValidator entityValidator) {
        this.coordinatesRepository = coordinatesRepository;
        this.entityValidator = entityValidator;
    }

    public Coordinates getCoordinatesById(Integer id) {
        return coordinatesRepository.findById(id).orElseThrow(NoResultException::new);
    }

    public List<Coordinates> getCoordinates() {
        return coordinatesRepository.findAll();
    }

    @Transactional
    public void updateCoordinates(Coordinates coordinatesToUpdate) {
        coordinatesRepository.findById(coordinatesToUpdate.getId())
                .orElseThrow(() -> new NoResultException("Coordinates with id " + coordinatesToUpdate.getId() + " does not exist"));
        entityValidator.validateCoordinates(coordinatesToUpdate);
        coordinatesRepository.save(coordinatesToUpdate);
    }

    @Transactional
    public void createCoordinates(Coordinates coordinatesToPersist) {
        entityValidator.validateCoordinates(coordinatesToPersist);
        coordinatesRepository.save(coordinatesToPersist);
    }
}