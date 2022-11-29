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

    public int getQuantity(Item item, List<String> barcodes){
        return (int) barcodes.stream().filter(itemCode -> itemCode.equals(item.getBarcode())).count();
    }

    public int calculateTotal(List<SubItem> subItemStream){
        return subItemStream.stream().mapToInt(subItem -> subItem.getTotalPrice()).sum();
    }

}
