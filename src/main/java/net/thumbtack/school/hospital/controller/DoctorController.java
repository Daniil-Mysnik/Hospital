package net.thumbtack.school.hospital.controller;

import net.thumbtack.school.hospital.dto.request.CreateDoctorRequest;
import net.thumbtack.school.hospital.dto.request.DeleteDoctorRequest;
import net.thumbtack.school.hospital.dto.request.UpdateScheduleRequest;
import net.thumbtack.school.hospital.dto.response.DoctorResponse;
import net.thumbtack.school.hospital.dto.response.DoctorWithScheduleResponse;
import net.thumbtack.school.hospital.dto.response.EmptyResponse;
import net.thumbtack.school.hospital.exceptions.HospitalException;
import net.thumbtack.school.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DoctorWithScheduleResponse create(@CookieValue("JAVASESSIONID") String sessionId,
                                             @Valid @RequestBody CreateDoctorRequest request) throws HospitalException {
        return doctorService.create(sessionId, request);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DoctorResponse get(@CookieValue("JAVASESSIONID") String sessionId, @PathVariable(value = "id") int doctorId,
                              @RequestParam(name = "schedule", required = false, defaultValue = "no") String schedule,
                              @RequestParam(name = "dateStart", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
                              @RequestParam(name = "dateEnd", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd) throws HospitalException {
        return doctorService.get(sessionId, doctorId, schedule, dateStart, dateEnd);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DoctorResponse> getBySpeciality(@CookieValue("JAVASESSIONID") String sessionId,
                                                @RequestParam(name = "schedule", required = false, defaultValue = "no") String schedule,
                                                @RequestParam(name = "speciality", required = false, defaultValue = "") String speciality,
                                                @RequestParam(name = "dateStart", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
                                                @RequestParam(name = "dateEnd", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd) throws HospitalException {
        return doctorService.getBySpeciality(sessionId, schedule, speciality, dateStart, dateEnd);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DoctorResponse updateSchedule(@CookieValue("JAVASESSIONID") String sessionId,
                                         @PathVariable(value = "id") int doctorId, @Valid @RequestBody UpdateScheduleRequest request) throws HospitalException {
        return doctorService.update(sessionId, doctorId, request);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmptyResponse delete(@CookieValue("JAVASESSIONID") String sessionId,
                                @PathVariable(value = "id") int doctorId, @Valid @RequestBody DeleteDoctorRequest request) throws HospitalException {
        return doctorService.delete(sessionId, doctorId, request.getDate());
    }

}
