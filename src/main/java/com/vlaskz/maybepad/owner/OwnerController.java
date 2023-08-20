package com.vlaskz.maybepad.owner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller("/api/owners")
@Slf4j(topic = "OwnerController")
public class OwnerController {


    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/list")
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }


    @PostMapping
    public Owner createOwner(@RequestBody Owner owner) {
        return ownerRepository.save(owner);
    }


    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerRepository.deleteById(id);
    }
}
