package dev.bruno.PersonRegistry.service;

import dev.bruno.PersonRegistry.model.AdressModel;
import dev.bruno.PersonRegistry.repositorys.AdressRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdressService {

    private final AdressRepository adressRepository;

    public AdressService(AdressRepository adressRepository) {
            this.adressRepository = adressRepository;
    }

    public List<AdressModel> findAll(){
        return adressRepository.findAll();
    }

    public AdressModel findById(Long id){
        return adressRepository.findById(id).orElse(null);
    }

    public void createPerson(AdressModel adressModel){
        adressRepository.save(adressModel);
    }

    public void deleteById(Long id){
        adressRepository.deleteById(id);
    }

    public AdressModel alterPerson(Long id, AdressModel adressModel){
        AdressModel adress = adressRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Adress not found!"));
        AdressModel newAdress = AdressModel.builder()
                .id(adress.getId())
                .adress(adressModel.getAdress() != null ? adressModel.getAdress() : adress.getAdress())
                .number(adressModel.getNumber() != 0 ? adressModel.getNumber() : adress.getNumber())
                .neighborhood(adressModel.getNeighborhood() != null ? adressModel.getNeighborhood() : adress.getNeighborhood())
                .build();
        return adressRepository.save(newAdress);
    }
}
