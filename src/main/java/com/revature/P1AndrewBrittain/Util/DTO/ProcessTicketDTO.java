package com.revature.P1AndrewBrittain.Util.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;

public class ProcessTicketDTO {

    @JsonAlias(value = {"id",})
    private int ticketId;

    @JsonAlias(value = {"status",})
    private String isTicketApproved;

    public int getTicketId(){
        return ticketId;
    }

    public void setTicketId(int ticketId){
        this.ticketId = ticketId;
    }

    public String getIsTicketApproved(){
        return isTicketApproved;
    }

    public void setIsTicketApproved(String isTicketApproved){

        this.isTicketApproved= isTicketApproved;
    }
}
