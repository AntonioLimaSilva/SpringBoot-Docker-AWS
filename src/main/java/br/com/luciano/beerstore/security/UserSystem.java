package br.com.luciano.beerstore.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserSystem extends User {

    private br.com.luciano.beerstore.model.User user;

    public UserSystem(br.com.luciano.beerstore.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }

    public br.com.luciano.beerstore.model.User getUser() {
        return user;
    }
}
