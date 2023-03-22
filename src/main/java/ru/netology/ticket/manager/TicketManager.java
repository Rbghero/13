package ru.netology.ticket.manager;

import ru.netology.ticket.data.Ticket;
import ru.netology.ticket.exception.NotFoundException;
import ru.netology.ticket.repository.TicketRepository;


import java.util.Arrays;

public class TicketManager {

    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void add(Ticket ticket) {
        repository.add(ticket);
    }

    public void remove(int id) {
        repository.remove(id);
    }

    public Ticket[] findAll() {
        return repository.findAll();
    }

    public Ticket[] findAll(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, from, to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        if (result.length == 0) {
            throw new NotFoundException("Tickets from " + from + " to " + to + " were not found.");
        }
        Arrays.sort(result);
        return result;
    }

    public boolean matches(Ticket ticket, String from, String to) {
        if (ticket.getFrom() != from) {
            return false;
        }
        if (ticket.getTo() != to) {
            return false;
        } else {
            return true;
        }
    }
}