package com.kuklin.user_service.repositories;

import com.kuklin.user_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserEntityByTelegramId(Long telegramId);

}
