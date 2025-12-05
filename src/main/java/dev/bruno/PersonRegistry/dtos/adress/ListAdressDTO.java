package dev.bruno.PersonRegistry.dtos.adress;

import dev.bruno.PersonRegistry.dtos.person.ListPersonSimpleDTO;
import java.util.List;

public record ListAdressDTO(
        Long id,
        String adress,
        short number,
        String neighborhood,
        List<ListPersonSimpleDTO> person
) {}
