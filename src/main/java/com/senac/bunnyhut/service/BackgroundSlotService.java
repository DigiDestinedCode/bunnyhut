package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.BackgroundSlotDTORequest;
import com.senac.bunnyhut.dto.response.BackgroundSlotDTOResponse;
import com.senac.bunnyhut.entity.Background;
import com.senac.bunnyhut.entity.BackgroundSlot;
import com.senac.bunnyhut.entity.Furniture;
import com.senac.bunnyhut.repository.BackgroundRepository;
import com.senac.bunnyhut.repository.BackgroundSlotRepository;
import com.senac.bunnyhut.repository.FurnitureRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BackgroundSlotService {

    private final BackgroundSlotRepository backgroundSlotRepository;
    private final BackgroundRepository backgroundRepository;
    private final FurnitureRepository furnitureRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public BackgroundSlotService(BackgroundSlotRepository backgroundSlotRepository,
                                 ModelMapper modelMapper,
                                 BackgroundRepository backgroundRepository,
                                 FurnitureRepository furnitureRepository) {
        this.backgroundSlotRepository = backgroundSlotRepository;
        this.modelMapper = modelMapper;
        this.backgroundRepository = backgroundRepository;
        this.furnitureRepository = furnitureRepository;
    }

    public List<BackgroundSlotDTOResponse> listBackgroundSlots() {
        return backgroundSlotRepository.listBackgroundSlots()
                .stream()
                .map(backgroundslot -> modelMapper.map(backgroundslot, BackgroundSlotDTOResponse.class))
                .toList();
    }

    public BackgroundSlotDTOResponse getBackgroundSlotById(Integer backgroundSlotId) {
        BackgroundSlot backgroundSlot = backgroundSlotRepository.getBackgroundSlotById(backgroundSlotId);
        return (backgroundSlot != null) ? modelMapper.map(backgroundSlot, BackgroundSlotDTOResponse.class) : null;
    }

    @Transactional
    public BackgroundSlotDTOResponse createBackgroundSlot(BackgroundSlotDTORequest backgroundSlotDTORequest) {
        BackgroundSlot backgroundSlot = modelMapper.map(backgroundSlotDTORequest, BackgroundSlot.class);

        Background background = backgroundRepository.getBackgroundById(backgroundSlotDTORequest.getBackgroundId());
        if (background == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Background not found with ID: " + backgroundSlotDTORequest.getBackgroundId());
        }
        backgroundSlot.setBackground(background);

        Furniture furniture = furnitureRepository.getFurnitureById(backgroundSlotDTORequest.getFurnitureId());
        if (furniture == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Furniture not found with ID: " + backgroundSlotDTORequest.getFurnitureId());
        }
        backgroundSlot.setFurniture(furniture);

        BackgroundSlot backgroundSlotSave = this.backgroundSlotRepository.save(backgroundSlot);
        return modelMapper.map(backgroundSlotSave, BackgroundSlotDTOResponse.class);
    }

    @Transactional
    public BackgroundSlotDTOResponse updateBackgroundSlot(Integer backgroundSlotId, BackgroundSlotDTORequest backgroundSlotDTORequest) {
        BackgroundSlot backgroundSlot = backgroundSlotRepository.getBackgroundSlotById(backgroundSlotId);
        if (backgroundSlot != null) {
            modelMapper.map(backgroundSlotDTORequest, backgroundSlot);

            if (backgroundSlotDTORequest.getBackgroundId() != null) {
                Background background = backgroundRepository.getBackgroundById(backgroundSlotDTORequest.getBackgroundId());
                if (background == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Background not found for update with ID: " + backgroundSlotDTORequest.getBackgroundId());
                }
                backgroundSlot.setBackground(background);
            }

            if (backgroundSlotDTORequest.getFurnitureId() != null) {
                Furniture furniture = furnitureRepository.getFurnitureById(backgroundSlotDTORequest.getFurnitureId());
                if (furniture == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Furniture not found for update with ID: " + backgroundSlotDTORequest.getFurnitureId());
                }
                backgroundSlot.setFurniture(furniture);
            }

            BackgroundSlot tempResponse = backgroundSlotRepository.save(backgroundSlot);
            return modelMapper.map(tempResponse, BackgroundSlotDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}