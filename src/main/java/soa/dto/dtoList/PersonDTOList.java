package soa.dto.dtoList;

import soa.dto.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class PersonDTOList {
    @XmlElementWrapper(name = "personList")
    @XmlElement(name = "personList")
    private List<PersonDTO> personsList;
}
