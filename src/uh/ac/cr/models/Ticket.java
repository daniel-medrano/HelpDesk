package uh.ac.cr.models;

import java.util.ArrayList;
import java.util.Date;

public class Ticket {
    int ticketID;
    User requesterOfTicket;
    Supporter supporter;
    String problemDescription;
    Date date;
    Date deadlineExpected;
    boolean available;
    boolean finished;
    ArrayList<String> comments;

    int descriptionWidth;

    public Ticket(int ticketID, User requesterOfTicket, String problemDescription, Date date, Date deadlineExpected) {
        this.ticketID = ticketID;
        this.requesterOfTicket = requesterOfTicket;
        this.supporter = null;
        this.problemDescription = problemDescription;
        this.date = date;
        this.deadlineExpected = deadlineExpected;
        this.available = true;
        this.finished = false;
        this.comments = new ArrayList<>();

        this.descriptionWidth = 25;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public User getRequesterOfTicket() {
        return requesterOfTicket;
    }

    public void setRequesterOfTicket(User requesterOfTicket) {
        this.requesterOfTicket = requesterOfTicket;
    }

    public void assignTicketToSupporter(Supporter supporter) {
        this.supporter = supporter;
        this.available = false;
    }

    public String getTicketInfo() {
        return ticketID + " - Soportista a cargo: " + getSupporterFullName() + ". Creador del tiquete: " + getUserFullName() + "\n\n" +
                getSpacing() + "Descripción del problema:" + "\n" +
                getShortProblemDescription() + "\n" +
                getSpacing() + "Comments(" + comments.size() + ")" + "\n";
    }
    //TODO
    public String getTicketDetailedInfo() {
        return ticketID + " - Soportista a cargo: " + getSupporterFullName() + ". Creador del tiquete: " + getUserFullName() + "\n" +
                getSpacing() + "Fecha de creación: " + date + "\n" +
                getSpacing() + "Fecha límite: " + deadlineExpected + "\n" +
                getSpacing() + "Disponible: " + available + ". Terminado: " + finished + "\n\n" +
                getSpacing() + "Descripción del problema:" + "\n" +
                getProblemDescription() + "\n" +
                getSpacing() + "Comments(" + comments.size() + ")" + "\n" +
                getComments();
    }

    public String getTicketInfoForUser() {
        return ticketID + " - Soportista a cargo: " + getSupporterFullName() + "\n\n" +
                getSpacing() + "Descripción del problema:" + "\n" +
                getShortProblemDescription() + "\n" +
                getSpacing() + "Comments(" + comments.size() + ")" + "\n";
    }

    public String getTicketInfoForSupporter() {
        return ticketID + " - Soportista a cargo: Yo. Creador del tiquete: " + getUserFullName() + "\n\n" +
                getSpacing() + "Descripción del problema:" + "\n" +
                getShortProblemDescription() + "\n" +
                getSpacing() + "Comments(" + comments.size() + ")" + "\n";
    }

    public boolean isFromUser(User user) {
        return requesterOfTicket == user;
    }

    public boolean wasTakenBy(Supporter supporter) {
        return this.supporter == supporter;
    }

    public String getShortProblemDescription() {
        if (problemDescription.length() >= 50) {
            return getTextBox(problemDescription.substring(0, 50).trim() + "...", descriptionWidth);
        } else {
            return getTextBox(problemDescription.trim(), descriptionWidth);
        }
    }
    public String getProblemDescription() {
        return getTextBox(problemDescription.trim(), descriptionWidth);
    }

    public void insertComment(String comment) {
        this.comments.add(comment);
    }

    public String getComments() {
        String comments = "";
        for (String comment : this.comments) {
            comments = comments + getTextBox(comment, descriptionWidth) + "\n";
        }
        return comments;
    }

    public String getTextBox(String value, int width) {
        String string = value;
        String problemDescription = "";
        int widthCounter = width;
        boolean ready = false;

        while (!ready) {
            if (string.length() > widthCounter && width > 0) {
                problemDescription = problemDescription + getSpacing() + string.substring(widthCounter - width, widthCounter) + "\n";
                widthCounter = widthCounter + width;
            } else {
                problemDescription = problemDescription + getSpacing() + string.substring(widthCounter - width);
                ready = true;
            }
        }
        return problemDescription;
    }
    public String getSpacing() {
        //The number of digits that ticketID has is obtained.
        int numOfDigits = String.valueOf(ticketID).length();
        String spacing = "   ";
        for (int i = 0; i < numOfDigits; i++) {
            spacing = spacing + " ";
        }
        return spacing;
    }

    public String getSupporterFullName() {
        if (supporter != null) {
            return supporter.getFirstName().charAt(0) + ". " + supporter.getSecondName();
        } else {
            return "Nadie";
        }
    }

    public String getUserFullName() {
        return requesterOfTicket.getFirstName().charAt(0) + ". " + requesterOfTicket.getSecondName();
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDeadlineExpected() {
        return deadlineExpected;
    }

    public void setDeadlineExpected(Date deadlineExpected) {
        this.deadlineExpected = deadlineExpected;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void finishTicket() {
        setFinished(true);
        setAvailable(false);
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
