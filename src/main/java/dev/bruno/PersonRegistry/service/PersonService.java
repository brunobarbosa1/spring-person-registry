package dev.bruno.PersonRegistry.service;

import dev.bruno.PersonRegistry.model.PersonModel;
import dev.bruno.PersonRegistry.repositorys.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonModel> findAll(){
        return personRepository.findAll();
    }

    public PersonModel findById(Long id){
        return personRepository.findById(id).orElse(null);
    }

    public void createPerson(PersonModel personModel){
        personRepository.save(personModel);
    }

    public void deleteById(Long id){
        personRepository.deleteById(id);
    }

    public PersonModel alterPerson(Long id, PersonModel personModel){
       PersonModel person = personRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Person not found!"));

       PersonModel newPerson = PersonModel.builder()
               .name(personModel.getName() != null ? personModel.getName() : person.getName())
               .email(personModel.getEmail() != null ? personModel.getEmail() : person.getEmail())
               .id(person.getId())
        .build();
        return personRepository.save(newPerson);
    }
}
