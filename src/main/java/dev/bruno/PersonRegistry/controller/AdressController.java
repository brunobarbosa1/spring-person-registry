package dev.bruno.PersonRegistry.controller;

import dev.bruno.PersonRegistry.model.AdressModel;
import dev.bruno.PersonRegistry.model.PersonModel;
import dev.bruno.PersonRegistry.service.AdressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adress")
public class AdressController {

    private final AdressService adressService;

    public AdressController(AdressService adressService) {
        this.adressService = adressService;
    }

    @GetMapping
    public ResponseEntity<List<AdressModel>> findAll() {
        return adressService.findAll().isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(adressService.findAll());
    }

    @GetMapping
    public ResponseEntity<AdressModel> findById(@PathVariable Long id) {
        return adressService.findById(id) == null ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(adressService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PersonModel> createAdress(@RequestBody AdressModel adressModel) {
        adressService.createPerson(adressModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdress(@PathVariable Long id) {
        if(adressService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            adressService.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdressModel> updatePerson(@PathVariable Long id, @RequestBody AdressModel adressModel) {
        return adressService.findById(id) == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.ok(adressService.alterPerson(id, adressModel));
    }
}
