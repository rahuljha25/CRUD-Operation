package com.user.controller;

import com.user.Dto.PropertyUserDto;
import com.user.entity.PropertyUserEntity;
import com.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class PropertyUserController {

    private UserService userService;


    public PropertyUserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/addProperty")
    public ResponseEntity<?> addUsers(
            @Valid @RequestBody PropertyUserDto dto,
            BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }

        PropertyUserDto propertyUserDto = userService.addUsers(dto);
        return new ResponseEntity<>(propertyUserDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam long propertyUserId){
        userService.deleteUser(propertyUserId);
        return new ResponseEntity<>("Record is deleted",HttpStatus.OK);
    }

    @PutMapping("/{propertyUserId}")
    public ResponseEntity<PropertyUserDto> updateUser(@PathVariable long propertyUserId,
                                                      @RequestBody PropertyUserDto dto){
        PropertyUserDto propertyUserDto = userService.updateUser(propertyUserId, dto);
        return new ResponseEntity<>(propertyUserDto,HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<PropertyUserEntity> getUsersById(@RequestParam long propertyUserId){
        PropertyUserEntity usersById = userService.getUsersById(propertyUserId);
        return new ResponseEntity<>(usersById,HttpStatus.OK);
    }
}
