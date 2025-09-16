package com.senac.bunnyhut.service;

import com.senac.bunnyhut.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository,
                         ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDTOResponse> listarUseres() {
        return userRepository.listarUseres()
                .stream()
                .map(user -> modelMapper.map(user, UserDTOResponse.class))
                .toList()
                ;
    }

    public UserDTOResponse listarPorUserId(Integer userId) {
        User user = userRepository.obterUserPeloId(userId);
        return (user != null) ? modelMapper.map(user, UserDTOResponse.class) : null;
    }

    @Transactional
    public UserDTOResponse criarUser(UserDTORequest userDTORequest) {
        User user = modelMapper.map(userDTORequest, User.class);
        User UserSave = this.userRepository.save(user);
        return modelMapper.map(UserSave, UserDTOResponse.class);
    }

    @Transactional
    public UserDTOResponse atualizarUser(Integer userId, UserDTORequest userDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        User user = userRepository.obterUserPeloId(userId);
        //se encontra o registro a ser atualizado
        if (user != null) {
            // atualiza dados do user a partir do DTO
            modelMapper.map(userDTORequest, user);
            // atualiza a categoria vinculada
            User tempResponse = userRepository.save(user);
            return modelMapper.map(tempResponse, UserDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza user inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public UserDTOUpdateResponse atualizarStatusUser(Integer userId, UserDTORequest userDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        User user = userRepository.obterUserPeloId(userId);
        //se encontra o registro a ser atualizado
        if (user != null) {
            // atualiza o status do User a partir do DTO
            user.setStatus(userDTOUpdateRequest.getStatus());
            User UserSave = userRepository.save(user);
            return modelMapper.map(UserSave, UserDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza user inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarUser(Integer userId) {
        userRepository.apagadoLogicoUser(userId);
    }
}

