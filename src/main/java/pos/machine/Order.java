package pos.machine;

public class Order {
    private final String name;
    private final int quantity;
    private final int subtotalPrice;
    private final int price;
    public Order(String name,int quantity, int subTotalPrice, int price){
        this.name = name;
        this.quantity = quantity;
        this.subtotalPrice = subTotalPrice;
        this.price = price;
    }
    public String getName(){ return name;}
    public int getPrice(){ return price;}
    public int getSubtotalPrice(){ return subtotalPrice;}
    public int getQuantity(){ return quantity;}
}
