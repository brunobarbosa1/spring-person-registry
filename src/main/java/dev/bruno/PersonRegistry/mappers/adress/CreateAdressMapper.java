package dev.bruno.PersonRegistry.mappers.adress;

import dev.bruno.PersonRegistry.dtos.adress.CreateAdressDTO;
import dev.bruno.PersonRegistry.model.AdressModel;
import org.springframework.stereotype.Component;

@Component
public class CreateAdressMapper {

    public AdressModel entityToDto(CreateAdressDTO createAdressDTO) {
        return new AdressModel(
                createAdressDTO.adress(),
                createAdressDTO.number(),
                createAdressDTO.neighborhood()
        );
    }

    public CreateAdressDTO dtoToEntity(AdressModel adressModel) {
        return new CreateAdressDTO(
                adressModel.getAdress(),
                adressModel.getNumber(),
                adressModel.getNeighborhood()
        );
    }
}
