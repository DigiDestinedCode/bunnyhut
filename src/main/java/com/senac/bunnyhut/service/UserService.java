package com.senac.bunnyhut.service;

import com.senac.bunnyhut.config.SecurityConfiguration;
import com.senac.bunnyhut.dto.request.UserDTOLoginRequest;
import com.senac.bunnyhut.dto.request.UserDTORequest;
import com.senac.bunnyhut.dto.response.UserDTOLoginResponse;
import com.senac.bunnyhut.dto.response.UserDTOResponse;
import com.senac.bunnyhut.dto.response.UserDTOUpdateResponse;
import com.senac.bunnyhut.entity.Role;
import com.senac.bunnyhut.entity.RoleName;
import com.senac.bunnyhut.entity.User;
import com.senac.bunnyhut.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private SecurityConfiguration securityConfiguration;

    private final UserRepository userRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository,
                         ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDTOResponse> listUsers() {
        return userRepository.listUsers()
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

    // Método responsável por autenticar um usuário e retornar um token JWT
    public UserDTOLoginResponse login(UserDTOLoginRequest userDTOLoginRequest) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDTOLoginRequest.getEmail(), userDTOLoginRequest.getPassword_hash());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        UserDTOLoginResponse userDTOLoginResponse = new UserDTOLoginResponse();
        userDTOLoginResponse.setId(userDetails.getIdUser());
        userDTOLoginResponse.setEmail(userDetails.getUserEmail());
        userDTOLoginResponse.setToken(jwtTokenService.generateToken(userDetails));
        return userDTOLoginResponse;
//        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    // Método responsável por criar um usuário
    public UserDTOResponse criar(UserDTORequest userDTORequest) {

        List<Role> roles = new ArrayList<Role>();
        for (int i=0; i<userDTORequest.getRoleList().size(); i++){
            Role role = new Role();
            role.setName(RoleName.valueOf(userDTORequest.getRoleList().get(i)));
            roles.add(role);
        }
//        // Atribui ao usuário uma permissão específica
        Role role = new Role();
//        role.setName(userDTORequest.getRoles());
//        newUser.setRoles(List.of(role));
//
//        // Cria um novo usuário com os dados fornecidos


        User user = new User();
        user.setNickname(userDTORequest.getNickname());
        user.setEmail(userDTORequest.getEmail());
        user.setPassword_hash(securityConfiguration.passwordEncoder().encode(userDTORequest.getPassword_hash()));
        user.setCoin(0);
        user.setStatus(1);
        user.setCreated_at(LocalDateTime.now());
        user.setRoles(roles);
        User usersave = userRepository.save(user);
//        newUser.setLogin(userDTORequest.getLogin());
//        // Codifica a senha do usuário com o algoritmo bcrypt
//        newUser.setSenha(securityConfiguration.passwordEncoder().encode(userDTORequest.getSenha()));
//
//
//        // Salva o novo usuário no banco de dados
        UserDTOResponse userDTO = new UserDTOResponse();
        user.setId(usersave.getId());
        user.setEmail(usersave.getEmail());
        user.setNickname(usersave.getNickname());
        user.setCoin(usersave.getCoin());
        user.setCreated_at(usersave.getCreated_at());
        return userDTO;
    }
}

