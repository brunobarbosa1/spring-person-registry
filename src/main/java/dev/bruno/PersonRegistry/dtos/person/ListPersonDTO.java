package dev.bruno.PersonRegistry.dtos.person;

import dev.bruno.PersonRegistry.model.AdressModel;

public record ListPersonDTO(
        Long id,
        String name,
        String email,
        AdressModel adress
) {
}
