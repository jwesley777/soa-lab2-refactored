package soa.controllers;

import soa.dto.CountDTO;
import soa.dto.dtoList.TicketDTOList;
import soa.dto.dtoList.TicketTypeDTOList;
import soa.entity.Ticket;
import soa.enums.TicketType;
import soa.mapper.TicketMapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soa.services.TicketService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/additional", produces = MediaType.APPLICATION_XML_VALUE)
@Validated
public class AdditionalTasksController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    public AdditionalTasksController(TicketService ticketService, TicketMapper ticketMapper) {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
    }

    @GetMapping
    public TicketTypeDTOList getDistinctTicketTypes() {
        List<TicketType> ticketTypeList = ticketService.getDistinctTypes();
        TicketTypeDTOList dto = new TicketTypeDTOList(new ArrayList<>());
        dto.setTicketTypeList(ticketMapper.mapTicketTypesListToTicketTypesDTOList(ticketTypeList));
        return dto;
    }

    @GetMapping(params={"person"})
    public CountDTO countTicketsByPeopleWithIdLessThan(
            @RequestParam(name="person") Integer person
    ) {
        Long countResult = ticketService.countAllByPerson_IdLessThan(person);
        CountDTO dto = new CountDTO();
        dto.setCount(countResult);
        return dto;
    }

    @GetMapping(params={"discount"})
    public TicketDTOList findTicketsByDiscountLessThan (
            @RequestParam(name="discount") Long discount
    ) {
        List<Ticket> ticketList = ticketService.findAllByDiscountLessThan(discount);
        TicketDTOList dto = new TicketDTOList(
                ticketMapper.mapTicketListToTicketDTOList(ticketList),
                ticketList.size()
        );
        return dto;
    }
}
