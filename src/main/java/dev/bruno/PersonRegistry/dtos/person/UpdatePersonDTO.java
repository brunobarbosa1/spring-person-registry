package dev.bruno.PersonRegistry.dtos.person;

import dev.bruno.PersonRegistry.model.AdressModel;

public record UpdatePersonDTO(
        String name,
        AdressModel adressModel
) {
}
