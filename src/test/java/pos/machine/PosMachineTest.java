package pos.machine;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PosMachineTest {

    @Test
    public void should_return_correct_quantity() {
        PosMachine posMachine = new PosMachine();

        Map<String, Integer> expectedMap = new HashMap<String, Integer>() {{
            put("ITEM000000", 4);
            put("ITEM000004", 3);
            put("ITEM000001", 2);
        }};

        assertEquals(expectedMap, posMachine.loadItemQuantity(ItemDataLoader.loadBarcodes()));
    }

    @Test
    public void should_return_items() {
        PosMachine posMachine = new PosMachine();

        ArrayList<String> itemsCode = new ArrayList<>();
        itemsCode.add("ITEM000000");
        itemsCode.add("ITEM000001");


        List<Item> filteredResult =  posMachine.getAndFilterItems(itemsCode);

        assertEquals(filteredResult.size(), 2);
        Item sample = filteredResult.get(0);

        assertEquals(sample.getBarcode(),"ITEM000000");
        assertEquals(sample.getName(), "Coca-Cola");
        assertEquals(sample.getPrice(), 3);
    }


    @Test
    public void should_return_receipt_line() {
        PosMachine posMachine = new PosMachine();
        String result =  posMachine.generateReceiptLine(new Item("ITEM000000", "Coca-Cola", 3), 5);

        assertEquals("Name: Coca-Cola, Quantity: 5, Unit price: 3 (yuan), Subtotal: 15 (yuan)\n",result);
    }

    @Test
    public void should_return_total_price() {
        PosMachine posMachine = new PosMachine();
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("ITEM000000", "Coca-Cola", 3));
        items.add(new Item("ITEM000001", "Sprite", 3));
        Map<String, Integer> quantityMap = new HashMap<String, Integer>() {{
            put("ITEM000000", 4);
            put("ITEM000001", 2);
        }};
        int result =  posMachine.getTotalPrice(items, quantityMap);

        assertEquals(18, result);
    }

    @Test
    public void should_return_receipt() {
        PosMachine posMachine = new PosMachine();

        String expected = "***<store earning no money>Receipt***\n" +
                "Name: Coca-Cola, Quantity: 4, Unit price: 3 (yuan), Subtotal: 12 (yuan)\n" +
                "Name: Sprite, Quantity: 2, Unit price: 3 (yuan), Subtotal: 6 (yuan)\n" +
                "Name: Battery, Quantity: 3, Unit price: 2 (yuan), Subtotal: 6 (yuan)\n" +
                "----------------------\n" +
                "Total: 24 (yuan)\n" +
                "**********************";

        assertEquals(expected, posMachine.printReceipt(ItemDataLoader.loadBarcodes()));
    }
}
