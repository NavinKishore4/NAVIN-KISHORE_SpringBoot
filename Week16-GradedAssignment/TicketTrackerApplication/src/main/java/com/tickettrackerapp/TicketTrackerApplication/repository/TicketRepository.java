package com.tickettrackerapp.TicketTrackerApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickettrackerapp.TicketTrackerApplication.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
