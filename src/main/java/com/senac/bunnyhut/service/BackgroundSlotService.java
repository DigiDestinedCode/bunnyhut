package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.BackgroundSlotDTORequest;
import com.senac.bunnyhut.dto.response.BackgroundSlotDTOResponse;
import com.senac.bunnyhut.entity.BackgroundSlot;
import com.senac.bunnyhut.repository.BackgroundSlotRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BackgroundSlotService {

    private final BackgroundSlotRepository backgroundslotRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public BackgroundSlotService(BackgroundSlotRepository backgroundslotRepository,
                                 ModelMapper modelMapper) {
        this.backgroundslotRepository = backgroundslotRepository;
        this.modelMapper = modelMapper;
    }

    public List<BackgroundSlotDTOResponse> listBackgroundSlots() {
        return backgroundslotRepository.listBackgroundSlots()
                .stream()
                .map(backgroundslot -> modelMapper.map(backgroundslot, BackgroundSlotDTOResponse.class))
                .toList()
                ;
    }

    public BackgroundSlotDTOResponse listarPorBackgroundSlotId(Integer backgroundslotId) {
        BackgroundSlot backgroundslot = backgroundslotRepository.obterBackgroundSlotPeloId(backgroundslotId);
        return (backgroundslot != null) ? modelMapper.map(backgroundslot, BackgroundSlotDTOResponse.class) : null;
    }

    @Transactional
    public BackgroundSlotDTOResponse criarBackgroundSlot(BackgroundSlotDTORequest backgroundslotDTORequest) {
        BackgroundSlot backgroundslot = modelMapper.map(backgroundslotDTORequest, BackgroundSlot.class);
        BackgroundSlot BackgroundSlotsave = this.backgroundslotRepository.save(backgroundslot);
        return modelMapper.map(BackgroundSlotsave, BackgroundSlotDTOResponse.class);
    }

    @Transactional
    public BackgroundSlotDTOResponse atualizarBackgroundSlot(Integer backgroundslotId, BackgroundSlotDTORequest backgroundslotDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        BackgroundSlot backgroundslot = backgroundslotRepository.obterBackgroundSlotPeloId(backgroundslotId);
        //se encontra o registro a ser atualizado
        if (backgroundslot != null) {
            // atualiza dados do backgroundslot a partir do DTO
            modelMapper.map(backgroundslotDTORequest, backgroundslot);
            // atualiza a categoria vinculada
            BackgroundSlot tempResponse = backgroundslotRepository.save(backgroundslot);
            return modelMapper.map(tempResponse, BackgroundSlotDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza backgroundslot inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

//    @Transactional
//    public BackgroundslotDTOUpdateResponse atualizarStatusBackgroundslot(Integer backgroundslotId, BackgroundslotDTORequest backgroundslotDTOUpdateRequest) {
//        //antes de atualizar busca se existe o registro a ser atualizado
//        Backgroundslot backgroundslot = backgroundslotRepository.obterBackgroundslotPeloId(backgroundslotId);
//        //se encontra o registro a ser atualizado
//        if (backgroundslot != null) {
//            // atualiza o status do Backgroundslot a partir do DTO
//            backgroundslot.setStatus(backgroundslotDTOUpdateRequest.getStatus());
//            Backgroundslot BackgroundSlotsave = backgroundslotRepository.save(backgroundslot);
//            return modelMapper.map(BackgroundSlotsave, BackgroundslotDTOUpdateResponse.class);
//        } else {
//            // Error 400 caso tente atualiza backgroundslot inexistente.
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//    }

//    public void apagarBackgroundslot(Integer backgroundslotId) {
//        backgroundslotRepository.apagadoLogicoBackgroundslot(backgroundslotId);
//    }
}

