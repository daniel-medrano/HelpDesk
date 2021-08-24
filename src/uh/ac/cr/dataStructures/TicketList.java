package uh.ac.cr.dataStructures;

import uh.ac.cr.models.Supporter;
import uh.ac.cr.models.Ticket;
import uh.ac.cr.models.User;

public class TicketList {
    TicketNode head;
    TicketNode tail;
    int size;
    //Constructor
    public TicketList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    //Methods
    //Inserts a new ticket at the end of the list.
    public void insertTicketToTail(Ticket ticket) {
        TicketNode nodeToBeAdded = new TicketNode(ticket);

        if (isTicketListEmpty()) {
            //If it is the first node that will be added, then head and tail will have the same reference.
            head = nodeToBeAdded;
            tail = nodeToBeAdded;
        } else {
            //The pointer of tail won't be null anymore, now it will have the reference of the new node created.
            tail.next = nodeToBeAdded;
            //Because a new node has been added, tail is not the tail anymore and it has to be updated.
            tail = tail.next;
        }
        size++;
    }
    //Finds a ticket with its ID and returns it.
    public Ticket getTicketByID(int ticketID) {
        TicketNode nodeIndex = head;
        boolean found = false;

        while (nodeIndex != null) {
            //If the ticketID inserted matches the ticketID of one of the tickets in the nodes, then found is true.
            found = nodeIndex.ticket.getTicketID() == ticketID;

            if (found) {
                return nodeIndex.ticket;
            }
            //Iterates through all the nodes
            nodeIndex = nodeIndex.next;
        }
        return null;
    }
    //Finds a node with a ticket and removes it.
    public void removeTicket(Ticket ticket) {
        TicketNode prev = null;
        TicketNode current = head;
        boolean found = false;

        while (current != null && !found) {

            found = current.ticket == ticket;
            if (!found) {
                prev = current;
                current = current.next;
            }
        }

        if (current != null) {
            if (current == head) {
                //Verifies if it the ticket to delete is in the head.
                head = current.next;
            } else {
                //The node prev won't point to current anymore, but it will point to the node that current is pointing to. Therefore, current is deleted.
                prev.next = current.next;
                current.next = null;
            }
            size--;
        }
    }

    public String readHangingTickets() {
        TicketNode index = head;
        String hangingTickets = "";

        while (index != null) {
            if (!index.ticket.isFinished() && !index.ticket.isAvailable()) {
                hangingTickets = hangingTickets + index.ticket.getTicketInfo() + "\n";
            }
            index = index.next;
        }
        return hangingTickets;
    }

    public String readFinishedTickets() {
        TicketNode index = head;
        String finishedTickets = "";

        while (index != null) {
            if (index.ticket.isFinished()) {
                finishedTickets = finishedTickets + index.ticket.getTicketInfo() + "\n";
            }
            index = index.next;
        }
        return finishedTickets;
    }

    public String readAvailableTickets() {
        TicketNode index = head;
        String availableTickets = "";

        while (index != null) {
            if (index.ticket.isAvailable()) {
                availableTickets = availableTickets + index.ticket.getTicketInfo() + "\n";
            }
            index = index.next;
        }
        return availableTickets;
    }

    public String readUserUnfinishedTickets(User user) {
        TicketNode index = head;
        String activeTickets = "";

        while (index != null) {
            if (index.ticket.isFromUser(user) && !index.ticket.isFinished()) {
                //TODO - Replace it for the method getTicketInfo().
                activeTickets = activeTickets + index.ticket.getTicketInfoForUser() + "\n";
            }
            index = index.next;
        }
        return activeTickets;
    }

    public String readUserFinishedTickets(User user) {
        TicketNode index = head;
        String finishedTickets = "";

        while (index != null) {
            if (index.ticket.isFromUser(user) && index.ticket.isFinished()) {
                //TODO - Replace it for the method getTicketInfo().
                finishedTickets = finishedTickets + index.ticket.getTicketInfoForUser() + "\n";
            }
            index = index.next;
        }
        return finishedTickets;
    }

    public String readSupporterUnfinishedTickets(Supporter supporter) {
        TicketNode index = head;
        String activeTickets = "";

        while (index != null) {
            if (index.ticket.wasTakenBy(supporter) && !index.ticket.isFinished()) {
                //TODO - Replace it for the method getTicketInfo().
                activeTickets = activeTickets + index.ticket.getTicketInfoForSupporter() + "\n";
            }
            index = index.next;
        }
        return activeTickets;
    }
    public String readSupporterFinishedTickets(Supporter supporter) {
        TicketNode index = head;
        String finishedTickets = "";

        while (index != null) {
            if (index.ticket.wasTakenBy(supporter) && index.ticket.isFinished()) {
                //TODO - Replace it for the method getTicketInfo().
                finishedTickets = finishedTickets + index.ticket.getTicketInfoForSupporter() + "\n";
            }
            index = index.next;
        }
        return finishedTickets;
    }



    private boolean isTicketListEmpty() {
        return size == 0 || head == null;
    }

    //The class of the node.
     static class TicketNode {
        Ticket ticket;
        TicketNode next;
        //Constructor
        public TicketNode(Ticket ticket) {
            this.ticket = ticket;
            this.next = null;
        }
    }
}




