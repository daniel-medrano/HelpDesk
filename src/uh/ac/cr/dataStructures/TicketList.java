package uh.ac.cr.dataStructures;

import uh.ac.cr.models.Ticket;

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

        while (nodeIndex != null && !found) {
            //If the ticketID inserted matches the ticketID of one of the tickets in the nodes, then found is true.
            found = nodeIndex.ticket.getTicketID() == ticketID;
            //Iterates through all the nodes
            nodeIndex = nodeIndex.next;
        }

        if (nodeIndex != null) {
            return nodeIndex.ticket;
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




