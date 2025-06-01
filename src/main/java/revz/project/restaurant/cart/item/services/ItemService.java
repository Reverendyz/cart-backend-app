package revz.project.restaurant.cart.item.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import revz.project.restaurant.cart.item.dao.ItemDAO;
import revz.project.restaurant.cart.item.dto.CreateItemDTO;
import revz.project.restaurant.cart.item.dto.UpdateItemDTO;
import revz.project.restaurant.cart.item.model.Item;

@Slf4j
@Service
public class ItemService {
  private final ItemDAO itemDAO;

  public ItemService(ItemDAO itemDAO){
    this.itemDAO = itemDAO;
  }

  public Item createItem(CreateItemDTO createItemDTO){
    return this.itemDAO.save(createItemDTO);
  }

  public List<Item> findAll(){
    return this.itemDAO.findAll();
  }

  public List<Item> findByName(String name) {
    return this.itemDAO.findByName(name);
  }

  public Optional<Item> findById(String id){
    return this.itemDAO.findById(id);
  }

  public void deleteById(String id) throws NotFoundException {
    var item = itemDAO.findById(id);
    if (!item.isPresent()){
      log.error("Coudn't find item with id: {}", id.toString());
      return;
    }
    itemDAO.deleteById(item.get().getId().toString());
  }

  public Item updateItem(String id, UpdateItemDTO updateItemDTO){
    var item = itemDAO.findById(id);
    if (!item.isPresent()){
      log.error("Couldn't find an item with such ID: {}", id);
      return null;
    }
    return itemDAO.updateItem(id, updateItemDTO);
  }
}
