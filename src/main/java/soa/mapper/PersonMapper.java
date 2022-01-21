package soa.mapper;

import soa.dto.PersonDTO;
import soa.entity.Person;
import soa.exceptions.EntityIsNotValidException;
import org.springframework.stereotype.Service;
import soa.repository.LocationRepository;
import soa.utils.FieldValidationUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonMapper {

    private final LocationMapper locationMapper;
    private final LocationRepository locationRepository;

    public PersonMapper(LocationMapper locationMapper, LocationRepository locationRepository) {
        this.locationMapper = locationMapper;
        this.locationRepository = locationRepository;
    }

    public Person mapPersonDTOToPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setId(FieldValidationUtil.getIntegerFieldValue(personDTO.getId()));
        person.setEyeColor(FieldValidationUtil.getColorValue(personDTO.getEyeColor()));
        person.setHeight(FieldValidationUtil.getDoubleFieldValue(personDTO.getHeight()));
        if (personDTO.getLocation().getId().equals(""))
            throw new EntityIsNotValidException("location must not be null");
        person.setLocation(locationMapper.mapLocationDTOToLocation(personDTO.getLocation()));
        if (person.getLocation().getId() != null) {
            if (!locationRepository.existsById(person.getLocation().getId()))
                throw new EntityIsNotValidException("location with id = " + person.getLocation().getId() + " does not exist");
        }

        return person;
    }

    public PersonDTO mapPersonToPersonDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(String.valueOf(person.getId()));
        personDTO.setLocation(locationMapper.mapLocationToLocationDTO(person.getLocation()));
        personDTO.setHeight(String.valueOf(person.getHeight()));
        personDTO.setEyeColor(String.valueOf(person.getEyeColor()));
        return personDTO;
    }

    public List<PersonDTO> mapPersonListToPersonDTOList(List<Person> personList) {
        ArrayList<PersonDTO> personDTOArrayList = new ArrayList<>();
        for (Person person : personList) {
            personDTOArrayList.add(mapPersonToPersonDTO(person));
        }
        return personDTOArrayList;
    }
}
