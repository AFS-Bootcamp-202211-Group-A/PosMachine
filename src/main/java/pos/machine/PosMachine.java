package pos.machine;

import java.util.List;
import java.util.stream.Collectors;

public class PosMachine {

    public List<Item> getEachItemNamePrice(List<String> barcodes){
        return ItemDataLoader.loadAllItems();
    }
    public String printReceipt(List<String> barcodes) {
        String receipt = constructReceipt(barcodes);
        return receipt;
    }

    public String constructReceipt(List<String> barcodes) {
        String receipt = "";
        receipt += "***<store earning no money>Receipt***\n";
        List<Item> receiptItems = getEachItemNamePrice(barcodes);
        List<ItemSubtotal> receiptItemsWithSubtotal = receiptItems.stream().map(item -> calculateSubtotal(item, barcodes)).collect(Collectors.toList());
        receipt += String.join("", receiptItemsWithSubtotal.stream().map(item -> ("Name: " + item.getName() + ", Quantity: " + item.getQuantity() + ", Unit price: "+ item.getPrice() + " (yuan), Subtotal: " + item.getSubTotal()) + " (yuan)\n").collect(Collectors.toList()) );
        int total = calculateTotal(receiptItemsWithSubtotal);
        receipt += "----------------------\n";
        receipt += "Total: "+ total +" (yuan)\n";
        receipt += "**********************";
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
