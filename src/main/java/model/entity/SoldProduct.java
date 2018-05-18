package model.entity;

import java.util.Objects;

public class SoldProduct implements Entity {

    private int id, checkId;
    private String productCode;
    private double quantity, price;

    public SoldProduct(int id, int checkId, String productCode, double quantity, double price) {
        this.id = id;
        this.checkId = checkId;
        this.productCode = productCode;
        this.quantity = quantity;
        this.price = price;
    }

    public SoldProduct() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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
        if (!(o instanceof SoldProduct)) return false;
        SoldProduct that = (SoldProduct) o;
        return id == that.id &&
                checkId == that.checkId &&
                Double.compare(that.quantity, quantity) == 0 &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(productCode, that.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkId, productCode, quantity, price);
    }

    @Override
    public String toString() {
        return "SoldProduct{" +
                "id=" + id +
                ", checkId=" + checkId +
                ", productCode='" + productCode + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
