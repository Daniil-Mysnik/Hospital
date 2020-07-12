package net.thumbtack.school.hospital.dto.response;

public class DoctorWithoutRoomResponse extends UserResponse {
    private String speciality;

    public DoctorWithoutRoomResponse() {
    }

    public DoctorWithoutRoomResponse(int id, String firstName, String lastName, String patronymic, String speciality) {
        super(id, firstName, lastName, patronymic);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

}
