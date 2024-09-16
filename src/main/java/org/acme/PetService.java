package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PetService {

    public List<PetEntity> getPets() {
        return PetEntity.listAll();
    }

    public PetEntity getPet(Long id) {
        return PetEntity.findById(id);
    }

    @Transactional
    public void createPet(String name, Integer age, String type, String gender) {
        var pet = new PetEntity();
        pet.name = name;
        pet.age = age;
        pet.type = type;
        pet.gender = gender;
        pet.persist();
    }

    @Transactional
    public void updatePet(Long id, Integer age, String type, String gender) {
        PetEntity petEntity = PetEntity.findById(id);
        petEntity.age = age;
        petEntity.type = type;
        petEntity.gender = gender;
    }

    @Transactional
    public void deletePet(Long id) {
        PetEntity.deleteById(id);
    }
}
