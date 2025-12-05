package dev.bruno.PersonRegistry.dtos.adress;

public record UpdateAdressDTO(
        String adress,
        short number,
        String neighborhood
) {
}
