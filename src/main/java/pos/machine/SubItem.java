package pos.machine;

public class SubItem {

    public final String itemStr;

    public final int totalValue;


    public SubItem(String itemStr, int totalValue) {
        this.itemStr = itemStr;
        this.totalValue = totalValue;
    }

    public String getItemStr(){
        return this.itemStr;
    }

    public int getTotalPrice(){
        return this.totalValue;

    }
}
