package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.GardenDTORequest;
import com.senac.bunnyhut.dto.response.GardenDTOResponse;
import com.senac.bunnyhut.dto.response.GardenDTOUpdateResponse;
import com.senac.bunnyhut.entity.Garden;
import com.senac.bunnyhut.repository.GardenRepository;
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

    @Autowired
    private final ModelMapper modelMapper;

    public GardenService(GardenRepository gardenRepository,
                         ModelMapper modelMapper) {
        this.gardenRepository = gardenRepository;
        this.modelMapper = modelMapper;
    }

    public List<GardenDTOResponse> listGardens() {
        return gardenRepository.listGardens()
                .stream()
                .map(garden -> modelMapper.map(garden, GardenDTOResponse.class))
                .toList()
                ;
    }

    public GardenDTOResponse listarPorGardenId(Integer gardenId) {
        Garden garden = gardenRepository.obterGardenPeloId(gardenId);
        return (garden != null) ? modelMapper.map(garden, GardenDTOResponse.class) : null;
    }

    @Transactional
    public GardenDTOResponse criarGarden(GardenDTORequest gardenDTORequest) {
        Garden garden = modelMapper.map(gardenDTORequest, Garden.class);
        Garden GardenSave = this.gardenRepository.save(garden);
        return modelMapper.map(GardenSave, GardenDTOResponse.class);
    }

    @Transactional
    public GardenDTOResponse atualizarGarden(Integer gardenId, GardenDTORequest gardenDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Garden garden = gardenRepository.obterGardenPeloId(gardenId);
        //se encontra o registro a ser atualizado
        if (garden != null) {
            // atualiza dados do garden a partir do DTO
            modelMapper.map(gardenDTORequest, garden);
            // atualiza a categoria vinculada
            Garden tempResponse = gardenRepository.save(garden);
            return modelMapper.map(tempResponse, GardenDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza garden inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

//    @Transactional
//    public GardenDTOUpdateResponse atualizarStatusGarden(Integer gardenId, GardenDTORequest gardenDTOUpdateRequest) {
//        //antes de atualizar busca se existe o registro a ser atualizado
//        Garden garden = gardenRepository.obterGardenPeloId(gardenId);
//        //se encontra o registro a ser atualizado
//        if (garden != null) {
//            // atualiza o status do Garden a partir do DTO
//            garden.setStatus(gardenDTOUpdateRequest.getStatus());
//            Garden GardenSave = gardenRepository.save(garden);
//            return modelMapper.map(GardenSave, GardenDTOUpdateResponse.class);
//        } else {
//            // Error 400 caso tente atualiza garden inexistente.
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//    }

//    public void apagarGarden(Integer gardenId) {
//        gardenRepository.apagadoLogicoGarden(gardenId);
//    }
}

