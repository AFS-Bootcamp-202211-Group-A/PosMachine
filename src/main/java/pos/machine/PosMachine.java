package pos.machine;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PosMachine {

    public List<Item> getEachItemNamePrice(List<String> barcodes){
        return ItemDataLoader.loadAllItems();
    }
    public String printReceipt(List<String> barcodes) {
        String receipt = constructReceipt(barcodes);
        return receipt;
    }

    public String constructReceipt(List<String> barcodes){
        String receipt = "";
        return receipt;
    }

    public ItemSubtotal calculateSubtotal(Item item, List<String> barcodes){
        int quantity = (int) barcodes.stream().filter(itemCode -> itemCode.equals(item.getBarcode())).count();
        int subTotal = quantity*item.getPrice();
        ItemSubtotal receiptItemsWithSubtotal = new ItemSubtotal(item.getBarcode(), item.getName(), item.getPrice(), quantity, subTotal );
        return receiptItemsWithSubtotal;
    }

    public int calculateTotal(List<ItemSubtotal> receiptItemsWithSubtotal){
        int total = receiptItemsWithSubtotal.stream().mapToInt(itemSubtotal -> itemSubtotal.getSubTotal()).sum();
        return total;
    }

}
