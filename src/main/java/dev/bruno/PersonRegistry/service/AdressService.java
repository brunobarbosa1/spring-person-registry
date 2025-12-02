package dev.bruno.PersonRegistry.service;

import dev.bruno.PersonRegistry.dtos.AdressDTO;
import dev.bruno.PersonRegistry.mappers.AdressMapper;
import dev.bruno.PersonRegistry.model.AdressModel;
import dev.bruno.PersonRegistry.repositorys.AdressRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdressService {

    private final AdressRepository adressRepository;
    private final AdressMapper adressMapper;

    public AdressService(AdressRepository adressRepository, AdressMapper adressMapper) {
        this.adressRepository = adressRepository;
        this.adressMapper = adressMapper;
    }

    public List<AdressDTO> findAll(){
      return adressRepository.findAll().stream()
              .map(adressMapper::mapsDtoToEntity)
               .toList();
    }

    public AdressDTO findById(Long id){
        AdressModel adressModel = adressRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Adrress with id " + id + " not found"));
        return adressMapper.mapsDtoToEntity(adressModel);
    }

    public void createPerson(AdressDTO adressDTO){
        adressRepository.save(adressMapper.mapsEntityToDto(adressDTO));
    }

    public void deleteById(Long id){
        adressRepository.deleteById(id);
    }

    public AdressDTO alterPerson(Long id, AdressDTO adressDTO){
        AdressModel adress = adressRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Adress not found!"));
        AdressModel newAdress = AdressModel.builder()
                .id(adress.getId())
                .adress(adressDTO.getAdress() != null ? adressDTO.getAdress() : adress.getAdress())
                .number(adressDTO.getNumber() != 0 ? adressDTO.getNumber() : adress.getNumber())
                .neighborhood(adressDTO.getNeighborhood() != null ? adressDTO.getNeighborhood() : adress.getNeighborhood())
                .person(adressDTO.getPerson() != null ? adressDTO.getPerson() : adress.getPerson())
                .build();
        return adressMapper.mapsDtoToEntity(adressRepository.save(newAdress));
    }
}
