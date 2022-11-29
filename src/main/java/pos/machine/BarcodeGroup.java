package pos.machine;

public class BarcodeGroup {
    String barcode;
    String name;
    int quantity;
    int price;

    public BarcodeGroup(String barcode, String name, int quantity, int price) {
        this.barcode = barcode;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}
