package dev.bruno.PersonRegistry.service;

import dev.bruno.PersonRegistry.dtos.PersonDTO;
import dev.bruno.PersonRegistry.mappers.PersonMapper;
import dev.bruno.PersonRegistry.model.PersonModel;
import dev.bruno.PersonRegistry.repositorys.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonDTO> findAll(){
        return personRepository.findAll().stream()
                .map(personMapper::mapsDtoToEntity)
                .toList();
    }

    public PersonDTO findById(Long id){
        PersonModel personModel = personRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Person with id " + id + " not found"));
        return personMapper.mapsDtoToEntity(personModel);
    }

    public void createPerson(PersonDTO personDTO){
        PersonModel personModel = personMapper.mapsEntityToDto(personDTO);
        personRepository.save(personModel);
    }

    public void deleteById(Long id){
        personRepository.deleteById(id);
    }

    public PersonDTO alterPerson(Long id, PersonDTO personDTO){
       PersonModel person = personRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Person not found!"));
       PersonModel newPerson = PersonModel.builder()
               .name(personDTO.getName() != null ? personDTO.getName() : person.getName())
               .email(personDTO.getEmail() != null ? personDTO.getEmail() : person.getEmail())
               .adress(personDTO.getAdress() != null ? personDTO.getAdress() : person.getAdress())
               .id(person.getId())
        .build();
        return personMapper.mapsDtoToEntity(personRepository.save(newPerson));
    }
}
