package uh.ac.cr.models;

import java.util.Date;

public class Ticket {
    int ticketID;
    User requesterOfTicket;
    Supporter supporter;
    String problemDescription;
    Date date;
    Date deadlineExpected;
    boolean available;
    boolean finished;

    public Ticket(int ticketID, User requesterOfTicket, String problemDescription, Date date, Date deadlineExpected) {
        this.ticketID = ticketID;
        this.requesterOfTicket = requesterOfTicket;
        this.supporter = null;
        this.problemDescription = problemDescription;
        this.date = date;
        this.deadlineExpected = deadlineExpected;
        this.available = true;
        this.finished = false;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public User getRequesterOfTicket() {
        return requesterOfTicket;
    }

    public void setRequesterOfTicket(User requesterOfTicket) {
        this.requesterOfTicket = requesterOfTicket;
    }

    public void assignTicketToSupporter(Supporter supporter) {
        this.supporter = supporter;
        this.available = false;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDeadlineExpected() {
        return deadlineExpected;
    }

    public void setDeadlineExpected(Date deadlineExpected) {
        this.deadlineExpected = deadlineExpected;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void finishTicket() {
        setFinished(true);
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
