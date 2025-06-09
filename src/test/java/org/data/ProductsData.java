package org.data;

import lombok.Getter;

@Getter
public enum ProductsData {

    BIKE_LIGHT("Sauce Labs Bike Light", 9.99),
    FLEECE_JACKET("Sauce Labs Fleece Jacket", 49.99);

    private final String name;
    private final double price;

    ProductsData(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public static ProductsData fromName(String name) {
        for (ProductsData product : values()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        throw new IllegalArgumentException("No product found for name: " + name);
    }
    public static double getProductPrice(String productName) {
        for (ProductsData product : ProductsData.values()) {
            if (product.getName().equals(productName)) {
                return product.getPrice();
            }
        }
        throw new IllegalArgumentException("Unknown product: " + productName);
    }
}

