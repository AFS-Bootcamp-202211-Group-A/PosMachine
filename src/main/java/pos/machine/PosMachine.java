package pos.machine;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PosMachine {

    public Map<String, Integer> loadItemQuantity(List<String> barcodes){
        return barcodes.stream().collect(Collectors.toMap(Function.identity(), e -> 1, Math::addExact));
    }

    //returns items that has the barcodes
    public List<Item> getAndFilterItems(List<String> barcodes){
        return ItemDataLoader.loadAllItems()
                .stream()
                .filter(item -> barcodes.contains(item.getBarcode()))
                .collect(Collectors.toList());
    }

    public String generateReceiptLine(Item item, Integer quantity){
        return "Name: " + item.getName() + ", Quantity: " + quantity+ ", Unit price: "
                + item.getPrice() + " (yuan), Subtotal: "+ quantity*item.getPrice() + " (yuan)\n";
    }

    public Integer getTotalPrice(List<Item> items, Map<String, Integer> quantityMap){
        return items
                .stream()
                .map(item -> item.getPrice() * quantityMap.get(item.getBarcode()))
                .mapToInt(Integer::intValue).sum();
    }

    public String printReceipt(List<String> barcodes) {
        StringBuilder output = new StringBuilder("***<store earning no money>Receipt***\n");
        Map<String, Integer> quantityMap = loadItemQuantity(barcodes);

        for (Item item : getAndFilterItems(barcodes)){
            output.append(generateReceiptLine(item, quantityMap.get(item.getBarcode())));
        }
        output.append("----------------------\n");
        output.append("Total: ").append(getTotalPrice(getAndFilterItems(barcodes), quantityMap)).append(" (yuan)\n");
        output.append("**********************");
        return output.toString();
    }
}
