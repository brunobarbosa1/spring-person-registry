package dev.bruno.PersonRegistry.mappers.person;

import dev.bruno.PersonRegistry.dtos.person.ListPersonSimpleDTO;
import dev.bruno.PersonRegistry.model.PersonModel;
import org.springframework.stereotype.Component;

@Component
public class ListPersonSimpleMapper {

    public PersonModel entityToDto(ListPersonSimpleDTO dto) {
        return new PersonModel(
                dto.id(),
                dto.name(),
                dto.email()
        );
    }

    public ListPersonSimpleDTO dtoToEntity(PersonModel entity) {
        return new ListPersonSimpleDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail()
        );
    }
}
