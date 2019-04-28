package br.com.luciano.beerstore.security;

import br.com.luciano.beerstore.model.User;
import br.com.luciano.beerstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepository.findByEmailIgnoreCaseAndActiveTrue(email);

        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha inválidos"));

        Set<SimpleGrantedAuthority> authorities = userRepository.findAllRoles(user).stream().map(r -> new SimpleGrantedAuthority(r.toUpperCase())).collect(Collectors.toSet());

        return new UserSystem(user, authorities);
    }
}
