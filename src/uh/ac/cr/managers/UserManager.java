package uh.ac.cr.managers;

import uh.ac.cr.dataStructures.TicketList;
import uh.ac.cr.dataStructures.UserList;
import uh.ac.cr.models.Supervisor;
import uh.ac.cr.models.Supporter;
import uh.ac.cr.models.User;

public class UserManager {

    UserList userList;

    public UserManager() {
        this.userList = new UserList();
    }

    public void createUser(int ID, String firstName, String secondName, String department, String username, String password) {
        userList.insertUserToTail(new User(ID, firstName, secondName, department, username, password));
    }

    public void createSupervisor(int ID, String firstName, String secondName, String department, String username, String password) {
        userList.insertUserToTail(new Supervisor(ID, firstName, secondName, department, username, password));

    }

    public void createSupporter(int ID, String firstName, String secondName, String department, String username, String password) {
        userList.insertUserToTail(new Supporter(ID, firstName, secondName, department, username, password));
    }

    public String readSupporters() {
        return userList.readSupporters();
    }

    public String readSupportersWithStatistics() {
        return userList.readSupportersWithStatistics();
    }

    public User getUser(int userID) {
        return userList.getUserByID(userID);
    }

    public void deleteUser() {

    }

    public User validateUser(String username, String password) {
        return userList.validateUser(username, password);
    }
    //TODO - Test if this works.
    public Supporter getSupporter(int supporterID) {
        return userList.getSupporterByID(supporterID);
    }

    public boolean existsUser(int id) {
        return getUser(id) != null;
    }

    public UserList getUserList() {
        return userList;
    }

}
