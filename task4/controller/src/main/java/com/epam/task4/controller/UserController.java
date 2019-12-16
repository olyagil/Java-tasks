package com.epam.task4.controller;

import com.epam.task4.entity.User;
import com.epam.task4.exception.EntityNotFoundException;
import com.epam.task4.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(value = "/agency/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class UserController {

    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping(value = "/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User getUserById(@PathVariable("id") ObjectId id) {
        return service.findBy_id(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", id.toHexString()));
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || (authentication.principal == @userRepository.findById(#id).orElse(new com.epam.task4.entity.User()).login)")
    public void updateUserById(@PathVariable("id") ObjectId id, @Valid @RequestBody User user) {
        User oldUser = service.findBy_id(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", id.toHexString()));
        user.setPassword(oldUser.getPassword());
        service.save(user);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User createUser(@Valid @RequestBody User user) {
        user.set_id(ObjectId.get());
        service.save(user);
        return user;
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteUser(@PathVariable ObjectId id) {
        if (service.existsBy_id(id)) {
            service.deleteBy_id(id);
        } else {
            throw new EntityNotFoundException(User.class, "id", id.toHexString());
        }
    }

    @PutMapping("/{id}/change-password")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || (#oldPassword != null && !#oldPassword.isEmpty() && authentication.principal == @userRepository.findBy_id(#id).orElse(new com.epam.task4.entity.User()).login)")
    void changePassword(@PathVariable ObjectId id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 6) @RequestParam String newPassword) {
        User user = service.findBy_id(id).orElseThrow(() -> new EntityNotFoundException(User.class, "id", id.toHexString()));
        if (oldPassword == null || oldPassword.isEmpty() || oldPassword.equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            service.save(user);
        } else {
            throw new ConstraintViolationException("Passwords doesn't match", new HashSet<>());
        }
    }
}
