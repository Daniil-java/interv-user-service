package com.kuklin.user_service.services;

import com.kuklin.user_service.entities.User;
import com.kuklin.user_service.models.BalanceSubtractRequest;
import com.kuklin.user_service.models.UserDto;
import com.kuklin.user_service.models.UserMapper;
import com.kuklin.user_service.repositories.UserRepository;
import com.kuklin.user_service.sharedlibrary.exceptions.ErrorResponseException;
import com.kuklin.user_service.sharedlibrary.exceptions.ErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserDto subtractBalance(Long userId, BalanceSubtractRequest subtractTokens) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ErrorResponseException(ErrorStatus.USER_NOT_FOUND));

        return mapper.toDto(userRepository.save(
                user.setBalance(user.getBalance().subtract(subtractTokens.getAmount())))
        );
    }

    public UserDto createUser(UserDto userDto) {
        if (userDto.getId() != null) {
            throw new ErrorResponseException(ErrorStatus.USER_CREATION_ERROR);
        }
        return mapper.toDto(
                userRepository.save(mapper.toEntity(userDto).setBalance(BigDecimal.ONE))
        );
    }

    public UserDto getUserById(Long userId) {
        return mapper.toDto(userRepository.findById(userId)
                .orElseThrow(() -> new ErrorResponseException(ErrorStatus.USER_NOT_FOUND))
        );
    }

    public UserDto updateUser(UserDto userDto) {
        if (userDto.getId() == null) {
            throw new ErrorResponseException(ErrorStatus.USER_UPDATE_ERROR);
        }
        return mapper.toDto(userRepository.save(mapper.toEntity(userDto)));
    }

    public UserDto setJobTitle(Long userId, String jobTitle) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ErrorResponseException(ErrorStatus.USER_NOT_FOUND));
        user.setJobTitle(jobTitle);
        return mapper.toDto(userRepository.save(user));
    }

    public UserDto setProperties(Long userId, String properties) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ErrorResponseException(ErrorStatus.USER_NOT_FOUND));
        user.setProperties(properties);
        return mapper.toDto(userRepository.save(user));
    }
}
