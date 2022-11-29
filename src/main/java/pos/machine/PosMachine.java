package pos.machine;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
    }
    String generateItemString(GroupedItems groupedItem) {
        return "Name: " + groupedItem.getName() + ", Quantity: " + groupedItem.getQuantity() + ", Unit price: " + groupedItem.getUnitPrice() + " (yuan), Subtotal: " + groupedItem.getSubtotalPrice() + " (yuan)\n";
    }
    int getTotalPrice(List<GroupedItems> groupedItems) {
        int totalPrice = 0;
        for (GroupedItems groupedItem : groupedItems) {
            totalPrice += groupedItem.getSubtotalPrice();
        }
        return totalPrice;
    }
}
