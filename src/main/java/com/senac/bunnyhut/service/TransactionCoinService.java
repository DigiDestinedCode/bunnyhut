package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.TransactionCoinDTORequest;
import com.senac.bunnyhut.dto.response.TransactionCoinDTOResponse;
import com.senac.bunnyhut.entity.TransactionCoin;
import com.senac.bunnyhut.entity.User;
import com.senac.bunnyhut.repository.TransactionCoinRepository;
import com.senac.bunnyhut.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public TransactionCoinService(TransactionCoinRepository transactionCoinRepository,
                                  ModelMapper modelMapper,
                                  UserRepository userRepository) {
        this.transactionCoinRepository = transactionCoinRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public List<TransactionCoinDTOResponse> listTransactionCoins() {
        return transactionCoinRepository.listTransactionCoins()
                .stream()
                .map(transactionCoin -> modelMapper.map(transactionCoin, TransactionCoinDTOResponse.class))
                .toList();
    }

    public TransactionCoinDTOResponse getTransactionCoinById(Integer transactionCoinId) {
        TransactionCoin transactionCoin = transactionCoinRepository.getTransactionCoinById(transactionCoinId);
        return (transactionCoin != null) ? modelMapper.map(transactionCoin, TransactionCoinDTOResponse.class) : null;
    }

    @Transactional
    public TransactionCoinDTOResponse createTransactionCoin(TransactionCoinDTORequest transactionCoinDTORequest) {
        TransactionCoin transactionCoin = modelMapper.map(transactionCoinDTORequest, TransactionCoin.class);

        User user = userRepository.getUserById(transactionCoinDTORequest.getUserId());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found with ID: " + transactionCoinDTORequest.getUserId());
        }
        transactionCoin.setUser(user);

        TransactionCoin transactionCoinSave = this.transactionCoinRepository.save(transactionCoin);
        return modelMapper.map(transactionCoinSave, TransactionCoinDTOResponse.class);
    }

    @Transactional
    public TransactionCoinDTOResponse updateTransactionCoin(Integer transactionCoinId, TransactionCoinDTORequest transactionCoinDTORequest) {
        TransactionCoin transactionCoin = transactionCoinRepository.getTransactionCoinById(transactionCoinId);
        if (transactionCoin != null) {
            modelMapper.map(transactionCoinDTORequest, transactionCoin);

            if (transactionCoinDTORequest.getUserId() != null) {
                User user = userRepository.getUserById(transactionCoinDTORequest.getUserId());
                if (user == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found for update with ID: " + transactionCoinDTORequest.getUserId());
                }
                transactionCoin.setUser(user);
            }

            TransactionCoin tempResponse = transactionCoinRepository.save(transactionCoin);
            return modelMapper.map(tempResponse, TransactionCoinDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}