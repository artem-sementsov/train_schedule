package ru.netcracker.train_schedule.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.netcracker.train_schedule.domain.User;

import ru.netcracker.train_schedule.repository.UserRepository;

import javax.inject.Inject;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Inject
    private UserRepository userRepository;

    public void deleteUserInformation(String login) {
        userRepository.findOneByLogin(login).ifPresent(u -> {
            userRepository.delete(u);
            log.debug("Deleted User: {}", u);
        });
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        User user = userRepository.findOne(id);
        return user;
    }

}
