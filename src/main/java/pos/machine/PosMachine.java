package pos.machine;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        String result = constructRecipt(barcodes);
        return result;
    }

    public List<Item> getAllItems() {
        return ItemDataLoader.loadAllItems();
    }

    public SubItem constructSubItem(Item item, List<String> barcodes){
        int itemQuantity = getQuantity(item, barcodes);
        int itemSubTotal = itemQuantity*item.getPrice();
        String str = "Name: " + item.getName() + ", Quantity: " + itemQuantity + ", Unit price: "+ item.getPrice() + " (yuan), Subtotal: " + itemSubTotal + " (yuan)\n";
        SubItem subItem = new SubItem(str, itemSubTotal);
        return subItem;
    }

}
