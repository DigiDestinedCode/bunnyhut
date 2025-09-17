package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.FurnitureDTORequest;
import com.senac.bunnyhut.dto.response.FurnitureDTOResponse;
import com.senac.bunnyhut.dto.response.FurnitureDTOUpdateResponse;
import com.senac.bunnyhut.entity.Furniture;
import com.senac.bunnyhut.repository.FurnitureRepository;
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

    @Autowired
    private final ModelMapper modelMapper;

    public FurnitureService(FurnitureRepository furnitureRepository,
                         ModelMapper modelMapper) {
        this.furnitureRepository = furnitureRepository;
        this.modelMapper = modelMapper;
    }

    public List<FurnitureDTOResponse> listFurnitures() {
        return furnitureRepository.listFurnitures()
                .stream()
                .map(furniture -> modelMapper.map(furniture, FurnitureDTOResponse.class))
                .toList()
                ;
    }

    public FurnitureDTOResponse listarPorFurnitureId(Integer furnitureId) {
        Furniture furniture = furnitureRepository.obterFurniturePeloId(furnitureId);
        return (furniture != null) ? modelMapper.map(furniture, FurnitureDTOResponse.class) : null;
    }

    @Transactional
    public FurnitureDTOResponse criarFurniture(FurnitureDTORequest furnitureDTORequest) {
        Furniture furniture = modelMapper.map(furnitureDTORequest, Furniture.class);
        Furniture FurnitureSave = this.furnitureRepository.save(furniture);
        return modelMapper.map(FurnitureSave, FurnitureDTOResponse.class);
    }

    @Transactional
    public FurnitureDTOResponse atualizarFurniture(Integer furnitureId, FurnitureDTORequest furnitureDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Furniture furniture = furnitureRepository.obterFurniturePeloId(furnitureId);
        //se encontra o registro a ser atualizado
        if (furniture != null) {
            // atualiza dados do furniture a partir do DTO
            modelMapper.map(furnitureDTORequest, furniture);
            // atualiza a categoria vinculada
            Furniture tempResponse = furnitureRepository.save(furniture);
            return modelMapper.map(tempResponse, FurnitureDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza furniture inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public FurnitureDTOUpdateResponse atualizarStatusFurniture(Integer furnitureId, FurnitureDTORequest furnitureDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Furniture furniture = furnitureRepository.obterFurniturePeloId(furnitureId);
        //se encontra o registro a ser atualizado
        if (furniture != null) {
            // atualiza o status do Furniture a partir do DTO
            furniture.setStatus(furnitureDTOUpdateRequest.getStatus());
            Furniture FurnitureSave = furnitureRepository.save(furniture);
            return modelMapper.map(FurnitureSave, FurnitureDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza furniture inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarFurniture(Integer furnitureId) {
        furnitureRepository.apagadoLogicoFurniture(furnitureId);
    }
}

