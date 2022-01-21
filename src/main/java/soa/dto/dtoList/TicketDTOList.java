package soa.dto.dtoList;

import soa.dto.TicketDTO;
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
public class TicketDTOList {
    @XmlElementWrapper(name = "ticketList")
    @XmlElement(name = "ticketList")
    private List<TicketDTO> ticketList;
    private long count;
}
