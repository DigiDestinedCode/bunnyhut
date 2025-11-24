package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.TransactionCoinDTORequest;
import com.senac.bunnyhut.dto.response.TransactionCoinDTOResponse;
import com.senac.bunnyhut.entity.TransactionCoin;
import com.senac.bunnyhut.repository.TransactionCoinRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransactionCoinService {

    private final TransactionCoinRepository transactionCoinRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public TransactionCoinService(TransactionCoinRepository transactionCoinRepository,
                                  ModelMapper modelMapper) {
        this.transactionCoinRepository = transactionCoinRepository;
        this.modelMapper = modelMapper;
    }

    public List<TransactionCoinDTOResponse> listTransactionCoins() {
        return transactionCoinRepository.listTransactionCoins()
                .stream()
                .map(transactionCoin -> modelMapper.map(transactionCoin, TransactionCoinDTOResponse.class))
                .toList()
                ;
    }

    public TransactionCoinDTOResponse listarPorTransactionCoinId(Integer transactionCoinId) {
        TransactionCoin transactionCoin = transactionCoinRepository.obterTransactionCoinPeloId(transactionCoinId);
        return (transactionCoin != null) ? modelMapper.map(transactionCoin, TransactionCoinDTOResponse.class) : null;
    }

    @Transactional
    public TransactionCoinDTOResponse criarTransactionCoin(TransactionCoinDTORequest transactionCoinDTORequest) {
        TransactionCoin transactionCoin = modelMapper.map(transactionCoinDTORequest, TransactionCoin.class);
        TransactionCoin TransactionCoinSave = this.transactionCoinRepository.save(transactionCoin);
        return modelMapper.map(TransactionCoinSave, TransactionCoinDTOResponse.class);
    }

    @Transactional
    public TransactionCoinDTOResponse atualizarTransactionCoin(Integer transactionCoinId, TransactionCoinDTORequest transactionCoinDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        TransactionCoin transactionCoin = transactionCoinRepository.obterTransactionCoinPeloId(transactionCoinId);
        //se encontra o registro a ser atualizado
        if (transactionCoin != null) {
            // atualiza dados do transactionCoin a partir do DTO
            modelMapper.map(transactionCoinDTORequest, transactionCoin);
            // atualiza a categoria vinculada
            TransactionCoin tempResponse = transactionCoinRepository.save(transactionCoin);
            return modelMapper.map(tempResponse, TransactionCoinDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza transactionCoin inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

//    @Transactional
//    public TransactionCoinDTOUpdateResponse atualizarStatusTransactionCoin(Integer transactionCoinId, TransactionCoinDTORequest transactionCoinDTOUpdateRequest) {
//        //antes de atualizar busca se existe o registro a ser atualizado
//        TransactionCoin transactionCoin = transactionCoinRepository.obterTransactionCoinPeloId(transactionCoinId);
//        //se encontra o registro a ser atualizado
//        if (transactionCoin != null) {
//            // atualiza o status do TransactionCoin a partir do DTO
//            transactionCoin.setStatus(transactionCoinDTOUpdateRequest.getStatus());
//            TransactionCoin TransactionCoinSave = transactionCoinRepository.save(transactionCoin);
//            return modelMapper.map(TransactionCoinSave, TransactionCoinDTOUpdateResponse.class);
//        } else {
//            // Error 400 caso tente atualiza transactionCoin inexistente.
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//    }

//    public void apagarTransactionCoin(Integer transactionCoinId) {
//        transactionCoinRepository.apagadoLogicoTransactionCoin(transactionCoinId);
//    }
}

