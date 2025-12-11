package com.senac.bunnyhut.service;

import com.senac.bunnyhut.dto.request.InventoryDTORequest;
import com.senac.bunnyhut.dto.response.InventoryDTOResponse;
import com.senac.bunnyhut.entity.Inventory;
import com.senac.bunnyhut.entity.Item;
import com.senac.bunnyhut.entity.User;
import com.senac.bunnyhut.repository.InventoryRepository;
import com.senac.bunnyhut.repository.ItemRepository;
import com.senac.bunnyhut.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public InventoryService(InventoryRepository inventoryRepository,
                            ModelMapper modelMapper,
                            UserRepository userRepository,
                            ItemRepository itemRepository) {
        this.inventoryRepository = inventoryRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public List<InventoryDTOResponse> listInventories() {
        return inventoryRepository.listInventories()
                .stream()
                .map(inventory -> modelMapper.map(inventory, InventoryDTOResponse.class))
                .toList();
    }

    public InventoryDTOResponse getInventoryById(Integer inventoryId) {
        Inventory inventory = inventoryRepository.getInventoryById(inventoryId);
        return (inventory != null) ? modelMapper.map(inventory, InventoryDTOResponse.class) : null;
    }

    @Transactional
    public InventoryDTOResponse createInventory(InventoryDTORequest inventoryDTORequest) {
        Inventory inventory = modelMapper.map(inventoryDTORequest, Inventory.class);

        User user = userRepository.getUserById(inventoryDTORequest.getUserId());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found with ID: " + inventoryDTORequest.getUserId());
        }
        inventory.setUser(user);

        Item item = itemRepository.getItemById(inventoryDTORequest.getItemId());
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item not found with ID: " + inventoryDTORequest.getItemId());
        }
        inventory.setItem(item);

        Inventory inventorySave = this.inventoryRepository.save(inventory);
        return modelMapper.map(inventorySave, InventoryDTOResponse.class);
    }

    @Transactional
    public InventoryDTOResponse updateInventory(Integer inventoryId, InventoryDTORequest inventoryDTORequest) {
        Inventory inventory = inventoryRepository.getInventoryById(inventoryId);
        if (inventory != null) {
            modelMapper.map(inventoryDTORequest, inventory);

            if (inventoryDTORequest.getUserId() != null) {
                User user = userRepository.getUserById(inventoryDTORequest.getUserId());
                if (user == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found for update with ID: " + inventoryDTORequest.getUserId());
                }
                inventory.setUser(user);
            }

            if (inventoryDTORequest.getItemId() != null) {
                Item item = itemRepository.getItemById(inventoryDTORequest.getItemId());
                if (item == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item not found for update with ID: " + inventoryDTORequest.getItemId());
                }
                inventory.setItem(item);
            }

            Inventory tempResponse = inventoryRepository.save(inventory);
            return modelMapper.map(tempResponse, InventoryDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}