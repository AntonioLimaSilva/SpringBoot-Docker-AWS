package br.com.luciano.beerstore.service;

import br.com.luciano.beerstore.model.User;
import br.com.luciano.beerstore.repository.UserRepository;
import br.com.luciano.beerstore.service.exception.UserAlreadyExistException;
import br.com.luciano.beerstore.service.exception.UserNotExistException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        Optional<User> userOptional = this.userRepository.findByEmailIgnoreCase(user.getEmail());

        if(userOptional.isPresent()) {
            throw new UserAlreadyExistException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return this.userRepository.save(user);
    }

    public void delete(Integer id) {
        Optional<User> usuarioOptional = this.userRepository.findById(id);

        if(!usuarioOptional.isPresent()) {
            throw new UserNotExistException();
        }

        this.userRepository.deleteById(id);
    }

    public User update(User user, Integer id) {
        Optional<User> userOptional = this.userRepository.findById(id);

        if(!userOptional.isPresent()) {
            throw new UserNotExistException();
        }

        if(!userOptional.get().getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        User userSaved = userOptional.get();

        BeanUtils.copyProperties(user, userSaved, "id");

        return userRepository.save(userSaved);
    }
}
