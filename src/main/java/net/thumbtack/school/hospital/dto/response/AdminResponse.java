package net.thumbtack.school.hospital.dto.response;

public class AdminResponse extends UserResponse {
    private String position;

    public AdminResponse() {
    }

    public AdminResponse(int id, String firstName, String lastName, String patronymic, String position) {
        super(id, firstName, lastName, patronymic);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
