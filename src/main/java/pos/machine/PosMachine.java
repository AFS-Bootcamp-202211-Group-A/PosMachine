package pos.machine;

import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
    }

    public int countItems(List<String> barcodeList, String barcode){
        int count = 0;
        for (String barcodeItem:barcodeList){
            if (barcodeItem == barcode){
                count ++;
            }
        }
        return count;
    }
}

