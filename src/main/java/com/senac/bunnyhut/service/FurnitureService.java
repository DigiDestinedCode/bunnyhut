package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.FurnitureDTORequest;
import com.senac.bunnyhut.dto.response.FurnitureDTOResponse;
import com.senac.bunnyhut.entity.Furniture;
import com.senac.bunnyhut.entity.Item;
import com.senac.bunnyhut.repository.FurnitureRepository;
import com.senac.bunnyhut.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FurnitureService {

    private final FurnitureRepository furnitureRepository;
    private final ItemRepository itemRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public FurnitureService(FurnitureRepository furnitureRepository,
                            ModelMapper modelMapper,
                            ItemRepository itemRepository) {
        this.furnitureRepository = furnitureRepository;
        this.modelMapper = modelMapper;
        this.itemRepository = itemRepository;
    }

    public List<FurnitureDTOResponse> listFurnitures() {
        return furnitureRepository.listFurnitures()
                .stream()
                .map(furniture -> modelMapper.map(furniture, FurnitureDTOResponse.class))
                .toList();
    }

    public FurnitureDTOResponse getFurnitureById(Integer furnitureId) {
        Furniture furniture = furnitureRepository.getFurnitureById(furnitureId);
        return (furniture != null) ? modelMapper.map(furniture, FurnitureDTOResponse.class) : null;
    }

    @Transactional
    public FurnitureDTOResponse createFurniture(FurnitureDTORequest furnitureDTORequest) {
        Furniture furniture = modelMapper.map(furnitureDTORequest, Furniture.class);

        Item item = itemRepository.getItemById(furnitureDTORequest.getItemId());
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item not found with ID: " + furnitureDTORequest.getItemId());
        }
        furniture.setItem(item);

        Furniture furnitureSave = this.furnitureRepository.save(furniture);
        return modelMapper.map(furnitureSave, FurnitureDTOResponse.class);
    }

    @Transactional
    public FurnitureDTOResponse updateFurniture(Integer furnitureId, FurnitureDTORequest furnitureDTORequest) {
        Furniture furniture = furnitureRepository.getFurnitureById(furnitureId);
        if (furniture != null) {
            modelMapper.map(furnitureDTORequest, furniture);

            if (furnitureDTORequest.getItemId() != null) {
                Item item = itemRepository.getItemById(furnitureDTORequest.getItemId());
                if (item == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item not found for update with ID: " + furnitureDTORequest.getItemId());
                }
                furniture.setItem(item);
            }

            Furniture tempResponse = furnitureRepository.save(furniture);
            return modelMapper.map(tempResponse, FurnitureDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}