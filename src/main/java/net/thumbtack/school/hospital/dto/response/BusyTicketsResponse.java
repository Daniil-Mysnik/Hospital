package net.thumbtack.school.hospital.dto.response;

import java.util.List;

public class BusyTicketsResponse extends PatientResponse {
    List<TicketResponse> busyTickets;

    public BusyTicketsResponse() {
    }

    public BusyTicketsResponse(int id, String firstName, String lastName, String patronymic, String email, String address, String phone, List<TicketResponse> busyTickets) {
        super(id, firstName, lastName, patronymic, email, address, phone);
        this.busyTickets = busyTickets;
    }

    public List<TicketResponse> getBusyTickets() {
        return busyTickets;
    }

    public void setBusyTickets(List<TicketResponse> busyTickets) {
        this.busyTickets = busyTickets;
    }

}
