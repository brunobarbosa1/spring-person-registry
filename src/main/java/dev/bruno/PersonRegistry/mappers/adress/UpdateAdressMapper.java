package dev.bruno.PersonRegistry.mappers.adress;

import dev.bruno.PersonRegistry.dtos.adress.UpdateAdressDTO;
import dev.bruno.PersonRegistry.model.AdressModel;
import org.springframework.stereotype.Component;

@Component
public class UpdateAdressMapper {

    public AdressModel entityToDto(UpdateAdressDTO updateAdressDTO) {
        return new AdressModel(
                updateAdressDTO.adress(),
                updateAdressDTO.number(),
                updateAdressDTO.neighborhood()
        );
    }

    public UpdateAdressDTO dtoToEntity(AdressModel adressModel) {
        return new UpdateAdressDTO(
                adressModel.getAdress(),
                adressModel.getNumber(),
                adressModel.getNeighborhood()
        );
    }
}
