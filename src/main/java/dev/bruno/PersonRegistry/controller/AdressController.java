package dev.bruno.PersonRegistry.controller;

import dev.bruno.PersonRegistry.dtos.adress.CreateAdressDTO;
import dev.bruno.PersonRegistry.dtos.adress.ListAdressDTO;
import dev.bruno.PersonRegistry.dtos.adress.UpdateAdressDTO;
import dev.bruno.PersonRegistry.service.AdressService;
import jakarta.websocket.server.PathParam;
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

    @GetMapping("/all")
    public ResponseEntity<List<ListAdressDTO>> adressGetAll() {
        return adressService.adressGetAll().isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) :
                ResponseEntity.ok(adressService.adressGetAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListAdressDTO> adressGetById(Long id) {
        return adressService.adressById(id) == null ? ResponseEntity
                .status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.ok(adressService.adressById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<CreateAdressDTO> createAdress(@RequestBody CreateAdressDTO createAdressDTO) {
        adressService.createAdress(createAdressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAdress(@PathParam("id") Long id) {
        if(adressService.adressById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            adressService.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateAdressDTO> updatePerson(@PathVariable Long id, @RequestBody UpdateAdressDTO updateAdressDTO) {
        return adressService.adressById(id) == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.ok(adressService.alterAdress(id, updateAdressDTO));
    }
}
