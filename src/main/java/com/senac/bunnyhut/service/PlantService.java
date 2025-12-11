package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.PlantDTORequest;
import com.senac.bunnyhut.dto.response.PlantDTOResponse;
import com.senac.bunnyhut.entity.Item;
import com.senac.bunnyhut.entity.Plant;
import com.senac.bunnyhut.repository.ItemRepository;
import com.senac.bunnyhut.repository.PlantRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlantService {

    private final PlantRepository plantRepository;
    private final ItemRepository itemRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public PlantService(PlantRepository plantRepository,
                        ModelMapper modelMapper,
                        ItemRepository itemRepository) {
        this.plantRepository = plantRepository;
        this.modelMapper = modelMapper;
        this.itemRepository = itemRepository;
    }

    public List<PlantDTOResponse> listPlants() {
        return plantRepository.listPlants()
                .stream()
                .map(plant -> modelMapper.map(plant, PlantDTOResponse.class))
                .toList();
    }

    public PlantDTOResponse getPlantById(Integer plantId) {
        Plant plant = plantRepository.getPlantById(plantId);
        return (plant != null) ? modelMapper.map(plant, PlantDTOResponse.class) : null;
    }

    @Transactional
    public PlantDTOResponse createPlant(PlantDTORequest plantDTORequest) {
        Plant plant = modelMapper.map(plantDTORequest, Plant.class);

        Item item = itemRepository.getItemById(plantDTORequest.getItemId());
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item not found with ID: " + plantDTORequest.getItemId());
        }
        plant.setItem(item);

        Plant plantSave = this.plantRepository.save(plant);
        return modelMapper.map(plantSave, PlantDTOResponse.class);
    }

    @Transactional
    public PlantDTOResponse updatePlant(Integer plantId, PlantDTORequest plantDTORequest) {
        Plant plant = plantRepository.getPlantById(plantId);
        if (plant != null) {
            modelMapper.map(plantDTORequest, plant);

            if (plantDTORequest.getItemId() != null) {
                Item item = itemRepository.getItemById(plantDTORequest.getItemId());
                if (item == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item not found for update with ID: " + plantDTORequest.getItemId());
                }
                plant.setItem(item);
            }

            Plant tempResponse = plantRepository.save(plant);
            return modelMapper.map(tempResponse, PlantDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}