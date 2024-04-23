package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Método para crear un nuevo usuario
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Método para obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Método para obtener un usuario por ID
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Método para actualizar un usuario por ID
    public void updateUser(Long userId, User updatedUser) {
        User existingUser = getUserById(userId);
        existingUser.setName(updatedUser.getName());
        existingUser.setProfile(updatedUser.getProfile());
        userRepository.save(existingUser);
    }

    // Método para eliminar un usuario por ID
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}



