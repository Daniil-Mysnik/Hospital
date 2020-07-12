package net.thumbtack.school.hospital.controller;

import net.thumbtack.school.hospital.dto.request.CreatePatientRequest;
import net.thumbtack.school.hospital.dto.request.LoginRequest;
import net.thumbtack.school.hospital.dto.request.UpdatePatientRequest;
import net.thumbtack.school.hospital.dto.response.LoginResponse;
import net.thumbtack.school.hospital.dto.response.PatientResponse;
import net.thumbtack.school.hospital.exceptions.HospitalException;
import net.thumbtack.school.hospital.service.PatientService;
import net.thumbtack.school.hospital.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;
    private final SessionService sessionService;

    @Autowired
    public PatientController(PatientService patientService, SessionService sessionService) {
        this.patientService = patientService;
        this.sessionService = sessionService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PatientResponse create(@Valid @RequestBody CreatePatientRequest request, HttpServletResponse servletResponse) throws HospitalException {
        PatientResponse patientResponse = patientService.create(request);
        LoginResponse loginResponse = sessionService.create(new LoginRequest(request.getLogin(), request.getPassword()));
        servletResponse.addCookie(new Cookie("JAVASESSIONID", loginResponse.getSessionId()));
        return patientResponse;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PatientResponse get(@CookieValue("JAVASESSIONID") String sessionId, @PathVariable(value = "id") int patientId) throws HospitalException {
        return patientService.get(sessionId, patientId);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PatientResponse update(@CookieValue("JAVASESSIONID") String sessionId, @Valid @RequestBody UpdatePatientRequest request) throws HospitalException {
        return patientService.update(sessionId, request);
    }

}
