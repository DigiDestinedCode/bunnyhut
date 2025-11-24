package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.GardenSpotDTORequest;
import com.senac.bunnyhut.dto.response.GardenSpotDTOResponse;
import com.senac.bunnyhut.entity.GardenSpot;
import com.senac.bunnyhut.repository.GardenSpotRepository;
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

    @Autowired
    private final ModelMapper modelMapper;

    public GardenSpotService(GardenSpotRepository gardenSpotRepository,
                             ModelMapper modelMapper) {
        this.gardenSpotRepository = gardenSpotRepository;
        this.modelMapper = modelMapper;
    }

    public List<GardenSpotDTOResponse> listGardenSpots() {
        return gardenSpotRepository.listGardenSpots()
                .stream()
                .map(gardenSpot -> modelMapper.map(gardenSpot, GardenSpotDTOResponse.class))
                .toList()
                ;
    }

    public GardenSpotDTOResponse listarPorGardenSpotId(Integer gardenSpotId) {
        GardenSpot gardenSpot = gardenSpotRepository.obterGardenSpotPeloId(gardenSpotId);
        return (gardenSpot != null) ? modelMapper.map(gardenSpot, GardenSpotDTOResponse.class) : null;
    }

    @Transactional
    public GardenSpotDTOResponse criarGardenSpot(GardenSpotDTORequest gardenSpotDTORequest) {
        GardenSpot gardenSpot = modelMapper.map(gardenSpotDTORequest, GardenSpot.class);
        GardenSpot GardenSpotSave = this.gardenSpotRepository.save(gardenSpot);
        return modelMapper.map(GardenSpotSave, GardenSpotDTOResponse.class);
    }

    @Transactional
    public GardenSpotDTOResponse atualizarGardenSpot(Integer gardenSpotId, GardenSpotDTORequest gardenSpotDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        GardenSpot gardenSpot = gardenSpotRepository.obterGardenSpotPeloId(gardenSpotId);
        //se encontra o registro a ser atualizado
        if (gardenSpot != null) {
            // atualiza dados do gardenSpot a partir do DTO
            modelMapper.map(gardenSpotDTORequest, gardenSpot);
            // atualiza a categoria vinculada
            GardenSpot tempResponse = gardenSpotRepository.save(gardenSpot);
            return modelMapper.map(tempResponse, GardenSpotDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza gardenSpot inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

//    @Transactional
//    public GardenSpotDTOUpdateResponse atualizarStatusGardenSpot(Integer gardenSpotId, GardenSpotDTORequest gardenSpotDTOUpdateRequest) {
//        //antes de atualizar busca se existe o registro a ser atualizado
//        GardenSpot gardenSpot = gardenSpotRepository.obterGardenSpotPeloId(gardenSpotId);
//        //se encontra o registro a ser atualizado
//        if (gardenSpot != null) {
//            // atualiza o status do GardenSpot a partir do DTO
//            gardenSpot.setStatus(gardenSpotDTOUpdateRequest.getStatus());
//            GardenSpot GardenSpotSave = gardenSpotRepository.save(gardenSpot);
//            return modelMapper.map(GardenSpotSave, GardenSpotDTOUpdateResponse.class);
//        } else {
//            // Error 400 caso tente atualiza gardenSpot inexistente.
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//    }

//    public void apagarGardenSpot(Integer gardenSpotId) {
//        gardenSpotRepository.apagadoLogicoGardenSpot(gardenSpotId);
//    }
}

