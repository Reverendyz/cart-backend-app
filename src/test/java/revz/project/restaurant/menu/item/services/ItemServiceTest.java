package revz.project.restaurant.menu.item.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.validation.Validator;
import revz.project.restaurant.menu.item.dao.ItemDAO;
import revz.project.restaurant.menu.item.dto.CreateItemDTO;
import revz.project.restaurant.menu.item.model.Item;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemServiceTest {
  private ItemDAO itemDAO = mock(ItemDAO.class);
  private ItemService itemService = new ItemService(itemDAO);
  private CreateItemDTO createItemDTO = new CreateItemDTO("aasd", 32.4, 1);

  @Autowired
  Validator validator;

  @Test
  @DisplayName("Shoud Create Item")
  void testCreateItem() {
    Item item = new Item("aasd", 32.4, 1, Instant.now(), null);
    when(itemDAO.save(any())).thenReturn(item);

    Item result = itemService.createItem(createItemDTO);

    assertEquals("aasd", result.getName());
    assertEquals(32.4, result.getPrice());
    assertEquals(1, result.getQuantity());
  }

  @Test
  @DisplayName("Shoud not Create Item")
  void shouldNotCreateItem() {
    var dto = new CreateItemDTO("", 34.5, 2);
    var violations = validator.validate(dto);

    assertFalse(violations.isEmpty());
    assertTrue(violations.stream()
      .anyMatch(v -> v.getPropertyPath()
        .toString().equals("name")));
  }

  @Test
  void testDeleteById() {

  }

  @Test
  void testFindAll() {

  }

  @Test
  void testFindById() {

  }

  @Test
  void testFindByName() {

  }

  @Test
  void testUpdateItem() {

  }
}
