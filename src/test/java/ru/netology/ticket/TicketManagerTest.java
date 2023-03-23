package ru.netology.ticket;

import org.junit.jupiter.api.Assertions;
import ru.netology.ticket.data.Ticket;
import ru.netology.ticket.manager.TicketManager;
import ru.netology.ticket.repository.TicketRepository;
import org.junit.jupiter.api.Test;


public class TicketManagerTest {

    Ticket ticket1 = new Ticket(1, 1_299, "VKO", "KZN", 97);
    Ticket ticket2 = new Ticket(2, 2_199, "VKO", "KZN", 99);
    Ticket ticket3 = new Ticket(3, 9_249, "LED", "IKT", 380);
    Ticket ticket4 = new Ticket(4, 11_494, "IKT", "LED", 390);
    Ticket ticket5 = new Ticket(5, 2_432, "VKO", "KZN", 130);

    @Test
    public void sortTickets() {
        TicketRepository repo = new TicketRepository();
        TicketManager manager = new TicketManager(repo);


        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = {ticket1, ticket2, ticket5};
        Ticket[] actual = manager.findAll("VKO", "KZN");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void singleTicketSearch() {
        TicketRepository repo = new TicketRepository();
        TicketManager manager = new TicketManager(repo);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = {ticket4};
        Ticket[] actual = manager.findAll("IKT", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByWhenItemNotFound() {
        TicketRepository repo = new TicketRepository();
        TicketManager manager = new TicketManager(repo);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = new Ticket[0];
        Ticket[] actual = manager.findAll("FR", "TR");

        Assertions.assertArrayEquals(expected, actual);
    }
}