package com.kuklin.userservice.repositories;

import com.kuklin.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserEntityByTelegramId(Long telegramId);

}
