package net.thumbtack.school.hospital.dto.response;

public class DoctorWithoutScheduleResponse extends DoctorResponse {

    public DoctorWithoutScheduleResponse(int id, String firstName, String lastName, String patronymic, String speciality, String room) {
        super(id, firstName, lastName, patronymic, speciality, room);
    }

}
