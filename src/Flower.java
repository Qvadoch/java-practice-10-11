import java.io.Serial;
import java.io.Serializable;

public class Flower implements Serializable {
    @Serial
    private static final long serialVersionUID = -3175526916404368353L;
    private String name;
    private String color;
    private int quantity;
    private double price;
    private String bloomSeason;
    private String fragrance;

    public Flower(String name, String color, int quantity, double price, String bloomSeason, String fragrance) {
        this.name = name;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.bloomSeason = bloomSeason;
        this.fragrance = fragrance;
    }

    public Flower(Flower other) {
        this.name = other.name;
        this.color = other.color;
        this.quantity = other.quantity;
        this.price = other.price;
        this.bloomSeason = other.bloomSeason;
        this.fragrance = other.fragrance;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getBloomSeason() {
        return bloomSeason;
    }

    public String getFragrance() {
        return fragrance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBloomSeason(String bloomSeason) {
        this.bloomSeason = bloomSeason;
    }

    public void setFragrance(String fragrance) {
        this.fragrance = fragrance;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", bloomSeason='" + bloomSeason + '\'' +
                ", fragrance='" + fragrance + '\'' +
                '}';
    }
}