package org.example.dto;

public class CartResponse {

    private Long id;
    private Long foodId;
    private String foodName;
    private String imageUrl;
    private Double price;
    private Integer quantity;
    private Double totalPrice;

    public CartResponse(
            Long id,
            Long foodId,
            String foodName,
            String imageUrl,
            Double price,
            Integer quantity,
            Double totalPrice) {

        this.id = id;
        this.foodId = foodId;
        this.foodName = foodName;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public Long getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}