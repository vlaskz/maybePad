package com.vlaskz.maybepad.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class OwnerController {


    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Owner getOwnerById(@PathVariable Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Owner createOwner(@RequestBody Owner owner) {
        return ownerRepository.save(owner);
    }

//    @PutMapping("/{id}")
//    public Owner updateOwner(@PathVariable Long id, @RequestBody Owner updatedOwner) {
//        // Lógica de atualização
//    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerRepository.deleteById(id);
    }
}
