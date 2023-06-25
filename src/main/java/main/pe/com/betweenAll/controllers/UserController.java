package main.pe.com.betweenAll.controllers;

import main.pe.com.betweenAll.entities.User;
import main.pe.com.betweenAll.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.listAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/users/name/{name}")
    public ResponseEntity<List<User>> getUsersByName(@PathVariable("name") String name) {
        List<User> users = userService.listByName(name);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.listById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/users/{idCategory}")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser=userService.save(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id){
        userService.delete(id, true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("idCategory") Long idCategory, @PathVariable("id") Long id) {
        User foundUser=userService.listById(id);
        if (user.getName()!=null) {
            foundUser.setName(user.getName());
        }
        if (user.getLastname()!=null) {
            foundUser.setLastname(user.getLastname());
        }
        if (user.getTypeDocument()!=null) {
            foundUser.setTypeDocument(user.getTypeDocument());
        }
        if (user.getNumberDocument()!=0) {
            foundUser.setNumberDocument(user.getNumberDocument());
        }
        if (user.getPhone()!=null) {
            foundUser.setPhone(user.getPhone());
        }
        if (user.getEmail()!=null) {
            foundUser.setEmail(user.getEmail());
        }
        if (user.getPassword()!=null) {
            foundUser.setPassword(user.getPassword());
        }
        if (user.getImage()!=null) {
            foundUser.setImage(user.getImage());
        }
        if (user.getCity()!=null) {
            foundUser.setCity(user.getCity());
        }

        User updateUser=userService.save(foundUser);
        return new ResponseEntity<User>(updateUser, HttpStatus.OK);
    }



}
