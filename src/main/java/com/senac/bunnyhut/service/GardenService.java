package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.GardenDTORequest;
import com.senac.bunnyhut.dto.response.GardenDTOResponse;
import com.senac.bunnyhut.entity.Garden;
import com.senac.bunnyhut.entity.User;
import com.senac.bunnyhut.repository.GardenRepository;
import com.senac.bunnyhut.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GardenService {

    private final GardenRepository gardenRepository;
    private final UserRepository userRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public GardenService(GardenRepository gardenRepository,
                         ModelMapper modelMapper,
                         UserRepository userRepository) {
        this.gardenRepository = gardenRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public List<GardenDTOResponse> listGardens() {
        return gardenRepository.listGardens()
                .stream()
                .map(garden -> modelMapper.map(garden, GardenDTOResponse.class))
                .toList();
    }

    public GardenDTOResponse getGardenById(Integer gardenId) {
        Garden garden = gardenRepository.getGardenById(gardenId);
        return (garden != null) ? modelMapper.map(garden, GardenDTOResponse.class) : null;
    }

    @Transactional
    public GardenDTOResponse createGarden(GardenDTORequest gardenDTORequest) {
        Garden garden = modelMapper.map(gardenDTORequest, Garden.class);

        User user = userRepository.getUserById(gardenDTORequest.getUserId());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found with ID: " + gardenDTORequest.getUserId());
        }
        garden.setUser(user);

        Garden gardenSave = this.gardenRepository.save(garden);
        return modelMapper.map(gardenSave, GardenDTOResponse.class);
    }

    @Transactional
    public GardenDTOResponse updateGarden(Integer gardenId, GardenDTORequest gardenDTORequest) {
        Garden garden = gardenRepository.getGardenById(gardenId);
        if (garden != null) {
            modelMapper.map(gardenDTORequest, garden);

            if (gardenDTORequest.getUserId() != null) {
                User user = userRepository.getUserById(gardenDTORequest.getUserId());
                if (user == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found for update with ID: " + gardenDTORequest.getUserId());
                }
                garden.setUser(user);
            }

            Garden tempResponse = gardenRepository.save(garden);
            return modelMapper.map(tempResponse, GardenDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}