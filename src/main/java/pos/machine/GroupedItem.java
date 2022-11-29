package pos.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<GroupedItem> fromItems(List<Item> items) {
        List<GroupedItem> groupedItems = new ArrayList<>();

        List<String> addedBarcodes = new ArrayList<>();

        for (Item item : items) {
            String barcode = item.getBarcode();
            if (addedBarcodes.contains(barcode)) {
                continue;
            }
            groupedItems.add(new GroupedItem(item.getName(), getQuantity(barcode, items), item.getPrice()));
            addedBarcodes.add(barcode);
        }

        return groupedItems;
    }
    static int getQuantity(String barcode, List<Item> items) {
        return items.stream().filter(item -> barcode.equals(item.getBarcode())).collect(Collectors.toList()).size();
    }
}
