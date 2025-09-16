package com.senac.bunnyhut.service;

import com.senac.bunnyhut.repository.BackgroundRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BackgroundService {

    private final BackgroundRepository backgroundRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public BackgroundService(BackgroundRepository backgroundRepository,
                         ModelMapper modelMapper) {
        this.backgroundRepository = backgroundRepository;
        this.modelMapper = modelMapper;
    }

    public List<BackgroundDTOResponse> listarBackgroundes() {
        return backgroundRepository.listarBackgroundes()
                .stream()
                .map(background -> modelMapper.map(background, BackgroundDTOResponse.class))
                .toList()
                ;
    }

    public BackgroundDTOResponse listarPorBackgroundId(Integer backgroundId) {
        Background background = backgroundRepository.obterBackgroundPeloId(backgroundId);
        return (background != null) ? modelMapper.map(background, BackgroundDTOResponse.class) : null;
    }

    @Transactional
    public BackgroundDTOResponse criarBackground(BackgroundDTORequest backgroundDTORequest) {
        Background background = modelMapper.map(backgroundDTORequest, Background.class);
        Background BackgroundSave = this.backgroundRepository.save(background);
        return modelMapper.map(BackgroundSave, BackgroundDTOResponse.class);
    }

    @Transactional
    public BackgroundDTOResponse atualizarBackground(Integer backgroundId, BackgroundDTORequest backgroundDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Background background = backgroundRepository.obterBackgroundPeloId(backgroundId);
        //se encontra o registro a ser atualizado
        if (background != null) {
            // atualiza dados do background a partir do DTO
            modelMapper.map(backgroundDTORequest, background);
            // atualiza a categoria vinculada
            Background tempResponse = backgroundRepository.save(background);
            return modelMapper.map(tempResponse, BackgroundDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza background inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public BackgroundDTOUpdateResponse atualizarStatusBackground(Integer backgroundId, BackgroundDTORequest backgroundDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Background background = backgroundRepository.obterBackgroundPeloId(backgroundId);
        //se encontra o registro a ser atualizado
        if (background != null) {
            // atualiza o status do Background a partir do DTO
            background.setStatus(backgroundDTOUpdateRequest.getStatus());
            Background BackgroundSave = backgroundRepository.save(background);
            return modelMapper.map(BackgroundSave, BackgroundDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza background inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarBackground(Integer backgroundId) {
        backgroundRepository.apagadoLogicoBackground(backgroundId);
    }
}

