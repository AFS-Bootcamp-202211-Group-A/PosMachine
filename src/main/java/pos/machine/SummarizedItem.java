package pos.machine;

public class SummarizedItem {
    private String barcode;
    private String name;
    private int quantity;
    private int unitPrice;
    private int subTotal;

    public SummarizedItem(String barcode, int quantity){
        this.barcode = barcode;
        this.quantity = quantity;
    }
    public void setNameAndPrice(String name, int unitPrice){
        this.name = name;
        this.unitPrice = unitPrice;
    }
    public void setSubtotal(int subTotal){
        this.subTotal = subTotal;
    }
}
