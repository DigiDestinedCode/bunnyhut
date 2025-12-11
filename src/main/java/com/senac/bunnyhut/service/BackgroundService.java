package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.BackgroundDTORequest;
import com.senac.bunnyhut.dto.response.BackgroundDTOResponse;
import com.senac.bunnyhut.entity.Background;
import com.senac.bunnyhut.entity.Item;
import com.senac.bunnyhut.repository.BackgroundRepository;
import com.senac.bunnyhut.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BackgroundService {

    private final BackgroundRepository backgroundRepository;
    private final ItemRepository itemRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public BackgroundService(BackgroundRepository backgroundRepository,
                             ModelMapper modelMapper,
                             ItemRepository itemRepository) {
        this.backgroundRepository = backgroundRepository;
        this.modelMapper = modelMapper;
        this.itemRepository = itemRepository;
    }

    public List<BackgroundDTOResponse> listBackgrounds() {
        return backgroundRepository.listBackgrounds()
                .stream()
                .map(background -> modelMapper.map(background, BackgroundDTOResponse.class))
                .toList();
    }

    public BackgroundDTOResponse getBackgroundById(Integer backgroundId) {
        Background background = backgroundRepository.getBackgroundById(backgroundId);
        return (background != null) ? modelMapper.map(background, BackgroundDTOResponse.class) : null;
    }

    @Transactional
    public BackgroundDTOResponse createBackground(BackgroundDTORequest backgroundDTORequest) {
        Background background = modelMapper.map(backgroundDTORequest, Background.class);

        Item item = itemRepository.getItemById(backgroundDTORequest.getItemId());
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item not found with ID: " + backgroundDTORequest.getItemId());
        }
        background.setItem(item);

        Background backgroundSave = this.backgroundRepository.save(background);
        return modelMapper.map(backgroundSave, BackgroundDTOResponse.class);
    }

    @Transactional
    public BackgroundDTOResponse updateBackground(Integer backgroundId, BackgroundDTORequest backgroundDTORequest) {
        Background background = backgroundRepository.getBackgroundById(backgroundId);
        if (background != null) {
            modelMapper.map(backgroundDTORequest, background);

            if (backgroundDTORequest.getItemId() != null) {
                Item item = itemRepository.getItemById(backgroundDTORequest.getItemId());
                if (item == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item not found for update with ID: " + backgroundDTORequest.getItemId());
                }
                background.setItem(item);
            }

            Background tempResponse = backgroundRepository.save(background);
            return modelMapper.map(tempResponse, BackgroundDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}