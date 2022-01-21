package soa.repository;

import soa.dto.PagedTicketList;

public interface TicketRepositoryCustom {
    PagedTicketList findAll(Integer perPage, Integer curPage, String sortBy, String filterBy);
}
