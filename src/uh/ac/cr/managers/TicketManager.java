package uh.ac.cr.managers;

import uh.ac.cr.dataStructures.TicketList;
import uh.ac.cr.models.Supporter;
import uh.ac.cr.models.Ticket;
import uh.ac.cr.models.User;

import java.util.Date;

public class TicketManager {

    TicketList ticketList;

    public TicketManager() {
        this.ticketList = new TicketList();
    }

    public void createTicket(int ticketID, User requesterOfTicket, String problemDescription, Date date, Date deadlineExpected) {
        ticketList.insertTicketToTail(new Ticket(ticketID, requesterOfTicket, problemDescription, date, deadlineExpected));
    }
    public void deleteTicket(int ticketID) {
        ticketList.removeTicket(ticketList.getTicketByID(ticketID));
    }
    public void assignTicket(int ticketID, Supporter supporter) {
        ticketList.getTicketByID(ticketID).assignTicketToSupporter(supporter);
    }
    public void finishTicket(int ticketID) {
        ticketList.getTicketByID(ticketID).finishTicket();
    }
}
