package pos.machine;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        String result = constructRecipt(barcodes);
        return result;
    }
    

}
