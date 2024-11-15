package com.user.service;

import com.user.Dto.PropertyUserDto;
import com.user.entity.PropertyUserEntity;

import java.util.List;

public interface UserService {
    PropertyUserDto addUsers(PropertyUserDto dto);

    void deleteUser(long propertyUserId);

    PropertyUserDto updateUser(long propertyUserId, PropertyUserDto dto);

    PropertyUserEntity getUsersById(long propertyUserId);
}
