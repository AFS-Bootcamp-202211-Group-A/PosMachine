package pos.machine;

import java.util.List;

public class PosMachine {

    public List<Item> getEachItemNamePriceQuantity(List<String> barcodes){
        return ItemDataLoader.loadAllItems();
    }
    public String printReceipt(List<String> barcodes) {
        return null;
    }
}
