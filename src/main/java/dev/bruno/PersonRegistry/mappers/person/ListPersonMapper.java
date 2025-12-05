package dev.bruno.PersonRegistry.mappers.person;

import dev.bruno.PersonRegistry.dtos.person.ListPersonDTO;
import dev.bruno.PersonRegistry.model.PersonModel;
import org.springframework.stereotype.Component;

@Component
public class ListPersonMapper {

    public PersonModel entityToDto(ListPersonDTO dto) {
        return new PersonModel(
                dto.id(),
                dto.name(),
                dto.email(),
                dto.adress()
        );
    }

    public ListPersonDTO dtoToEntity(PersonModel entity) {
        return new ListPersonDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getAdress()
        );
    }
}
