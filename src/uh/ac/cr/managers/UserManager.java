package uh.ac.cr.managers;

import uh.ac.cr.dataStructures.UserList;
import uh.ac.cr.models.Supervisor;
import uh.ac.cr.models.Supporter;
import uh.ac.cr.models.User;

public class UserManager {

    UserList userList;

    public UserManager() {
        this.userList = new UserList();
    }

    public void createUser(int userID, String username, String password) {
        userList.insertUserToTail(new User(userID, username, password));
    }

    public void createSupervisor(int supervisorID, String username, String password) {
        userList.insertUserToTail(new Supervisor(supervisorID, username, password));

    }

    public void createSupporter(int supporterID, String username, String password) {
        userList.insertUserToTail(new Supporter(supporterID, username, password));
    }

    public void getUser() {

    }

    public void deleteUser() {

    }

    public User validateUser(String username, String password) {
        return userList.validateUser(username, password);
    }

}
