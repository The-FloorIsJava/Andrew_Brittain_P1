package com.revature.P1AndrewBrittain.Models;

public class Requests {

    private double amount;
    private int requestId;
    private String requestType;
    private boolean requestApproved;
    private String requester;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public boolean isRequestApproved() {
        return requestApproved;
    }

    public void setRequestApproved(boolean requestApproved) {
        this.requestApproved = requestApproved;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

}
