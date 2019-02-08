package br.com.luciano.beerstore.resource;

import br.com.luciano.beerstore.model.Group;
import br.com.luciano.beerstore.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupResource {

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping
    public List<Group> getGroups() {
        return this.groupRepository.findAll();
    }

}
