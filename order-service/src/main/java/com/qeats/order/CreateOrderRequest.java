package com.qeats.order;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record CreateOrderRequest(@NotNull Long restaurantId,@NotBlank String customerName,@NotBlank String item,@Min(1) int quantity,@DecimalMin("0.01") BigDecimal unitPrice) {}
