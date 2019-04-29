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
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        User userSaved = this.userService.save(user);
        return ResponseEntity.created(LocationUtil.of(userSaved.getId())).body(userSaved);
    }

    @GetMapping
    @PreAuthorize("hasRole('WRITE_BEER')")
    public Page<User> findByName(@RequestParam(required = false, defaultValue = "%") String username, Pageable pageable) {
        return this.userRepository.findByUsernameContaining(username, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('WRITE_BEER')")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        Optional<User> userOptional = this.userRepository.findById(id);

        return userOptional.isPresent() ? ResponseEntity.ok(userOptional.get()) :  ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('EDIT_BEER')")
    public ResponseEntity<User> editar(@RequestBody @Valid User user, @PathVariable Integer id) {
        return ResponseEntity.ok(userService.update(user, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('DELETE_BEER')")
    public void delete(@PathVariable Integer id) {
        this.userService.delete(id);
    }

}
