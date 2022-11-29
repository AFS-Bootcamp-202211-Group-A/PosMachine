package pos.machine;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        int totalPrice;
        List<Order> receipt = combineItem(barcodes);
        totalPrice = getTotalPrice(receipt);
        return null;
    }

    private int getTotalPrice(List<Order> receipt) {
        int total=0;
        receipt.stream().forEach(order -> {
            total += order.getPrice() * order.getQuantity();
        });
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

    private int getSubtotalPrice(int quantity, Item item) {
        return quantity*item.getPrice();
    }

    public int getQuantity(String barcode, List<String> barcodes){
        return (int) barcodes.stream().filter(item -> item.equals(barcode)).count();
    }
}
