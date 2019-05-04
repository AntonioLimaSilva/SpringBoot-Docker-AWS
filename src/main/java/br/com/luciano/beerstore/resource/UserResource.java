package br.com.luciano.beerstore.resource;

import br.com.luciano.beerstore.model.User;
import br.com.luciano.beerstore.repository.UserRepository;
import br.com.luciano.beerstore.resource.util.LocationUtil;
import br.com.luciano.beerstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADD_USER')")
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        User userSaved = this.userService.save(user);
        return ResponseEntity.created(LocationUtil.of(userSaved.getId())).body(userSaved);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_READ_USER')")
    public Page<User> findByName(@RequestParam(required = false, defaultValue = "%") String username, Pageable pageable) {
        return this.userRepository.findByUsernameContaining(username, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_READ_USER')")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        Optional<User> userOptional = this.userRepository.findById(id);

        return userOptional.isPresent() ? ResponseEntity.ok(userOptional.get()) :  ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_USER')")
    public ResponseEntity<User> editar(@RequestBody @Valid User user, @PathVariable Integer id) {
        return ResponseEntity.ok(userService.update(user, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_USER')")
    public void delete(@PathVariable Integer id) {
        this.userService.delete(id);
    }

}
