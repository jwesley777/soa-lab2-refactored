package soa.services;

import soa.dto.PagedTicketList;
import soa.entity.Ticket;
import soa.enums.TicketType;
import org.springframework.stereotype.Service;
import soa.repository.TicketRepository;
import soa.validation.EntityValidator;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final EntityValidator entityValidator;

    public TicketService(TicketRepository ticketRepository, EntityValidator entityValidator) {
        this.ticketRepository = ticketRepository;
        this.entityValidator = entityValidator;
    }

    public Ticket getTicketById(Integer id) {
        return ticketRepository.findById(id).orElseThrow(NoResultException::new);
    }

    public PagedTicketList getTickets(Integer perPage, Integer curPage, String sortBy, String filterBy) {
        return ticketRepository.findAll(perPage, curPage, sortBy, filterBy);
    }

    @Transactional
    public void deleteTicket(Integer id) {
        ticketRepository.deleteTicketById(id);
    }

    @Transactional
    public void updateTicket(Ticket ticketToUpdate) {
        Ticket existingTicket = ticketRepository.findById(ticketToUpdate.getId())
                .orElseThrow(() -> new NoResultException("Ticket with id " + ticketToUpdate.getId() + " does not exist"));
        ticketToUpdate.setCreationDate(existingTicket.getCreationDate());
        entityValidator.validateTicket(ticketToUpdate);
        ticketRepository.save(ticketToUpdate);
    }

    @Transactional
    public void createTicket(Ticket ticketToPersist) {
        ticketToPersist.setCreationDate(new Date());
        entityValidator.validateTicket(ticketToPersist);
        ticketRepository.save(ticketToPersist);
    }

    public Long countAllByPerson_IdLessThan(Integer personId) {
        return ticketRepository.countAllByPerson_IdLessThan(personId);
    }

    public List<Ticket> findAllByDiscountLessThan(Long discount) {
        return ticketRepository.findAllByDiscountLessThan(discount);
    }

    public List<TicketType> getDistinctTypes() {
        return ticketRepository.getDistinctTypes();
    }






}
