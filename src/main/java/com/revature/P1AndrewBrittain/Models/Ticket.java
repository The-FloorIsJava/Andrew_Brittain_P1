package com.revature.P1AndrewBrittain.Models;

public class Ticket {



    private double amount;

    private int ticketId;
    private String requestType;
    private String isTicketApproved;
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
        this.isTicketApproved = "Pending";
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getIsTicketApproved() {
        return isTicketApproved;
    }

    public void setIsTicketApproved(String isTicketApproved) {
        this.isTicketApproved = isTicketApproved;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

}
