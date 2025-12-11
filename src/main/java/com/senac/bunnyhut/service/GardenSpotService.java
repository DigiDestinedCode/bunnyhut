package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.GardenSpotDTORequest;
import com.senac.bunnyhut.dto.response.GardenSpotDTOResponse;
import com.senac.bunnyhut.entity.Garden;
import com.senac.bunnyhut.entity.GardenSpot;
import com.senac.bunnyhut.entity.Plant;
import com.senac.bunnyhut.repository.GardenRepository;
import com.senac.bunnyhut.repository.GardenSpotRepository;
import com.senac.bunnyhut.repository.PlantRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GardenSpotService {

    private final GardenSpotRepository gardenSpotRepository;
    private final GardenRepository gardenRepository;
    private final PlantRepository plantRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public GardenSpotService(GardenSpotRepository gardenSpotRepository,
                             ModelMapper modelMapper,
                             GardenRepository gardenRepository,
                             PlantRepository plantRepository) {
        this.gardenSpotRepository = gardenSpotRepository;
        this.modelMapper = modelMapper;
        this.gardenRepository = gardenRepository;
        this.plantRepository = plantRepository;
    }

    public List<GardenSpotDTOResponse> listGardenSpots() {
        return gardenSpotRepository.listGardenSpots()
                .stream()
                .map(gardenSpot -> modelMapper.map(gardenSpot, GardenSpotDTOResponse.class))
                .toList();
    }

    public GardenSpotDTOResponse getGardenSpotById(Integer gardenSpotId) {
        GardenSpot gardenSpot = gardenSpotRepository.getGardenSpotById(gardenSpotId);
        return (gardenSpot != null) ? modelMapper.map(gardenSpot, GardenSpotDTOResponse.class) : null;
    }

    @Transactional
    public GardenSpotDTOResponse createGardenSpot(GardenSpotDTORequest gardenSpotDTORequest) {
        GardenSpot gardenSpot = modelMapper.map(gardenSpotDTORequest, GardenSpot.class);

        Garden garden = gardenRepository.getGardenById(gardenSpotDTORequest.getGardenId());
        if (garden == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Garden not found with ID: " + gardenSpotDTORequest.getGardenId());
        }
        gardenSpot.setGarden(garden);

        Plant plant = plantRepository.getPlantById(gardenSpotDTORequest.getPlantId());
        if (plant == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Plant not found with ID: " + gardenSpotDTORequest.getPlantId());
        }
        gardenSpot.setPlant(plant);

        GardenSpot gardenSpotSave = this.gardenSpotRepository.save(gardenSpot);
        return modelMapper.map(gardenSpotSave, GardenSpotDTOResponse.class);
    }

    @Transactional
    public GardenSpotDTOResponse updateGardenSpot(Integer gardenSpotId, GardenSpotDTORequest gardenSpotDTORequest) {
        GardenSpot gardenSpot = gardenSpotRepository.getGardenSpotById(gardenSpotId);
        if (gardenSpot != null) {
            modelMapper.map(gardenSpotDTORequest, gardenSpot);

            if (gardenSpotDTORequest.getGardenId() != null) {
                Garden garden = gardenRepository.getGardenById(gardenSpotDTORequest.getGardenId());
                if (garden == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Garden not found for update with ID: " + gardenSpotDTORequest.getGardenId());
                }
                gardenSpot.setGarden(garden);
            }

            if (gardenSpotDTORequest.getPlantId() != null) {
                Plant plant = plantRepository.getPlantById(gardenSpotDTORequest.getPlantId());
                if (plant == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Plant not found for update with ID: " + gardenSpotDTORequest.getPlantId());
                }
                gardenSpot.setPlant(plant);
            }

            GardenSpot tempResponse = gardenSpotRepository.save(gardenSpot);
            return modelMapper.map(tempResponse, GardenSpotDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}