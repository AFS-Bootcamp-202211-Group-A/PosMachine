package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        int totalPrice;
        List<Order> receipt = combineItem(barcodes);
        totalPrice = getTotalPrice(receipt);
        return generateReceipt(receipt, totalPrice);
    }

    public String generateReceipt(List<Order> receipt, int totalPrice) {
        StringBuilder result= new StringBuilder();
        result.append("***<store earning no money>Receipt***\n");
        receipt.stream().forEach(order -> {
            result.append("Name: "+order.getName()+", Quantity: "+order.getQuantity()+", Unit price: "+order.getPrice()+" (yuan), Subtotal: "+order.getSubtotalPrice()+" (yuan)\n");
        });
        result.append("----------------------\n");
        result.append("Total: "+totalPrice+" (yuan)\n");
        result.append("**********************");
        return result.toString();
    }

    public int getTotalPrice(List<Order> receipt) {
        int total= receipt.stream().mapToInt(order -> order.getPrice() * order.getQuantity()).sum();
        return total;
    }

    public List<Order> combineItem(List<String> barcodes){
        List<Item> allItem = ItemDataLoader.loadAllItems();
        List<Order> receipt = new ArrayList<>();
        allItem.stream().forEach(item -> {
            int quantity = getQuantity(item.getBarcode(), barcodes);
            int subTotalprice = getSubtotalPrice(quantity,item);
            receipt.add(new Order(item.getName(),quantity,subTotalprice,item.getPrice()));
        });
        return receipt;
    }

    public int getSubtotalPrice(int quantity, Item item) {
        return quantity*item.getPrice();
    }

    public int getQuantity(String barcode, List<String> barcodes){
        return (int) barcodes.stream().filter(item -> item.equals(barcode)).count();
    }
}
