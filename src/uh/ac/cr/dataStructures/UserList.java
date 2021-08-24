package uh.ac.cr.dataStructures;

import uh.ac.cr.models.Supporter;
import uh.ac.cr.models.Ticket;
import uh.ac.cr.models.User;

public class UserList {
    UserNode head;
    UserNode tail;
    int size;
    //Constructor
    public UserList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //Methods
    //Inserts a new user at the end of the list.
    public void insertUserToTail(User user) {
        UserNode nodeToBeAdded = new UserNode(user);

        if (isUserListEmpty()) {
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
    //Finds a user with its ID and returns it.
    public User getUserByID(int userID) {
        UserNode nodeIndex = head;
        boolean found;

        while (nodeIndex != null) {
            //If the userID inserted matches the ID of one of the users in the nodes, then found is true.
            found = nodeIndex.user.getId() == userID;
            if (found) {
                return nodeIndex.user;
            }
            //Iterates through all the nodes
            nodeIndex = nodeIndex.next;
        }
        return null;
    }

    public Supporter getSupporterByID(int supporterID) {
        UserNode nodeIndex = head;
        boolean found = false;

        while (nodeIndex != null) {
            //If the userID inserted matches the ID of one of the users in the nodes, then found is true.
            if (nodeIndex.user instanceof Supporter) {
                found = nodeIndex.user.getId() == supporterID;
            }
            if (found) {
                return (Supporter) nodeIndex.user;
            }
            //Iterates through all the nodes
            nodeIndex = nodeIndex.next;
        }
        return null;
    }

    public String readSupporters() {
        UserNode index = head;
        String supportersList = "";

        while (index != null) {
            if (index.user instanceof Supporter) {
                //TODO - Replace it for the method getTicketInfo().
                supportersList = supportersList + index.user.getUserInfo() + "\n";
            }
            index = index.next;
        }
        return supportersList;
    }

    public String readSupportersWithStatistics() {
        UserNode index = head;
        String supportersList = "";

        while (index != null) {
            if (index.user instanceof Supporter) {
                //TODO - Replace it for the method getTicketInfo().
                supportersList = supportersList + ((Supporter) index.user).getSupporterInfo() + "\n";
            }
            index = index.next;
        }
        return supportersList;
    }

    //Finds a user with its ID and returns it.
    public User validateUser(String username, String password) {
        UserNode nodeIndex = head;
        boolean found = false;

        while (nodeIndex != null) {
            //If the userID inserted matches the ID of one of the users in the nodes, then found is true.
            found = nodeIndex.user.getUsername().equals(username) && nodeIndex.user.getPassword().equals(password);

            if (found) {
                return nodeIndex.user;
            }
            //Iterates through all the nodes
            nodeIndex = nodeIndex.next;
        }
        return null;
    }

    private boolean isUserListEmpty() {
        return size == 0 || head == null;
    }

    //The class of the node.
    static class UserNode {
        User user;
        UserNode next;
        //Constructor
        public UserNode(User user) {
            this.user = user;
            this.next = null;
        }
    }
}
