package ru.netology.ticket;
import ru.netology.ticket.data.Ticket;
import ru.netology.ticket.exception.NotFoundException;
import ru.netology.ticket.manager.TicketManager;
import ru.netology.ticket.repository.TicketRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicketManagerTest {
    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);

    Ticket ticket1 = new Ticket(1, 1_299, "VKO", "KZN", 97);
    Ticket ticket2 = new Ticket(2, 2_199, "VKO", "KZN", 97);
    Ticket ticket3 = new Ticket(3, 9_249, "LED", "IKT", 380);
    Ticket ticket4 = new Ticket(4, 11_494, "IKT", "LED", 390);
    Ticket ticket5 = new Ticket(5, 2_432, "LED", "KGD", 130);
    Ticket ticket6 = new Ticket(6, 4_843, "LED", "KGD", 145);
    Ticket ticket7 = new Ticket(7, 29_020, "LED", "IST", 250);
    Ticket ticket8 = new Ticket(8, 2_346, "LED", "KGD", 140);


    @Test
    public void ShouldThrowNoFoundExceptionIfNoResults() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        assertThrows(NotFoundException.class, () -> {
            manager.findAll("LED", "VKO");
        });
    }

    @Test
    public void ShouldFindOneMatchingResult() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        manager.findAll("LED", "IKT");

        Ticket[] expected = {ticket3};
        Ticket[] actual = manager.findAll("LED", "IKT");
        assertArrayEquals(expected, actual);

    }

    @Test
    public void ShouldFindSeveralMatchingResults() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        manager.findAll("VKO", "KZN");

        Ticket[] expected = {ticket1, ticket2};
        Ticket[] actual = manager.findAll("VKO", "KZN");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldFindSeveralMatchingResultsAndSortFromCheapToExpensive() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        manager.findAll("LED", "KGD");

        Ticket[] expected = {ticket8, ticket5, ticket6};
        Ticket[] actual = manager.findAll("LED", "KGD");

        assertArrayEquals(expected, actual);

    }

}