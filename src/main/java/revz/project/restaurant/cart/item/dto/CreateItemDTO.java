package revz.project.restaurant.cart.item.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateItemDTO(
  @NotBlank
  String name,
  @NotNull
  @DecimalMin(value = "0.01")
  Double price,
  @NotNull
  @Min(value = 0)
  @Max(value = 999)
  Integer quantity
  ) {}
