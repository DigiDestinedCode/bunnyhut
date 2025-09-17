package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.Garden_SpotDTORequest;
import com.senac.bunnyhut.dto.response.Garden_SpotDTOResponse;
import com.senac.bunnyhut.dto.response.Garden_SpotDTOUpdateResponse;
import com.senac.bunnyhut.entity.Garden_Spot;
import com.senac.bunnyhut.repository.Garden_SpotRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class Garden_SpotService {

    private final Garden_SpotRepository garden_spotRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public Garden_SpotService(Garden_SpotRepository garden_spotRepository,
                         ModelMapper modelMapper) {
        this.garden_spotRepository = garden_spotRepository;
        this.modelMapper = modelMapper;
    }

    public List<Garden_SpotDTOResponse> listGarden_Spots() {
        return garden_spotRepository.listGarden_Spots()
                .stream()
                .map(garden_spot -> modelMapper.map(garden_spot, Garden_SpotDTOResponse.class))
                .toList()
                ;
    }

    public Garden_SpotDTOResponse listarPorGarden_SpotId(Integer garden_spotId) {
        Garden_Spot garden_spot = garden_spotRepository.obterGarden_SpotPeloId(garden_spotId);
        return (garden_spot != null) ? modelMapper.map(garden_spot, Garden_SpotDTOResponse.class) : null;
    }

    @Transactional
    public Garden_SpotDTOResponse criarGarden_Spot(Garden_SpotDTORequest garden_spotDTORequest) {
        Garden_Spot garden_spot = modelMapper.map(garden_spotDTORequest, Garden_Spot.class);
        Garden_Spot Garden_SpotSave = this.garden_spotRepository.save(garden_spot);
        return modelMapper.map(Garden_SpotSave, Garden_SpotDTOResponse.class);
    }

    @Transactional
    public Garden_SpotDTOResponse atualizarGarden_Spot(Integer garden_spotId, Garden_SpotDTORequest garden_spotDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Garden_Spot garden_spot = garden_spotRepository.obterGarden_SpotPeloId(garden_spotId);
        //se encontra o registro a ser atualizado
        if (garden_spot != null) {
            // atualiza dados do garden_spot a partir do DTO
            modelMapper.map(garden_spotDTORequest, garden_spot);
            // atualiza a categoria vinculada
            Garden_Spot tempResponse = garden_spotRepository.save(garden_spot);
            return modelMapper.map(tempResponse, Garden_SpotDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza garden_spot inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public Garden_SpotDTOUpdateResponse atualizarStatusGarden_Spot(Integer garden_spotId, Garden_SpotDTORequest garden_spotDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Garden_Spot garden_spot = garden_spotRepository.obterGarden_SpotPeloId(garden_spotId);
        //se encontra o registro a ser atualizado
        if (garden_spot != null) {
            // atualiza o status do Garden_Spot a partir do DTO
            garden_spot.setStatus(garden_spotDTOUpdateRequest.getStatus());
            Garden_Spot Garden_SpotSave = garden_spotRepository.save(garden_spot);
            return modelMapper.map(Garden_SpotSave, Garden_SpotDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza garden_spot inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarGarden_Spot(Integer garden_spotId) {
        garden_spotRepository.apagadoLogicoGarden_Spot(garden_spotId);
    }
}

