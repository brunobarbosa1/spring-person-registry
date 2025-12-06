package dev.bruno.PersonRegistry.service;

import dev.bruno.PersonRegistry.dtos.person.CreatePersonDTO;
import dev.bruno.PersonRegistry.dtos.person.ListPersonDTO;
import dev.bruno.PersonRegistry.dtos.person.UpdatePersonDTO;
import dev.bruno.PersonRegistry.mappers.person.CreatePersonMapper;
import dev.bruno.PersonRegistry.mappers.person.ListPersonMapper;
import dev.bruno.PersonRegistry.mappers.person.UpdatePersonMapper;
import dev.bruno.PersonRegistry.model.PersonModel;
import dev.bruno.PersonRegistry.repositorys.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final CreatePersonMapper createPersonMapper;
    private final ListPersonMapper listPersonMapper;
    private final UpdatePersonMapper updatePersonMapper;

    public PersonService(PersonRepository personRepository, CreatePersonMapper createPersonMapper, ListPersonMapper listPersonMapper, UpdatePersonMapper updatePersonMapper) {
        this.personRepository = personRepository;
        this.createPersonMapper = createPersonMapper;
        this.listPersonMapper = listPersonMapper;
        this.updatePersonMapper = updatePersonMapper;
    }

    public List<ListPersonDTO> personFindAll(){
        return personRepository.findAll().stream()
                .map(listPersonMapper::dtoToEntity)
                .toList();
    }

    public ListPersonDTO personFindById(Long id){
        PersonModel personModel = personRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Person with id " + id + " not found"));
        return listPersonMapper.dtoToEntity(personModel);
    }

    public void createPerson(CreatePersonDTO createPersonDTO){
        PersonModel personModel = createPersonMapper.entityToDto(createPersonDTO);
        personRepository.save(personModel);
    }

    public void personDeleteById(Long id){
        personRepository.deleteById(id);
    }


    public UpdatePersonDTO updatePerson(Long id, UpdatePersonDTO updatePersonDTO){
       PersonModel person = personRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Person not found!"));

       updatePersonMapper.merge(person, updatePersonDTO);
       person = personRepository.save(person);
       return updatePersonMapper.dtoToEntity(person);
    }
}