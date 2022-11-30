package pos.machine;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {

        List <SummarizedItem> summarizedItems = summarizeItems(barcodes);
        int total = getTotal(summarizedItems);
        return generateReceipt(summarizedItems,total);
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
    public int getPrice(String barcode){
        int price =0;
        List<Item> loadedItem = pos.machine.ItemDataLoader.loadAllItems();
        for (Item item:loadedItem){
            if (item.getBarcode().equals(barcode)){
                price = item.getPrice();
            }
        }
        return price;
    }
    public String getName(String barcode){
        String name = "";
        List<Item> loadedItem = pos.machine.ItemDataLoader.loadAllItems();
        for (Item item:loadedItem){
            if (item.getBarcode()==barcode){
                name = item.getName();
            }
        }
       return name;
    }

    public int getSubTotal(int quantity, int price){
        return quantity*price;
    }

    public List<SummarizedItem> summarizeItems(List<String> barcodeList){
        List<String> uniqueItemList = barcodeList.stream().distinct().collect(Collectors.toList());
        List<SummarizedItem> summarizedItems= new ArrayList<>();
        for (String barcode : uniqueItemList){
            int quantity = countItems(barcodeList,barcode);
            SummarizedItem summarizedItem = new SummarizedItem(barcode,quantity);
            String name = getName(barcode);
            int unitPrice = getPrice(barcode);
            summarizedItem.setNameAndPrice(name,unitPrice);
            int subtotal = getSubTotal(quantity,unitPrice);
            summarizedItem.setSubtotal(subtotal);

            summarizedItems.add(summarizedItem);
        }
        return summarizedItems;
    }
    public int getTotal(List<SummarizedItem> summarizedItems){
        int total = 0;
        for (SummarizedItem summarizedItem:summarizedItems){
            total += summarizedItem.getSubtotal();
            
        }
        return total;
    }
    public String generateReceiptLine(SummarizedItem summarizedItem){
        String receiptLine = "";
        receiptLine +="Name: "+summarizedItem.getName()+", Quantity: "+summarizedItem.getQuantity()+", Unit price: "+summarizedItem.getunitPrice()+" (yuan), Subtotal: "+summarizedItem.getSubtotal()+" (yuan)\n";
        return receiptLine;
    }

    public String generateReceipt(List<SummarizedItem> summarizedItems, int total){
        String receipt ="";
        receipt+="***<store earning no money>Receipt***\n";
        for (SummarizedItem summarizedItem:summarizedItems){
            receipt += generateReceiptLine(summarizedItem);
        }
        receipt +="----------------------\n" ;
        receipt +="Total: "+total+" (yuan)\n" ;
        receipt +="**********************";
        return receipt;
    }




}

