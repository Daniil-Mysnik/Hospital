package net.thumbtack.school.hospital.dto.response;

public class CountOfBusyAppointmentsResponse extends PatientResponse {
    int count;

    public CountOfBusyAppointmentsResponse() {
    }

    public CountOfBusyAppointmentsResponse(int id, String firstName, String lastName, String patronymic, String email, String address, String phone, int count) {
        super(id, firstName, lastName, patronymic, email, address, phone);
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
