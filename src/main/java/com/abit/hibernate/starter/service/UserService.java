package com.abit.hibernate.starter.service;

import com.abit.hibernate.starter.dao.UserRepository;
import com.abit.hibernate.starter.dto.UserCreateDto;
import com.abit.hibernate.starter.dto.UserReadDto;
import com.abit.hibernate.starter.dto.UserUpdateDto;
import com.abit.hibernate.starter.entity.User;
import com.abit.hibernate.starter.mapper.UserCreateMapper;
import com.abit.hibernate.starter.mapper.UserReadMapper;
import com.abit.hibernate.starter.mapper.UserUpdateMapper;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;
    private final UserUpdateMapper userUpdateMapper;

    public boolean delete(Integer id) {
        var maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(id));
        return maybeUser.isPresent();
    }

    public boolean update(UserUpdateDto userUpdateDto) {
        var maybeUser = userRepository.findById(userUpdateDto.id());
        var updatedUser = userUpdateMapper.mapFrom(userUpdateDto);
        maybeUser.ifPresent(user -> {
            userRepository.update(updatedUser);
        });

        return maybeUser.isPresent();
    }

    public Integer create(UserCreateDto userCreateDto) {
        // TODO validation
        var validationFactory = Validation.buildDefaultValidatorFactory();
        var validator = validationFactory.getValidator();
        var validationResult = validator.validate(userCreateDto);
        if (!validationResult.isEmpty()) {
            throw new ConstraintViolationException(validationResult);
        }
        var userEntity = userCreateMapper.mapFrom(userCreateDto);
        return userRepository.save(userEntity).getId();
    }

    public Optional<UserReadDto> findById(Integer id) {
        return userRepository.findById(id).map(userReadMapper::mapFrom);
    }
}