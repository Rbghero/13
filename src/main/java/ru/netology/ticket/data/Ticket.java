package ru.netology.ticket.data;
public class Ticket implements Comparable<Ticket> {

    protected int id;
    protected int price;
    protected String from;
    protected String to;
    protected int duration;

    public Ticket(int id, int price, String from, String to, int duration) {
        this.id = id;
        this.price = price;
        this.from = from;
        this.to = to;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public int getDuration() {
        return duration;
    }
    @Override
    public int compareTo(Ticket o) {
        if (price < o.getPrice()) {
            return -1;
        } else if (price > o.getPrice()) {
            return 1;
        } else {
            return 0;
        }
    }
}