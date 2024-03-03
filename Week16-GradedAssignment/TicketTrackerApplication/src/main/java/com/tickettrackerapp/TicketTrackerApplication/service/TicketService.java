package com.tickettrackerapp.TicketTrackerApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.tickettrackerapp.TicketTrackerApplication.model.Ticket;
import com.tickettrackerapp.TicketTrackerApplication.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	TicketRepository repo;

	public void addTicket(Ticket ticket) {
		repo.save(ticket);
	}

	public void updateTicket(Ticket ticket) {
		repo.save(ticket);
	}

	public void deleteTicketById(int id) {
		repo.deleteById(id);
	}

	public List<Ticket> showAllTicket() {
		return repo.findAll();
	}

	public Ticket getById(int id) {
		Optional<Ticket> opt = repo.findById(id);
		if (opt.isEmpty()) {
			return null;
		}
		return opt.get();
	}

	public List<Ticket> filter(String searchKey) {
		Ticket dummy = new Ticket();
		dummy.setTitle(searchKey);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains())
				.withIgnorePaths("id", "shortDescription", "content", "date");
		Example<Ticket> example = Example.of(dummy, exampleMatcher);

		return repo.findAll(example);
	}
}
