package net.thumbtack.school.hospital.dto.response;

public class DoctorResponse extends DoctorWithoutRoomResponse {
    private String room;

    public DoctorResponse() {
    }

    public DoctorResponse(int id, String firstName, String lastName, String patronymic, String speciality, String room) {
        super(id, firstName, lastName, patronymic, speciality);
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
