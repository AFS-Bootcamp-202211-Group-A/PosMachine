package pos.machine;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<Item> allItems = ItemDataLoader.loadAllItems();
        List<Item> items = new ArrayList<>();
        for (String barcode : barcodes) {
            for (Item allItem : allItems) {
                if (barcode.equals(allItem.getBarcode())) {
                    items.add(allItem);
                }
            }
        }

        List<GroupedItems> groupedItems = GroupedItems.fromItems(items);
        String receipt = "***<store earning no money>Receipt***\n";
        receipt += generateItemListString(groupedItems);
        receipt += "----------------------\n";
        receipt += "Total: " + getTotalPrice(groupedItems) + " (yuan)\n";
        receipt += "**********************";

        return receipt;
    }
    String generateItemListString(List<GroupedItems> groupedItems) {
        String itemListString = "";
        for (GroupedItems groupedItem : groupedItems) {
            itemListString += generateItemString(groupedItem);
        }
        return itemListString;
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
