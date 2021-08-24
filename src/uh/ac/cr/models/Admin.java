package uh.ac.cr.models;

import uh.ac.cr.dataStructures.UserList;

public class Admin extends User {


    public Admin(int id, String username, String password) {
        super(id, username, password);
    }

    public void makeSupporter(int userID) {
    }
}
