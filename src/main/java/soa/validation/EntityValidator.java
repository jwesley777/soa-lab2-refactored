package soa.validation;

import soa.entity.Coordinates;
import soa.entity.Location;
import soa.entity.Ticket;
import soa.entity.Person;
import soa.exceptions.EntityIsNotValidException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class EntityValidator {
    private Validator validator;

    public EntityValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private String formExceptionMsg(Set<ConstraintViolation<Object>> constraintViolations) {
        String errorMessage = "";
        for (ConstraintViolation<Object> violation : constraintViolations) {
            errorMessage = errorMessage.concat(violation.getMessage() + "\n");
        }
        return errorMessage;
    }

    public void validateTicket(Ticket ticket) throws EntityIsNotValidException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(ticket);
        if (!constraintViolations.isEmpty())
            throw new EntityIsNotValidException(formExceptionMsg(constraintViolations));
    }

    public void validateLocation(Location movie) throws EntityIsNotValidException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(movie);
        if (!constraintViolations.isEmpty())
            throw new EntityIsNotValidException(formExceptionMsg(constraintViolations));
    }

    public void validateCoordinates(Coordinates movie) throws EntityIsNotValidException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(movie);
        if (!constraintViolations.isEmpty())
            throw new EntityIsNotValidException(formExceptionMsg(constraintViolations));
    }

    public void validatePerson(Person movie) throws EntityIsNotValidException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(movie);
        if (!constraintViolations.isEmpty())
            throw new EntityIsNotValidException(formExceptionMsg(constraintViolations));
    }


}
