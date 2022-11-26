package com.revature.P1AndrewBrittain.Util.DTO;

public class ProcessTicketDTO {

    private int ticketId;

    private String isTicketApproved;

    public int getTicketId(){
        return ticketId;
    }

    public void setTicketId(int ticketIdDTO){
        this.ticketId = ticketId;
    }

    public String getIsTicketApproved(){
        return isTicketApproved;
    }

    public void setIsTicketApproved(String isTicketApproved){
        this.isTicketApproved= isTicketApproved;
    }
}
