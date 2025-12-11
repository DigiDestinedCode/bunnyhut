package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.VisitDTORequest;
import com.senac.bunnyhut.dto.response.VisitDTOResponse;
import com.senac.bunnyhut.entity.Rabbit;
import com.senac.bunnyhut.entity.User;
import com.senac.bunnyhut.entity.Visit;
import com.senac.bunnyhut.repository.RabbitRepository;
import com.senac.bunnyhut.repository.UserRepository;
import com.senac.bunnyhut.repository.VisitRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final UserRepository userRepository;
    private final RabbitRepository rabbitRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public VisitService(VisitRepository visitRepository,
                        ModelMapper modelMapper,
                        UserRepository userRepository,
                        RabbitRepository rabbitRepository) {
        this.visitRepository = visitRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.rabbitRepository = rabbitRepository;
    }

    public List<VisitDTOResponse> listVisits() {
        return visitRepository.listVisits()
                .stream()
                .map(visit -> modelMapper.map(visit, VisitDTOResponse.class))
                .toList();
    }

    public VisitDTOResponse getVisitById(Integer visitId) {
        Visit visit = visitRepository.getVisitById(visitId);
        return (visit != null) ? modelMapper.map(visit, VisitDTOResponse.class) : null;
    }

    @Transactional
    public VisitDTOResponse createVisit(VisitDTORequest visitDTORequest) {
        Visit visit = modelMapper.map(visitDTORequest, Visit.class);

        User user = userRepository.getUserById(visitDTORequest.getUserId());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found with ID: " + visitDTORequest.getUserId());
        }
        visit.setUser(user);

        Rabbit rabbit = rabbitRepository.getRabbitById(visitDTORequest.getRabbitId());
        if (rabbit == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rabbit not found with ID: " + visitDTORequest.getRabbitId());
        }
        visit.setRabbit(rabbit);

        Visit visitSave = this.visitRepository.save(visit);
        return modelMapper.map(visitSave, VisitDTOResponse.class);
    }

    @Transactional
    public VisitDTOResponse updateVisit(Integer visitId, VisitDTORequest visitDTORequest) {
        Visit visit = visitRepository.getVisitById(visitId);
        if (visit != null) {
            modelMapper.map(visitDTORequest, visit);

            if (visitDTORequest.getUserId() != null) {
                User user = userRepository.getUserById(visitDTORequest.getUserId());
                if (user == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found for update with ID: " + visitDTORequest.getUserId());
                }
                visit.setUser(user);
            }

            if (visitDTORequest.getRabbitId() != null) {
                Rabbit rabbit = rabbitRepository.getRabbitById(visitDTORequest.getRabbitId());
                if (rabbit == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rabbit not found for update with ID: " + visitDTORequest.getRabbitId());
                }
                visit.setRabbit(rabbit);
            }

            Visit tempResponse = visitRepository.save(visit);
            return modelMapper.map(tempResponse, VisitDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}