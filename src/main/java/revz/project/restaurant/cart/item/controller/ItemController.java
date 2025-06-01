package revz.project.restaurant.cart.item.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import revz.project.restaurant.cart.item.dto.CreateItemDTO;
import revz.project.restaurant.cart.item.dto.UpdateItemDTO;
import revz.project.restaurant.cart.item.model.Item;
import revz.project.restaurant.cart.item.services.ItemService;

@RestController
@RequestMapping("/v1/item")
public class ItemController {
  
  private ItemService itemService;

  public ItemController(ItemService itemService){
    this.itemService = itemService;
  }

  @GetMapping
  public ResponseEntity<List<Item>> getItems(){
    var items = this.itemService.findAll();

    return ResponseEntity.ok(items);
  }

  @GetMapping("/{itemId}")
  public ResponseEntity<Item> getItemById(@PathVariable("itemId") String id){
    var item = this.itemService.findById(id);
    if(!item.isPresent()){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(item.get());
  }

  @PostMapping
  public ResponseEntity<UUID> createItem(@RequestBody @Valid CreateItemDTO createItemDTO){
    var uuid = this.itemService.createItem(createItemDTO);
    return ResponseEntity.created(URI.create("/v1/item"+uuid.toString())).build();
  }

  @PutMapping("/{itemId}")
  public ResponseEntity<Item> updateItem(@PathVariable("itemId") String id, @RequestBody @Valid UpdateItemDTO updateItemDTO) {
    var updatedItem = this.itemService.updateItem(id, updateItemDTO);
    return ResponseEntity.ok(updatedItem);
  }

  @DeleteMapping("/{itemId}")
  public ResponseEntity<Void> deleteItem(@PathVariable("itemId") String id) throws NotFoundException {
    itemService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
