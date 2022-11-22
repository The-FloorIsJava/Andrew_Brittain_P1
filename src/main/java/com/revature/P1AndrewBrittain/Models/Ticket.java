package com.revature.P1AndrewBrittain.Models;

public class Ticket {



    private double amount;
    private int ticketId;
    private String requestType;
    private boolean isTicketApproved;
    private String requester;

    @Override
    public String toString() {
        return "Ticket{" +
                "amount=" + amount +
                ", ticketId=" + ticketId +
                ", requestType='" + requestType + '\'' +
                ", isTicketApproved?" + isTicketApproved +
                ", requester='" + requester + '\'' +
                '}';
    }

    public Ticket(){}

    public Ticket(double amount, String requester, String requestType) {
        this.amount = amount;
        this.requester = requester;
        this.requestType = requestType;
    }
    public Ticket(double amount, String requester, String requestType, boolean isTicketApproved) {
        this.amount = amount;
        this.requester = requester;
        this.requestType = requestType;
    }
    private String ticketPending(String approval){
        if (!isTicketApproved){
            return "Denied";
        } else if (isTicketApproved){
            return "Approved";
        } else {
            return "Pending";
        }
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public boolean isTicketApproved() {
        return isTicketApproved;
    }

    public void setTicketApproved(boolean ticketApproved) {
        isTicketApproved = ticketApproved;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

}
