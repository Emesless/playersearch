package ru.emesless.searchplayers.rest;

import ru.emesless.searchplayers.model.Contacts;
import ru.emesless.searchplayers.model.User;
import ru.emesless.searchplayers.service.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistrationRestController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        this.userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/registration/contacts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Contacts> saveUser(@RequestBody Contacts contacts) {
        User user = userService.getLast();
        contacts.setUser(user);
        HttpHeaders headers = new HttpHeaders();
        if (user == null) {
            return new ResponseEntity<Contacts>(HttpStatus.BAD_REQUEST);
        }
        this.userService.saveContacts(contacts);
        return new ResponseEntity<Contacts>(contacts, headers, HttpStatus.CREATED);
    }
}
/////// ДОБАВИТЬ ПРОВЕРКУ В БД НА СУЩЕСТВОАНИЕ ЮЗЕРА