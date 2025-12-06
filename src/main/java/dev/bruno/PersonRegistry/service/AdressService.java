package dev.bruno.PersonRegistry.service;

import dev.bruno.PersonRegistry.dtos.adress.CreateAdressDTO;
import dev.bruno.PersonRegistry.dtos.adress.ListAdressDTO;
import dev.bruno.PersonRegistry.dtos.adress.UpdateAdressDTO;
import dev.bruno.PersonRegistry.mappers.adress.CreateAdressMapper;
import dev.bruno.PersonRegistry.mappers.adress.ListAdressMapper;
import dev.bruno.PersonRegistry.mappers.adress.UpdateAdressMapper;
import dev.bruno.PersonRegistry.model.AdressModel;
import dev.bruno.PersonRegistry.repositorys.AdressRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdressService {

    private final AdressRepository adressRepository;
    private final CreateAdressMapper createAdressMapper;
    private final ListAdressMapper listAdressMapper;
    private final UpdateAdressMapper updateAdressMapper;

    public AdressService(AdressRepository adressRepository, CreateAdressMapper createAdressMapper, ListAdressMapper listAdressMapper,  UpdateAdressMapper updateAdressMapper) {
        this.adressRepository = adressRepository;
        this.createAdressMapper = createAdressMapper;
        this.listAdressMapper = listAdressMapper;
        this.updateAdressMapper = updateAdressMapper;
    }

    public List<ListAdressDTO> adressGetAll(){
      return adressRepository.findAll().stream()
              .map(listAdressMapper::dtoToEntity)
               .toList();
    }

    public ListAdressDTO adressById(Long id){
        AdressModel adressModel = adressRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Adrress with id " + id + " not found"));
        return listAdressMapper.dtoToEntity(adressModel);
    }

    public void createAdress(CreateAdressDTO createAdressDTO){
        adressRepository.save(createAdressMapper.entityToDto(createAdressDTO));
    }

    public void deleteById(Long id){
        adressRepository.deleteById(id);
    }

    public UpdateAdressDTO alterAdress(Long id, UpdateAdressDTO updateAdressDTO){
        AdressModel adress = adressRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Adress not found!"));

        updateAdressMapper.merge(adress, updateAdressDTO);
        adress =  adressRepository.save(adress);
        return updateAdressMapper.dtoToEntity(adress);
    }
}
