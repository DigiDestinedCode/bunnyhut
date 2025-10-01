package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.VisitDTORequest;
import com.senac.bunnyhut.dto.response.VisitDTOResponse;
import com.senac.bunnyhut.dto.response.VisitDTOUpdateResponse;
import com.senac.bunnyhut.entity.Visit;
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

    @Autowired
    private final ModelMapper modelMapper;

    public VisitService(VisitRepository visitRepository,
                         ModelMapper modelMapper) {
        this.visitRepository = visitRepository;
        this.modelMapper = modelMapper;
    }

    public List<VisitDTOResponse> listVisits() {
        return visitRepository.listVisits()
                .stream()
                .map(visit -> modelMapper.map(visit, VisitDTOResponse.class))
                .toList()
                ;
    }

    public VisitDTOResponse listarPorVisitId(Integer visitId) {
        Visit visit = visitRepository.obterVisitPeloId(visitId);
        return (visit != null) ? modelMapper.map(visit, VisitDTOResponse.class) : null;
    }

    @Transactional
    public VisitDTOResponse criarVisit(VisitDTORequest visitDTORequest) {
        Visit visit = modelMapper.map(visitDTORequest, Visit.class);
        Visit VisitSave = this.visitRepository.save(visit);
        return modelMapper.map(VisitSave, VisitDTOResponse.class);
    }

    @Transactional
    public VisitDTOResponse atualizarVisit(Integer visitId, VisitDTORequest visitDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Visit visit = visitRepository.obterVisitPeloId(visitId);
        //se encontra o registro a ser atualizado
        if (visit != null) {
            // atualiza dados do visit a partir do DTO
            modelMapper.map(visitDTORequest, visit);
            // atualiza a categoria vinculada
            Visit tempResponse = visitRepository.save(visit);
            return modelMapper.map(tempResponse, VisitDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza visit inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

//    @Transactional
//    public VisitDTOUpdateResponse atualizarStatusVisit(Integer visitId, VisitDTORequest visitDTOUpdateRequest) {
//        //antes de atualizar busca se existe o registro a ser atualizado
//        Visit visit = visitRepository.obterVisitPeloId(visitId);
//        //se encontra o registro a ser atualizado
//        if (visit != null) {
//            // atualiza o status do Visit a partir do DTO
//            visit.setStatus(visitDTOUpdateRequest.getStatus());
//            Visit VisitSave = visitRepository.save(visit);
//            return modelMapper.map(VisitSave, VisitDTOUpdateResponse.class);
//        } else {
//            // Error 400 caso tente atualiza visit inexistente.
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//    }

//    public void apagarVisit(Integer visitId) {
//        visitRepository.apagadoLogicoVisit(visitId);
//    }
}

