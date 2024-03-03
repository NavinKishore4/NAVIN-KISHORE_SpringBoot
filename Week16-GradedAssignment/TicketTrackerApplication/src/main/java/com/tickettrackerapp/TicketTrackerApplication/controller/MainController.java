package com.tickettrackerapp.TicketTrackerApplication.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tickettrackerapp.TicketTrackerApplication.model.Ticket;
import com.tickettrackerapp.TicketTrackerApplication.service.TicketService;

@Controller
public class MainController {

	@Autowired
	TicketService ticketService;

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/new-ticket-form")
	public String newTicketForm() {
		return "new-ticket-form";
	}

	@RequestMapping("/show-all")
	public String showAll(Model data) {
		List<Ticket> t1 = ticketService.showAllTicket();
		data.addAttribute("tickets", t1);

		return "show-all";
	}

	@PostMapping("/add-ticket")
	public String addTicket(@RequestParam String title, @RequestParam String shortdescription,
			@RequestParam String content, Model data) {
		Ticket t1 = new Ticket(0, title, shortdescription, content, LocalDate.now());
		ticketService.addTicket(t1);

		List<Ticket> t = ticketService.showAllTicket();
		data.addAttribute("tickets", t);

		return "added";

	}

	@GetMapping("/delete")
	public String deleteTicket(@RequestParam int id, Model data) {
		ticketService.deleteTicketById(id);

		List<Ticket> t1 = ticketService.showAllTicket();
		data.addAttribute("tickets", t1);

		return "show-all";
	}

	@GetMapping("/edit")
	public String editTicket(@RequestParam int id, Model data) {
		Ticket t1 = ticketService.getById(id);
		data.addAttribute("ticket", t1);

		return "edit-form";
	}

	@PostMapping("/edit-ticket")
	public String editSave(@RequestParam int id, @RequestParam String title, @RequestParam String shortdescription,
			@RequestParam String content, Model data) {
		Ticket t = new Ticket(id, title, shortdescription, content, LocalDate.now());
		ticketService.updateTicket(t);

		List<Ticket> t1 = ticketService.showAllTicket();
		data.addAttribute("tickets", t1);

		return "show-all";
	}

	@GetMapping("/view")
	public String viewPage(@RequestParam int id, Model data) {
		Ticket t1 = ticketService.getById(id);
		data.addAttribute("ticket", t1);
		return "view-page";
	}

	@PostMapping("/searchsum")
	public String search(@RequestParam String search, Model data) {
		List<Ticket> tickets = ticketService.filter(search);
		data.addAttribute("tickets", tickets);
		return "show-all";
	}
}