package revz.project.restaurant.menu.item.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import revz.project.restaurant.menu.item.model.Item;

public interface ItemRepository extends JpaRepository<Item, UUID>{
  List<Item> findByNameContaining(String name);
}
