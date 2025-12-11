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
                .toList();
    }

    public UserDTOResponse getUserById(Integer userId) {
        User user = userRepository.getUserById(userId);
        return (user != null) ? modelMapper.map(user, UserDTOResponse.class) : null;
    }

    @Transactional
    public UserDTOResponse createUser(UserDTORequest userDTORequest) {
        User user = modelMapper.map(userDTORequest, User.class);
        User userSave = this.userRepository.save(user);
        return modelMapper.map(userSave, UserDTOResponse.class);
    }

    @Transactional
    public UserDTOResponse updateUser(Integer userId, UserDTORequest userDTORequest) {
        User user = userRepository.getUserById(userId);
        if (user != null) {
            modelMapper.map(userDTORequest, user);
            User tempResponse = userRepository.save(user);
            return modelMapper.map(tempResponse, UserDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public UserDTOUpdateResponse updateUserStatus(Integer userId, UserDTORequest userDTOUpdateRequest) {
        User user = userRepository.getUserById(userId);
        if (user != null) {
            user.setStatus(userDTOUpdateRequest.getStatus());
            User userSave = userRepository.save(user);
            return modelMapper.map(userSave, UserDTOUpdateResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void deleteUser(Integer userId) {
        userRepository.logicalDeleteUser(userId);
    }

    public UserDTOLoginResponse login(UserDTOLoginRequest userDTOLoginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDTOLoginRequest.getEmail(), userDTOLoginRequest.getPasswordHash());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        UserDTOLoginResponse userDTOLoginResponse = new UserDTOLoginResponse();
        userDTOLoginResponse.setId(userDetails.getIdUser());
        userDTOLoginResponse.setEmail(userDetails.getUserEmail());
        userDTOLoginResponse.setToken(jwtTokenService.generateToken(userDetails));
        return userDTOLoginResponse;
    }

    public UserDTOResponse create(UserDTORequest userDTORequest) {

        List<Role> roles = new ArrayList<Role>();
        for (int i=0; i<userDTORequest.getRoleList().size(); i++){
            Role role = new Role();
            role.setName(RoleName.valueOf(userDTORequest.getRoleList().get(i)));
            roles.add(role);
        }

        User user = new User();
        user.setNickname(userDTORequest.getNickname());
        user.setEmail(userDTORequest.getEmail());
        user.setPasswordHash(securityConfiguration.passwordEncoder().encode(userDTORequest.getPasswordHash()));
        user.setCoin(0);
        user.setStatus(1);
        user.setCreatedAt(LocalDateTime.now());
        user.setRoles(roles);
        User userSave = userRepository.save(user);

        return modelMapper.map(userSave, UserDTOResponse.class);
    }
}