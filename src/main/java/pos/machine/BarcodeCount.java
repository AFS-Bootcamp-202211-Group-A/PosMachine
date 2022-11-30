package pos.machine;

public class BarcodeCount {
    String barcode;
    int quantity;

    public BarcodeCount(String barcode, int quantity) {
        this.barcode = barcode;
        this.quantity = quantity;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getQuantity() {
        return quantity;
    }
}
