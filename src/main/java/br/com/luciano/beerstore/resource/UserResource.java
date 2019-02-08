package br.com.luciano.beerstore.resource;

import br.com.luciano.beerstore.model.User;
import br.com.luciano.beerstore.repository.UserRepository;
import br.com.luciano.beerstore.resource.util.LocationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        User userSaved = this.userRepository.save(user);
        return ResponseEntity.created(LocationUtil.of(userSaved.getId())).body(userSaved);
    }

}
