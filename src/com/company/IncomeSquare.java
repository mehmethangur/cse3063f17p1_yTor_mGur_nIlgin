public class IncomeSquare extends Square{
    public IncomeSquare(int index){
        super(index);
    }

    @Override
    public void doAction(Player player, Board board){
        System.out.println("I'm in free income tax square\n");
        board.logs += "I'm in free income tax square\n";
        player.amount.setAmount(player.amount.getAmount() - (player.amount.getAmount() * 0.1));
    }
}
