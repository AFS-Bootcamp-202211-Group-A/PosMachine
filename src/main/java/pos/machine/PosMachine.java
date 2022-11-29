package pos.machine;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

    public List<BarcodeGroup> getBarcodeGroupListFromDB(List<BarcodeCount> barcodeCountList) {
        List<Item> itemList = ItemDataLoader.loadAllItems();
        List<BarcodeGroup> barcodeGroupList = barcodeCountList.stream().map((barcodeCount -> {
            Item barcodeItem = itemList.stream()
                    .filter( item -> item.getBarcode().equals(barcodeCount.getBarcode()))
                    .collect(Collectors.toList()).get(0);
            return new BarcodeGroup(
                    barcodeCount.getBarcode(), barcodeItem.getName(), barcodeCount.getQuantity(), barcodeItem.getPrice()
            );
        })).collect(Collectors.toList());
        return barcodeGroupList;
    }

    public String generateBarcodeGroupListSummary(List<BarcodeGroup> barcodeGroupList) {
        String barcodeGroupListSummary = "";
        for( BarcodeGroup barcodeGroup : barcodeGroupList) {
            barcodeGroupListSummary += "Name: "+barcodeGroup.name + ", Quantity: "+ barcodeGroup.quantity +
                    ", Unit price: "+ barcodeGroup.price + " (yuan), Subtotal: " + (barcodeGroup.price*barcodeGroup.quantity)+" (yuan)\n";
        }
        return  barcodeGroupListSummary.trim();
    }

    public int calculateTotal(List<BarcodeGroup> barcodeGroupList) {
        int total = 0;
        for(BarcodeGroup barcodeGroup: barcodeGroupList) {
            total += barcodeGroup.price * barcodeGroup.quantity;
        }
        return total;
    }
}
