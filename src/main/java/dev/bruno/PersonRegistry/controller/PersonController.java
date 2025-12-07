package dev.bruno.PersonRegistry.controller;

import dev.bruno.PersonRegistry.dtos.person.CreatePersonDTO;
import dev.bruno.PersonRegistry.dtos.person.ListPersonDTO;
import dev.bruno.PersonRegistry.dtos.person.UpdatePersonDTO;
import dev.bruno.PersonRegistry.service.PersonService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<CreatePersonDTO> createPerson(@RequestBody CreatePersonDTO newPerson) {
        personService.createPerson(newPerson);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ListPersonDTO>> personGetAll() {
        return personService.personFindAll().isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(personService.personFindAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListPersonDTO> personGetById(@PathVariable Long id) {
        return personService.personFindById(id) == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(personService.personFindById(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePerson(@PathParam("id") Long id) {
        if(personService.personFindById(id) == null) {
            return ResponseEntity.notFound().build();
        }else{
         personService.personDeleteById(id);
         return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatePersonDTO> updatePerson(@PathVariable Long id, @RequestBody UpdatePersonDTO updatePersonDTO) {
        return personService.personFindById(id) == null ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(personService.updatePerson(id, updatePersonDTO));
    }
}
