package soa.controllers;

import soa.dto.PagedTicketList;
import soa.dto.TicketDTO;
import soa.dto.dtoList.TicketDTOList;
import soa.entity.Ticket;
import soa.mapper.TicketMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import soa.services.TicketService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value="/tickets", produces = MediaType.APPLICATION_XML_VALUE)
@Validated
@CrossOrigin
public class TicketController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    public TicketController(
            TicketService ticketService,
            TicketMapper ticketMapper
    ) {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
    }

    @GetMapping
    public TicketDTOList getTickets(
        @RequestParam(name = "perPage", required = false) Integer perPage,
        @RequestParam(name = "curPage", required = false) Integer curPage,
        @RequestParam(name = "sortBy", required = false) String sortBy,
        @RequestParam(name = "filterBy", required = false) String filterBy
    ) {
        System.out.println("FILTER BY " + filterBy);
        PagedTicketList pagedTicketList = ticketService.getTickets(perPage,curPage,sortBy,filterBy);
        return new TicketDTOList(
                ticketMapper.mapTicketListToTicketDTOList(pagedTicketList.getTicketList()),
                pagedTicketList.getCount()
        );
    }

    @GetMapping(value = "/{id}")
    public TicketDTOList getTicket(@PathVariable(name="id") Integer id) {
        Ticket ticket = ticketService.getTicketById(id);
        TicketDTOList dto = new TicketDTOList(new ArrayList<>(), 1);
        List<TicketDTO> dtoList = dto.getTicketList();
        dtoList.add(ticketMapper.mapTicketToTicketDTO(ticket));
        return dto;
    }

    @PutMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity updateTicket(@RequestBody TicketDTOList ticketDTOList) {
        System.out.println("UPDATE");
        Ticket ticketToUpdate = ticketMapper.mapTicketDTOToTicket(ticketDTOList.getTicketList().get(0));
        ticketService.updateTicket(ticketToUpdate);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createTicket(@RequestBody TicketDTOList ticketDTOList) {
        System.out.println("CREATE");
        Ticket ticketToCreate = ticketMapper.mapTicketDTOToTicket(ticketDTOList.getTicketList().get(0));
        ticketService.createTicket(ticketToCreate);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTicket(@PathVariable(name = "id") Integer id) {
        ticketService.deleteTicket(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
