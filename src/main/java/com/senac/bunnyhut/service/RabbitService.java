package com.senac.bunnyhut.service;

import com.senac.bunnyhut.repository.RabbitRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RabbitService {

    private final RabbitRepository rabbitRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public RabbitService(RabbitRepository rabbitRepository,
                         ModelMapper modelMapper) {
        this.rabbitRepository = rabbitRepository;
        this.modelMapper = modelMapper;
    }

    public List<RabbitDTOResponse> listarRabbites() {
        return rabbitRepository.listarRabbites()
                .stream()
                .map(rabbit -> modelMapper.map(rabbit, RabbitDTOResponse.class))
                .toList()
                ;
    }

    public RabbitDTOResponse listarPorRabbitId(Integer rabbitId) {
        Rabbit rabbit = rabbitRepository.obterRabbitPeloId(rabbitId);
        return (rabbit != null) ? modelMapper.map(rabbit, RabbitDTOResponse.class) : null;
    }

    @Transactional
    public RabbitDTOResponse criarRabbit(RabbitDTORequest rabbitDTORequest) {
        Rabbit rabbit = modelMapper.map(rabbitDTORequest, Rabbit.class);
        Rabbit RabbitSave = this.rabbitRepository.save(rabbit);
        return modelMapper.map(RabbitSave, RabbitDTOResponse.class);
    }

    @Transactional
    public RabbitDTOResponse atualizarRabbit(Integer rabbitId, RabbitDTORequest rabbitDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Rabbit rabbit = rabbitRepository.obterRabbitPeloId(rabbitId);
        //se encontra o registro a ser atualizado
        if (rabbit != null) {
            // atualiza dados do rabbit a partir do DTO
            modelMapper.map(rabbitDTORequest, rabbit);
            // atualiza a categoria vinculada
            Rabbit tempResponse = rabbitRepository.save(rabbit);
            return modelMapper.map(tempResponse, RabbitDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza rabbit inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public RabbitDTOUpdateResponse atualizarStatusRabbit(Integer rabbitId, RabbitDTORequest rabbitDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Rabbit rabbit = rabbitRepository.obterRabbitPeloId(rabbitId);
        //se encontra o registro a ser atualizado
        if (rabbit != null) {
            // atualiza o status do Rabbit a partir do DTO
            rabbit.setStatus(rabbitDTOUpdateRequest.getStatus());
            Rabbit RabbitSave = rabbitRepository.save(rabbit);
            return modelMapper.map(RabbitSave, RabbitDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza rabbit inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarRabbit(Integer rabbitId) {
        rabbitRepository.apagadoLogicoRabbit(rabbitId);
    }
}

