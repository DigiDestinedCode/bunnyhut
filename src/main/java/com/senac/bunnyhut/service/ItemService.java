package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.ItemDTORequest;
import com.senac.bunnyhut.dto.response.ItemDTOResponse;
import com.senac.bunnyhut.dto.response.ItemDTOUpdateResponse;
import com.senac.bunnyhut.entity.Item;
import com.senac.bunnyhut.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public ItemService(ItemRepository itemRepository,
                       ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    public List<ItemDTOResponse> listItems() {
        return itemRepository.listItems()
                .stream()
                .map(item -> modelMapper.map(item, ItemDTOResponse.class))
                .toList();
    }

    public ItemDTOResponse getItemById(Integer itemId) {
        Item item = itemRepository.getItemById(itemId);
        return (item != null) ? modelMapper.map(item, ItemDTOResponse.class) : null;
    }

    @Transactional
    public ItemDTOResponse createItem(ItemDTORequest itemDTORequest) {
        Item item = modelMapper.map(itemDTORequest, Item.class);
        Item itemSave = this.itemRepository.save(item);
        return modelMapper.map(itemSave, ItemDTOResponse.class);
    }

    @Transactional
    public ItemDTOResponse updateItem(Integer itemId, ItemDTORequest itemDTORequest) {
        Item item = itemRepository.getItemById(itemId);
        if (item != null) {
            modelMapper.map(itemDTORequest, item);
            Item tempResponse = itemRepository.save(item);
            return modelMapper.map(tempResponse, ItemDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ItemDTOUpdateResponse updateItemStatus(Integer itemId, ItemDTORequest itemDTOUpdateRequest) {
        Item item = itemRepository.getItemById(itemId);
        if (item != null) {
            item.setStatus(itemDTOUpdateRequest.getStatus());
            Item itemSave = itemRepository.save(item);
            return modelMapper.map(itemSave, ItemDTOUpdateResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void deleteItem(Integer itemId) {
        itemRepository.logicalDeleteItem(itemId);
    }
}