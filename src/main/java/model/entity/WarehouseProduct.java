package model.entity;

import java.util.*;

public class WarehouseProduct implements Entity {
    private String code, name;
    private boolean isWeighty;
    private double quantity, price;

    public WarehouseProduct() {
    }

    public WarehouseProduct(String code, String name, boolean isWeighty, double quantity, double price) {
        this.code = code;
        this.name = name;
        this.isWeighty = isWeighty;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWeighty() {
        return isWeighty;
    }

    public void setWeighty(boolean weighty) {
        isWeighty = weighty;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarehouseProduct)) return false;
        WarehouseProduct warehouseProduct = (WarehouseProduct) o;
        return isWeighty == warehouseProduct.isWeighty &&
                Double.compare(warehouseProduct.quantity, quantity) == 0 &&
                Double.compare(warehouseProduct.price, price) == 0 &&
                Objects.equals(code, warehouseProduct.code) &&
                Objects.equals(name, warehouseProduct.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, isWeighty, quantity, price);
    }

    @Override
    public String toString() {
        return "WarehouseProduct{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", isWeighty=" + isWeighty +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}