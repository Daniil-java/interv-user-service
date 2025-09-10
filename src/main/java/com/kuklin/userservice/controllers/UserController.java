package com.kuklin.userservice.controllers;

import com.kuklin.userservice.models.BalanceSubtractRequest;
import com.kuklin.userservice.models.UserDto;
import com.kuklin.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PatchMapping("/{userId}/balance")
    public UserDto subtractBalance(
            @PathVariable Long userId,
            @RequestBody BalanceSubtractRequest balanceSubtractRequest
            ) {
        return userService.subtractBalance(userId, balanceSubtractRequest);
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/{userId}/job-title")
    public UserDto setJobTitle(@PathVariable Long userId,
                               @RequestBody String jobTitle) {
        return userService.setJobTitle(userId, jobTitle);
    }

    @PutMapping("/{userId}/properties")
    public UserDto setProperties(@PathVariable Long userId,
                               @RequestBody String properties) {
        return userService.setProperties(userId, properties);
    }



}
