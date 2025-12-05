package dev.bruno.PersonRegistry.dtos.adress;

public record CreateAdressDTO(
        String adress,
        short number,
        String neighborhood
) {
}
