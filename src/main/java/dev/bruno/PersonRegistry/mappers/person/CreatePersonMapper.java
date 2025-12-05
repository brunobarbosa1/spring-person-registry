package dev.bruno.PersonRegistry.mappers.person;

import dev.bruno.PersonRegistry.dtos.person.CreatePersonDTO;
import dev.bruno.PersonRegistry.model.PersonModel;
import org.springframework.stereotype.Component;

@Component
public class CreatePersonMapper {

    public PersonModel entityToDto(CreatePersonDTO dto) {
        return new PersonModel(
                dto.name(),
                dto.email(),
                dto.adress()
        );
    }

    public CreatePersonDTO dtoToEntity(PersonModel entity) {
        return new CreatePersonDTO(
                entity.getName(),
                entity.getEmail(),
                entity.getAdress()
        );
    }
}
