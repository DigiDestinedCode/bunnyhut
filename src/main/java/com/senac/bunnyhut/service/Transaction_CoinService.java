package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.Transaction_CoinDTORequest;
import com.senac.bunnyhut.dto.response.Transaction_CoinDTOResponse;
import com.senac.bunnyhut.dto.response.Transaction_CoinDTOUpdateResponse;
import com.senac.bunnyhut.entity.Transaction_Coin;
import com.senac.bunnyhut.repository.Transaction_CoinRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class Transaction_CoinService {

    private final Transaction_CoinRepository transaction_coinRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public Transaction_CoinService(Transaction_CoinRepository transaction_coinRepository,
                         ModelMapper modelMapper) {
        this.transaction_coinRepository = transaction_coinRepository;
        this.modelMapper = modelMapper;
    }

    public List<Transaction_CoinDTOResponse> listTransaction_Coins() {
        return transaction_coinRepository.listTransaction_Coins()
                .stream()
                .map(transaction_coin -> modelMapper.map(transaction_coin, Transaction_CoinDTOResponse.class))
                .toList()
                ;
    }

    public Transaction_CoinDTOResponse listarPorTransaction_CoinId(Integer transaction_coinId) {
        Transaction_Coin transaction_coin = transaction_coinRepository.obterTransaction_CoinPeloId(transaction_coinId);
        return (transaction_coin != null) ? modelMapper.map(transaction_coin, Transaction_CoinDTOResponse.class) : null;
    }

    @Transactional
    public Transaction_CoinDTOResponse criarTransaction_Coin(Transaction_CoinDTORequest transaction_coinDTORequest) {
        Transaction_Coin transaction_coin = modelMapper.map(transaction_coinDTORequest, Transaction_Coin.class);
        Transaction_Coin Transaction_CoinSave = this.transaction_coinRepository.save(transaction_coin);
        return modelMapper.map(Transaction_CoinSave, Transaction_CoinDTOResponse.class);
    }

    @Transactional
    public Transaction_CoinDTOResponse atualizarTransaction_Coin(Integer transaction_coinId, Transaction_CoinDTORequest transaction_coinDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Transaction_Coin transaction_coin = transaction_coinRepository.obterTransaction_CoinPeloId(transaction_coinId);
        //se encontra o registro a ser atualizado
        if (transaction_coin != null) {
            // atualiza dados do transaction_coin a partir do DTO
            modelMapper.map(transaction_coinDTORequest, transaction_coin);
            // atualiza a categoria vinculada
            Transaction_Coin tempResponse = transaction_coinRepository.save(transaction_coin);
            return modelMapper.map(tempResponse, Transaction_CoinDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza transaction_coin inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

//    @Transactional
//    public Transaction_CoinDTOUpdateResponse atualizarStatusTransaction_Coin(Integer transaction_coinId, Transaction_CoinDTORequest transaction_coinDTOUpdateRequest) {
//        //antes de atualizar busca se existe o registro a ser atualizado
//        Transaction_Coin transaction_coin = transaction_coinRepository.obterTransaction_CoinPeloId(transaction_coinId);
//        //se encontra o registro a ser atualizado
//        if (transaction_coin != null) {
//            // atualiza o status do Transaction_Coin a partir do DTO
//            transaction_coin.setStatus(transaction_coinDTOUpdateRequest.getStatus());
//            Transaction_Coin Transaction_CoinSave = transaction_coinRepository.save(transaction_coin);
//            return modelMapper.map(Transaction_CoinSave, Transaction_CoinDTOUpdateResponse.class);
//        } else {
//            // Error 400 caso tente atualiza transaction_coin inexistente.
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//    }

//    public void apagarTransaction_Coin(Integer transaction_coinId) {
//        transaction_coinRepository.apagadoLogicoTransaction_Coin(transaction_coinId);
//    }
}

