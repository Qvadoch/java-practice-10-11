public class ActionLog {
    public enum Type{
        ADD, DELETE, EDIT, BUY
    }
    private Type type;
    private Flower flower;
    private int purchasedQuantity;
    private int index;

    public ActionLog(Type type, Flower flower){
        this.type = type;
        this.flower = flower;
    }

    public ActionLog(Type type, Flower flower, int index) {
        this.type = type;
        this.flower = flower;
        this.index = index;
    }

    public ActionLog(Type type, Flower flower, int purchasedQuantity ,int index) {
        this.type = type;
        this.flower = flower;
        this.purchasedQuantity = purchasedQuantity;
        this.index = index;
    }

    public Type getType() {
        return type;
    }

    public Flower getFlower() {
        return flower;
    }

    public int getIndex() {
        return index;
    }

    public int getPurchasedQuantity() {
        return purchasedQuantity;
    }

    @Override
    public String toString() {
        return "ActionLog{" +
                "type=" + type +
                ", flower=" + flower +
                ", purchasedQuantity=" + purchasedQuantity +
                ", index=" + index +
                '}';
    }
}
