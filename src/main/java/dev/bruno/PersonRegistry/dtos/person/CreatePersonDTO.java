package dev.bruno.PersonRegistry.dtos.person;

import dev.bruno.PersonRegistry.model.AdressModel;

public record CreatePersonDTO(
        String name,
        String email,
        AdressModel adress
) {}
