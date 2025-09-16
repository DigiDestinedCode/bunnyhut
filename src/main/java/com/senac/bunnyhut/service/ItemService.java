package com.senac.bunnyhut.service;

import com.senac.bunnyhut.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<ItemDTOResponse> listarItemes() {
        return itemRepository.listarItemes()
                .stream()
                .map(item -> modelMapper.map(item, ItemDTOResponse.class))
                .toList()
                ;
    }

    public ItemDTOResponse listarPorItemId(Integer itemId) {
        Item item = itemRepository.obterItemPeloId(itemId);
        return (item != null) ? modelMapper.map(item, ItemDTOResponse.class) : null;
    }

    @Transactional
    public ItemDTOResponse criarItem(ItemDTORequest itemDTORequest) {
        Item item = modelMapper.map(itemDTORequest, Item.class);
        Item ItemSave = this.itemRepository.save(item);
        return modelMapper.map(ItemSave, ItemDTOResponse.class);
    }

    @Transactional
    public ItemDTOResponse atualizarItem(Integer itemId, ItemDTORequest itemDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Item item = itemRepository.obterItemPeloId(itemId);
        //se encontra o registro a ser atualizado
        if (item != null) {
            // atualiza dados do item a partir do DTO
            modelMapper.map(itemDTORequest, item);
            // atualiza a categoria vinculada
            Item tempResponse = itemRepository.save(item);
            return modelMapper.map(tempResponse, ItemDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza item inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ItemDTOUpdateResponse atualizarStatusItem(Integer itemId, ItemDTORequest itemDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Item item = itemRepository.obterItemPeloId(itemId);
        //se encontra o registro a ser atualizado
        if (item != null) {
            // atualiza o status do Item a partir do DTO
            item.setStatus(itemDTOUpdateRequest.getStatus());
            Item ItemSave = itemRepository.save(item);
            return modelMapper.map(ItemSave, ItemDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza item inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarItem(Integer itemId) {
        itemRepository.apagadoLogicoItem(itemId);
    }
}

