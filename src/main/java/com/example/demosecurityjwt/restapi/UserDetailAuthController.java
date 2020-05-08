package com.example.demosecurityjwt.restapi;

import com.example.demosecurityjwt.entity.User;
import com.example.demosecurityjwt.exception.DemoSecurityAppException;
import com.example.demosecurityjwt.service.EcomUserService;
import com.example.demosecurityjwt.restapi.model.SignIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController(value="/user")
public class UserDetailAuthController {

    @Autowired
    EcomUserService ecomUserService;

    @RequestMapping(value = "/signin/all", method = RequestMethod.GET)
    public ResponseEntity<Iterable<?>> getAllUsers() {
        return new ResponseEntity<>(ecomUserService.findAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/signin/byid/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(ecomUserService.findUserById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@Valid @RequestBody User user) {
        try{
            user = ecomUserService.createUser(user);
            log.info(">>> User Id: {}", user.getUsId());
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e){
            throw new DemoSecurityAppException(" Error on signup controller", e.getCause());
        }

    }

    @RequestMapping(value = "/signin/byusername/{userName}", method = RequestMethod.GET)
    public ResponseEntity<?> signInByUserName(@Valid @PathVariable String userName) {
        User user = ecomUserService.findByUserName(userName);
        log.info(">>> User Id: {}", user.getUsId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@Valid @RequestBody SignIn signIn) {
        User user = new User();
        user.setUserName(signIn.getUserNameOrEmailId());
        user.setPassword(signIn.getPassword());
        user = ecomUserService.signIn(user);
        log.info(">>> User Id: {}", user.getUsId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //change firstName, lastName, phoneNumber and dob
    @RequestMapping(value = "/signin/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        user = ecomUserService.updateUser(user);
        log.info(">>> User Id: {}", user.getUsId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/signin/delete/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        ecomUserService.deleteUserById(userId);
        log.info(">>> User Id: {}", userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/signin/edit/password", method = RequestMethod.PUT)
    public ResponseEntity<?> changePassword(@RequestBody User user) {
        user = ecomUserService.changePassword(user);
        log.info(">>> User Id: {}", user.getUsId());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/signin/edit/usernameemailid", method = RequestMethod.PUT)
    public ResponseEntity<?> changeUserNameOrEmailId(@RequestBody User user) {
        user = ecomUserService.changeUserNameOrEmailId(user);
        log.info(">>> User Id: {}", user.getUsId());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
