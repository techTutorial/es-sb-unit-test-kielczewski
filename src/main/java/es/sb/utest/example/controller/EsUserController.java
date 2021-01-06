package es.sb.utest.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import es.sb.utest.example.domain.EsUserEntity;
import es.sb.utest.example.service.EsUserService;
import es.sb.utest.example.service.exception.EsUserAlreadyExistsException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EsUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsUserController.class);
    
    @Autowired
    private EsUserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public EsUserEntity createUser(@RequestBody @Valid final EsUserEntity user) {
        LOGGER.debug("Received request to create the {}", user);
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<EsUserEntity> listUsers() {
        LOGGER.debug("Received request to list all users");
        return userService.getAllUsers();
    }

    // Shift into exception file > Pending
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(EsUserAlreadyExistsException ex) {
        return ex.getMessage();
    }

}
