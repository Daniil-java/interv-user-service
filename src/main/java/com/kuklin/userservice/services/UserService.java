package com.kuklin.userservice.services;

import com.kuklin.sharedlibrary.BalanceSubtractRequest;
import com.kuklin.sharedlibrary.UserDto;
import com.kuklin.sharedlibrary.exceptions.ErrorResponseException;
import com.kuklin.sharedlibrary.exceptions.ErrorStatus;
import com.kuklin.sharedlibrary.exceptions.ServiceOrigin;
import com.kuklin.userservice.entities.User;
import com.kuklin.userservice.models.UserMapper;
import com.kuklin.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Transactional
    public UserDto subtractBalance(Long userId, BalanceSubtractRequest subtractTokens) {
        User user = userRepository.findByIdForUpdate(userId)
                .orElseThrow(() -> new ErrorResponseException(ErrorStatus.USER_NOT_FOUND, ServiceOrigin.USER_SERVICE));

        return mapper.toDto(userRepository.save(
                user.setBalance(user.getBalance().subtract(subtractTokens.getAmount())))
        );
    }

    public UserDto createUser(UserDto userDto) {
        BigDecimal initialBalance = BigDecimal.ZERO;
        if (userDto.getId() != null) {
            throw new ErrorResponseException(ErrorStatus.USER_CREATION_ERROR, ServiceOrigin.USER_SERVICE);
        }
        return mapper.toDto(
                userRepository.save(mapper.toEntity(userDto).setBalance(initialBalance))
        );
    }

    public UserDto getUserById(Long userId) {
        return mapper.toDto(userRepository.findById(userId)
                .orElseThrow(() -> new ErrorResponseException(ErrorStatus.USER_NOT_FOUND, ServiceOrigin.USER_SERVICE))
        );
    }

    public UserDto updateUser(UserDto userDto) {
        if (userDto.getId() == null) {
            throw new ErrorResponseException(ErrorStatus.USER_UPDATE_ERROR, ServiceOrigin.USER_SERVICE);
        }
        return mapper.toDto(userRepository.save(mapper.toEntity(userDto)));
    }

    public UserDto setJobTitle(Long userId, String jobTitle) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ErrorResponseException(ErrorStatus.USER_NOT_FOUND, ServiceOrigin.USER_SERVICE));
        user.setJobTitle(jobTitle);
        return mapper.toDto(userRepository.save(user));
    }

    public UserDto setProperties(Long userId, String properties) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ErrorResponseException(ErrorStatus.USER_NOT_FOUND, ServiceOrigin.USER_SERVICE));
        user.setProperties(properties);
        return mapper.toDto(userRepository.save(user));
    }
}
