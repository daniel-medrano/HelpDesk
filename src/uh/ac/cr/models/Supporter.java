package uh.ac.cr.models;

import uh.ac.cr.managers.TicketManager;

public class Supporter extends User {

    public Supporter(int id, String firstName, String secondName, String department, String username, String password) {
        super(id, firstName, secondName, department, username, password);
    }

}
