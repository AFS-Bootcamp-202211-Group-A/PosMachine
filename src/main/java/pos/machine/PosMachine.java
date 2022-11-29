package pos.machine;

import java.util.List;

public class PosMachine {

    public List<Item> getEachItemNamePriceQuantity(List<String> barcodes){
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
}
