package pos.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupedItem {
    String name;
    int quantity;
    int unitPrice;
    int subtotalPrice;

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getSubtotalPrice() {
        return subtotalPrice;
    }

    public GroupedItem(String name, int quantity, int unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotalPrice = quantity * unitPrice;
    }
    public static List<GroupedItem> fromBarcodes(List<String> barcodes) {
        List<Item> allItems = ItemDataLoader.loadAllItems();
        List<GroupedItem> groupedItems = new ArrayList<>();
        List<String> addedBarcodes = new ArrayList<>();

        for (String barcode : barcodes) {
            if (addedBarcodes.contains(barcode)) {
                continue;
            }
            for (Item item : allItems) {
                if (barcode.equals(item.getBarcode())) {
                    groupedItems.add(new GroupedItem(item.getName(), Collections.frequency(barcodes, barcode), item.getPrice()));
                    addedBarcodes.add(barcode);
                    break;
                }
            }
        }
        return groupedItems;
    }
}
