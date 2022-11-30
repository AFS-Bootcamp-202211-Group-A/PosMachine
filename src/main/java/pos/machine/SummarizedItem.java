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
    public int getSubtotal(){
        return this.subTotal;
    }
    public String getName(){
        return this.name;
    }
    public int getunitPrice(){
        return this.unitPrice;
    }
    public int getQuantity(){
        return this.quantity;
    }
}
