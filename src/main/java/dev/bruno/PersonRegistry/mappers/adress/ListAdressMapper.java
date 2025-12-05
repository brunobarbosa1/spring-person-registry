package dev.bruno.PersonRegistry.mappers.adress;

import dev.bruno.PersonRegistry.dtos.adress.ListAdressDTO;
import dev.bruno.PersonRegistry.dtos.person.ListPersonSimpleDTO;
import dev.bruno.PersonRegistry.model.AdressModel;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ListAdressMapper {

    public ListAdressDTO dtoToEntity(AdressModel entity) {
        List<ListPersonSimpleDTO> listPersonSimpleDTO = entity.getPerson()
                .stream()
                .map(p -> new ListPersonSimpleDTO(
                        p.getId(),
                        p.getName(),
                        p.getEmail()
                )).toList();

        return new ListAdressDTO(
                entity.getId(),
                entity.getAdress(),
                entity.getNumber(),
                entity.getNeighborhood(),
                listPersonSimpleDTO
        );
    }
}
