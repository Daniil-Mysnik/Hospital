package net.thumbtack.school.hospital.controller;

import net.thumbtack.school.hospital.dto.response.UserResponse;
import net.thumbtack.school.hospital.exceptions.HospitalException;
import net.thumbtack.school.hospital.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final SessionService sessionService;

    @Autowired
    public AccountController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse get(@CookieValue("JAVASESSIONID") String sessionId) throws HospitalException {
        return sessionService.getInfo(sessionId);
    }

}
