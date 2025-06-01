package revz.project.restaurant.cart.item.dao;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import revz.project.restaurant.cart.item.dto.CreateItemDTO;
import revz.project.restaurant.cart.item.dto.UpdateItemDTO;
import revz.project.restaurant.cart.item.model.Item;
import revz.project.restaurant.cart.item.repository.ItemRepository;

@Repository
public class ItemDAO {
  
  private final ItemRepository itemRepository;

  public ItemDAO(ItemRepository itemRepository){
    this.itemRepository = itemRepository;
  }

  public Optional<Item> findById(String id){
    return itemRepository.findById(UUID.fromString(id));
  }

  public List<Item> findByName(String name){
    return itemRepository.findByNameContaining(name);
  }

  public Item save(CreateItemDTO createItemDTO) {
    var newItem = new Item(
      createItemDTO.name(),
      createItemDTO.price(),
      createItemDTO.quantity(),
      Instant.now(),
      null
    );
    var savedItem = itemRepository.save(newItem);

    return savedItem;
  }
  public List<Item> findAll(){
    return itemRepository.findAll();
  }

  public void deleteById(String id) {
    itemRepository.deleteById(UUID.fromString(id));
  }

  public Item updateItem(String id, UpdateItemDTO updateItemDTO){
    var entity = itemRepository.findById(UUID.fromString(id));
    if(entity.isPresent()){
      var item = entity.get();
      
      if (updateItemDTO.name() != null && !updateItemDTO.name().isBlank()) {
        item.setName(updateItemDTO.name());
      }

      if (updateItemDTO.price() != null) {
          item.setPrice(updateItemDTO.price());
      }

      if (updateItemDTO.quantity() != null) {
          item.setQuantity(updateItemDTO.quantity());
      }

      return itemRepository.save(item);
    }
    return null;
  }
}
