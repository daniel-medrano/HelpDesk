package uh.ac.cr.models;

import uh.ac.cr.managers.TicketManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class User {
    protected int id;
    protected String firstName;
    protected String secondName;
    protected String department;
    protected String username;
    protected String password;

    public User() {}

    public User(int id, String firstName, String secondName, String department, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.department = department;
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    //TODO - Change and create a menu class.

    public String getUserInfo() {
        return id + " - " + username + ". " + getFullName() + ".";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return firstName + " " + secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
