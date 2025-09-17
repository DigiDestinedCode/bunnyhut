package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.PlantDTORequest;
import com.senac.bunnyhut.dto.response.PlantDTOResponse;
import com.senac.bunnyhut.repository.PlantRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public PlantService(PlantRepository plantRepository,
                         ModelMapper modelMapper) {
        this.plantRepository = plantRepository;
        this.modelMapper = modelMapper;
    }

    public List<PlantDTOResponse> listPlants() {
        return plantRepository.listPlants()
                .stream()
                .map(plant -> modelMapper.map(plant, PlantDTOResponse.class))
                .toList()
                ;
    }

    public PlantDTOResponse listarPorPlantId(Integer plantId) {
        Plant plant = plantRepository.obterPlantPeloId(plantId);
        return (plant != null) ? modelMapper.map(plant, PlantDTOResponse.class) : null;
    }

    @Transactional
    public PlantDTOResponse criarPlant(PlantDTORequest plantDTORequest) {
        Plant plant = modelMapper.map(plantDTORequest, Plant.class);
        Plant PlantSave = this.plantRepository.save(plant);
        return modelMapper.map(PlantSave, PlantDTOResponse.class);
    }

    @Transactional
    public PlantDTOResponse atualizarPlant(Integer plantId, PlantDTORequest plantDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Plant plant = plantRepository.obterPlantPeloId(plantId);
        //se encontra o registro a ser atualizado
        if (plant != null) {
            // atualiza dados do plant a partir do DTO
            modelMapper.map(plantDTORequest, plant);
            // atualiza a categoria vinculada
            Plant tempResponse = plantRepository.save(plant);
            return modelMapper.map(tempResponse, PlantDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza plant inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public PlantDTOUpdateResponse atualizarStatusPlant(Integer plantId, PlantDTORequest plantDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Plant plant = plantRepository.obterPlantPeloId(plantId);
        //se encontra o registro a ser atualizado
        if (plant != null) {
            // atualiza o status do Plant a partir do DTO
            plant.setStatus(plantDTOUpdateRequest.getStatus());
            Plant PlantSave = plantRepository.save(plant);
            return modelMapper.map(PlantSave, PlantDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza plant inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarPlant(Integer plantId) {
        plantRepository.apagadoLogicoPlant(plantId);
    }
}

