package com.github.ndytar_s.capacity_test_project.services;

import com.github.ndytar.capacity.capacityModel.CapacityUser;
import com.github.ndytar.capacity.services.CapacityUserService;
import com.github.ndytar_s.capacity_test_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements CapacityUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<CapacityUser> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(u -> new CapacityUser(
                        u.getUsername(),
                        u.getPassword(),
                        u.getRole()
                ));
    }
}