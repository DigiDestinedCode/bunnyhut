package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.RabbitDTORequest;
import com.senac.bunnyhut.dto.response.RabbitDTOResponse;
import com.senac.bunnyhut.entity.Rabbit;
import com.senac.bunnyhut.repository.RabbitRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    public List<RabbitDTOResponse> listRabbits() {
        return rabbitRepository.listRabbits()
                .stream()
                .map(rabbit -> modelMapper.map(rabbit, RabbitDTOResponse.class))
                .toList();
    }

    public RabbitDTOResponse getRabbitById(Integer rabbitId) {
        Rabbit rabbit = rabbitRepository.getRabbitById(rabbitId);
        return (rabbit != null) ? modelMapper.map(rabbit, RabbitDTOResponse.class) : null;
    }

    @Transactional
    public RabbitDTOResponse createRabbit(RabbitDTORequest rabbitDTORequest) {
        Rabbit rabbit = modelMapper.map(rabbitDTORequest, Rabbit.class);
        Rabbit rabbitSave = this.rabbitRepository.save(rabbit);
        return modelMapper.map(rabbitSave, RabbitDTOResponse.class);
    }

    @Transactional
    public RabbitDTOResponse updateRabbit(Integer rabbitId, RabbitDTORequest rabbitDTORequest) {
        Rabbit rabbit = rabbitRepository.getRabbitById(rabbitId);
        if (rabbit != null) {
            modelMapper.map(rabbitDTORequest, rabbit);
            Rabbit tempResponse = rabbitRepository.save(rabbit);
            return modelMapper.map(tempResponse, RabbitDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}