package com.senac.bunnyhut.service;

import com.senac.bunnyhut.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public InventoryService(InventoryRepository inventoryRepository,
                         ModelMapper modelMapper) {
        this.inventoryRepository = inventoryRepository;
        this.modelMapper = modelMapper;
    }

    public List<InventoryDTOResponse> listarInventoryes() {
        return inventoryRepository.listarInventoryes()
                .stream()
                .map(inventory -> modelMapper.map(inventory, InventoryDTOResponse.class))
                .toList()
                ;
    }

    public InventoryDTOResponse listarPorInventoryId(Integer inventoryId) {
        Inventory inventory = inventoryRepository.obterInventoryPeloId(inventoryId);
        return (inventory != null) ? modelMapper.map(inventory, InventoryDTOResponse.class) : null;
    }

    @Transactional
    public InventoryDTOResponse criarInventory(InventoryDTORequest inventoryDTORequest) {
        Inventory inventory = modelMapper.map(inventoryDTORequest, Inventory.class);
        Inventory InventorySave = this.inventoryRepository.save(inventory);
        return modelMapper.map(InventorySave, InventoryDTOResponse.class);
    }

    @Transactional
    public InventoryDTOResponse atualizarInventory(Integer inventoryId, InventoryDTORequest inventoryDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Inventory inventory = inventoryRepository.obterInventoryPeloId(inventoryId);
        //se encontra o registro a ser atualizado
        if (inventory != null) {
            // atualiza dados do inventory a partir do DTO
            modelMapper.map(inventoryDTORequest, inventory);
            // atualiza a categoria vinculada
            Inventory tempResponse = inventoryRepository.save(inventory);
            return modelMapper.map(tempResponse, InventoryDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza inventory inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public InventoryDTOUpdateResponse atualizarStatusInventory(Integer inventoryId, InventoryDTORequest inventoryDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Inventory inventory = inventoryRepository.obterInventoryPeloId(inventoryId);
        //se encontra o registro a ser atualizado
        if (inventory != null) {
            // atualiza o status do Inventory a partir do DTO
            inventory.setStatus(inventoryDTOUpdateRequest.getStatus());
            Inventory InventorySave = inventoryRepository.save(inventory);
            return modelMapper.map(InventorySave, InventoryDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza inventory inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarInventory(Integer inventoryId) {
        inventoryRepository.apagadoLogicoInventory(inventoryId);
    }
}

