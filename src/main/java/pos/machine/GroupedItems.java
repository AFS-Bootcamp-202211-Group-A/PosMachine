package pos.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupedItems {
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

    public GroupedItems(String name, int quantity, int unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotalPrice = quantity * unitPrice;
    }

    public static List<GroupedItems> fromItems(List<Item> items) {
        List<GroupedItems> groupedItems = new ArrayList<>();

        List<String> addedBarcodes = new ArrayList<>();

        for (Item item : items) {
            if (addedBarcodes.contains(item.getBarcode())) {
                continue;
            }
            groupedItems.add(new GroupedItems(item.getName(), getQuantity(item.getBarcode(), items), item.getPrice()));
        }

        return groupedItems;
    }
    static int getQuantity(String barcode, List<Item> items) {
        return items.stream().filter(item -> item.getBarcode().equals(barcode)).collect(Collectors.toList()).size();
    }
}
