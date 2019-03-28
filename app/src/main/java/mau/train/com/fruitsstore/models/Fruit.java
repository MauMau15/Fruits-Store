package mau.train.com.fruitsstore.models;

public class Fruit {

    private String name;

    private int image;

    private String description;

    private String price;

    private int quantity;

    public Fruit(String name, int image, String decription, String price, int quantity) {
        this.name = name;
        this.image = image;
        this.description = decription;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addFruit(int quantity){
        this.setQuantity(this.getQuantity() + quantity);
    }

}
