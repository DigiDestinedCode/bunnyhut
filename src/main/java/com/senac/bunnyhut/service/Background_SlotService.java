package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.Background_SlotDTORequest;
import com.senac.bunnyhut.dto.response.Background_SlotDTOResponse;
import com.senac.bunnyhut.dto.response.Background_SlotDTOUpdateResponse;
import com.senac.bunnyhut.entity.Background_Slot;
import com.senac.bunnyhut.repository.Background_SlotRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class Background_SlotService {

    private final Background_SlotRepository background_slotRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public Background_SlotService(Background_SlotRepository background_slotRepository,
                         ModelMapper modelMapper) {
        this.background_slotRepository = background_slotRepository;
        this.modelMapper = modelMapper;
    }

    public List<Background_SlotDTOResponse> listBackground_Slots() {
        return background_slotRepository.listBackground_Slots()
                .stream()
                .map(background_slot -> modelMapper.map(background_slot, Background_SlotDTOResponse.class))
                .toList()
                ;
    }

    public Background_SlotDTOResponse listarPorBackground_SlotId(Integer background_slotId) {
        Background_Slot background_slot = background_slotRepository.obterBackground_SlotPeloId(background_slotId);
        return (background_slot != null) ? modelMapper.map(background_slot, Background_SlotDTOResponse.class) : null;
    }

    @Transactional
    public Background_SlotDTOResponse criarBackground_Slot(Background_SlotDTORequest background_slotDTORequest) {
        Background_Slot background_slot = modelMapper.map(background_slotDTORequest, Background_Slot.class);
        Background_Slot Background_SlotSave = this.background_slotRepository.save(background_slot);
        return modelMapper.map(Background_SlotSave, Background_SlotDTOResponse.class);
    }

    @Transactional
    public Background_SlotDTOResponse atualizarBackground_Slot(Integer background_slotId, Background_SlotDTORequest background_slotDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Background_Slot background_slot = background_slotRepository.obterBackground_SlotPeloId(background_slotId);
        //se encontra o registro a ser atualizado
        if (background_slot != null) {
            // atualiza dados do background_slot a partir do DTO
            modelMapper.map(background_slotDTORequest, background_slot);
            // atualiza a categoria vinculada
            Background_Slot tempResponse = background_slotRepository.save(background_slot);
            return modelMapper.map(tempResponse, Background_SlotDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza background_slot inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public Background_SlotDTOUpdateResponse atualizarStatusBackground_Slot(Integer background_slotId, Background_SlotDTORequest background_slotDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Background_Slot background_slot = background_slotRepository.obterBackground_SlotPeloId(background_slotId);
        //se encontra o registro a ser atualizado
        if (background_slot != null) {
            // atualiza o status do Background_Slot a partir do DTO
            background_slot.setStatus(background_slotDTOUpdateRequest.getStatus());
            Background_Slot Background_SlotSave = background_slotRepository.save(background_slot);
            return modelMapper.map(Background_SlotSave, Background_SlotDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza background_slot inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarBackground_Slot(Integer background_slotId) {
        background_slotRepository.apagadoLogicoBackground_Slot(background_slotId);
    }
}

