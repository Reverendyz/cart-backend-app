package revz.project.restaurant.cart.item.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Size;

public record UpdateItemDTO(
  @Size(min = 3, max = 50, message = "Size must be between 3 and 50 characters long")
  String name,
  Double price,
  Integer quantity
) {
  @AssertTrue(message = "Price must be greater than 0.01, if provided")
  public boolean isPriceValid(){
    return price == null || price > 0.01;
  }
  @AssertTrue(message = "Quantity must be between 0 and 999, if provided")
  public boolean isQuantityValid(){
    return quantity == null || (quantity >- 0 && quantity <= 999);
  }
}
