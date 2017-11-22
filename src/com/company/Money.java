public class Money {
    private int amount;

    Money(int value){
        this.amount = value;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = (int)amount;
    }


    public Money getPlayerAmount(Player player){
        return player.amount;
    }
}
