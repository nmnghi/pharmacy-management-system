package com.example.pharmacy;

public class purchaseData {
    private String medcine_id;
    private String productName;
    private String category;
    private Integer quantity;
    private Integer price;

    public purchaseData(String medcine_id, String productName, String category, Integer quantity, Integer price) {
        this.medcine_id = medcine_id;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public String getMedcine_id() {
        return medcine_id;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getPrice() {
        return price;
    }
}
