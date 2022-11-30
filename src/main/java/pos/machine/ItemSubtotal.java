package pos.machine;

public class ItemSubtotal {

    private final String barcode;
    private final String name;
    private final int price;

    private final int quantity;

    public final int subTotal;


    public ItemSubtotal(String barcode, String name, int price, int quantity, int subTotal) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getSubTotal() {
        return subTotal;
    }

}
