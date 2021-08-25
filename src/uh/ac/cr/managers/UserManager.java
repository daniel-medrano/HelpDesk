package uh.ac.cr.managers;

import uh.ac.cr.dataStructures.TicketList;
import uh.ac.cr.dataStructures.UserList;
import uh.ac.cr.models.Supervisor;
import uh.ac.cr.models.Supporter;
import uh.ac.cr.models.User;

public class UserManager {
    //Linked list for the users.
    UserList userList;
    //Constructor
    public UserManager() {
        this.userList = new UserList();
    }
    //Method that creates a user and inserts it at the end of the linked list.
    public void createUser(int ID, String firstName, String secondName, String department, String username, String password) {
        userList.insertUserToTail(new User(ID, firstName, secondName, department, username, password));
    }
    //Method that creates a supervisor and inserts it at the end of the linked list.
    public void createSupervisor(int ID, String firstName, String secondName, String department, String username, String password) {
        userList.insertUserToTail(new Supervisor(ID, firstName, secondName, department, username, password));
    }
    //Method that creates a supporter and inserts it at the end of the linked list.
    public void createSupporter(int ID, String firstName, String secondName, String department, String username, String password) {
        userList.insertUserToTail(new Supporter(ID, firstName, secondName, department, username, password));
    }
    //Method that returns a String of supporters, in order to show them on a menu.
    public String readSupporters() {
        return userList.readSupporters();
    }
    //Method that returns a String with a list of supporters and a couple of numbers about their "performance".
    public String readSupportersWithStatistics() {
        return userList.readSupportersWithStatistics();
    }
    //Method that returns a user using the ID.
    public User getUser(int userID) {
        return userList.getUserByID(userID);
    }
    //Method that validates if the username and password are correct.
    public User validateUser(String username, String password) {
        return userList.validateUser(username, password);
    }
    //Method that returns a supporter using the ID.
    public Supporter getSupporter(int supporterID) {
        return userList.getSupporterByID(supporterID);
    }
    //Method to point out whether or not a user exists.
    public boolean existsUser(int id) {
        return getUser(id) != null;
    }
}
