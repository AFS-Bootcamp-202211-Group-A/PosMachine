package pos.machine;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
    }

    public List<BarcodeCount> aggregateBarcodes(List<String> barcodes){
        List<BarcodeCount> barcodeCountList = new ArrayList<BarcodeCount>();
        HashMap<String, BarcodeCount> barcodeCounter = new HashMap<>();
        for(String barcode: barcodes){
            if (barcodeCounter.containsKey(barcodes)){
                barcodeCounter.get(barcode).setQuantity(barcodeCounter.get(barcode).getQuantity()+1);
            } else {
                BarcodeCount barcodeCount= new BarcodeCount(barcode, 1);
                barcodeCounter.put(barcode, barcodeCount);
                barcodeCountList.add(barcodeCount);
            }
        }
        return barcodeCountList;
    }

}
