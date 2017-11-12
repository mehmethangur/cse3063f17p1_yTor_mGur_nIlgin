public class GoSquare extends Square{
    public GoSquare(int index){
        super(index);
    }

    @Override
    public void doAction(Player player, Board board){
        int currentMoney = player.amount.getAmount() + 200;
        player.amount.setAmount(currentMoney);
    }
}
