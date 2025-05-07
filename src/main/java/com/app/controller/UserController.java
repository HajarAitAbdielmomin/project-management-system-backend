package com.app.controller;

import com.app.dto.UserRelatedFeature.UserDTO;
import com.app.services.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;


    @PatchMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO, userId).isPresent() ?
                ResponseEntity.ok("User updated successfully") :
                ResponseEntity.badRequest().body("User not found");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.delete(userId) ?
                ResponseEntity.ok("User deleted successfully") :
                ResponseEntity.badRequest().body("User not found");
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        return userService.getUser(userId).isPresent() ?
                ResponseEntity.ok(userService.getUser(userId)) :
                ResponseEntity.badRequest().body("User not found");
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
