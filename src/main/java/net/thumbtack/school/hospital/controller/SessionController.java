package net.thumbtack.school.hospital.controller;

import net.thumbtack.school.hospital.dto.request.LoginRequest;
import net.thumbtack.school.hospital.dto.response.EmptyResponse;
import net.thumbtack.school.hospital.dto.response.LoginResponse;
import net.thumbtack.school.hospital.dto.response.UserResponse;
import net.thumbtack.school.hospital.exceptions.HospitalException;
import net.thumbtack.school.hospital.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse login(@Valid @RequestBody LoginRequest request, HttpServletResponse servletResponse) throws HospitalException {
        LoginResponse loginResponse = sessionService.create(request);
        servletResponse.addCookie(new Cookie("JAVASESSIONID", loginResponse.getSessionId()));
        return loginResponse.getUserResponse();
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyResponse logout(@CookieValue("JAVASESSIONID") String sessionId) throws HospitalException {
        return sessionService.delete(sessionId);
    }

}
