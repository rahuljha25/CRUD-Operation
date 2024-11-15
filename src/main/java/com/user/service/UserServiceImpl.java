package com.user.service;

import com.user.Dto.PropertyUserDto;
import com.user.entity.PropertyUserEntity;
import com.user.repository.PropertyUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private PropertyUserRepository propertyUserRepository;

    public UserServiceImpl(PropertyUserRepository propertyUserRepository) {
        this.propertyUserRepository = propertyUserRepository;
    }

    @Override
    public PropertyUserDto addUsers(PropertyUserDto dto) {
        PropertyUserEntity entity=dtoToEntity(dto);

        //save the entity
        PropertyUserEntity save = propertyUserRepository.save(entity);
        PropertyUserDto propertyUserDto = entityToDto(save);
        return propertyUserDto;
    }

    @Override
    public void deleteUser(long propertyUserId) {
        propertyUserRepository.deleteById(propertyUserId);
    }


    @Override
    public PropertyUserDto updateUser(long propertyUserId, PropertyUserDto dto) {
        PropertyUserEntity propertyUserEntity=null;
        Optional<PropertyUserEntity> byId = propertyUserRepository.findById(propertyUserId);
        if (byId.isPresent()){
            propertyUserEntity = dtoToEntity(dto);
            propertyUserEntity.setId(propertyUserId);

            PropertyUserEntity save = propertyUserRepository.save(propertyUserEntity);
            PropertyUserDto propertyUserDto = entityToDto(save);
            return propertyUserDto;
        } else{
            throw new RuntimeException("user does not exist with id :"+propertyUserId);
        }
           }

    @Override
    public PropertyUserEntity getUsersById(long propertyUserId) {
        PropertyUserEntity propertyUserEntity = propertyUserRepository.findById(propertyUserId).orElseThrow(
                ()-> new RuntimeException("user does not exist with id :"+propertyUserId)
        );
       return propertyUserEntity;
    }

    //dtoToEntity
    PropertyUserEntity dtoToEntity(PropertyUserDto dto){
        PropertyUserEntity entity=new PropertyUserEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        return entity;
    }
    //entityToDto
    PropertyUserDto entityToDto(PropertyUserEntity en){
        PropertyUserDto dto1=new PropertyUserDto();
        dto1.setId(en.getId());
        dto1.setName(en.getName());
        dto1.setEmail(en.getEmail());
        dto1.setMobile(en.getMobile());
        return dto1;
    }
}
