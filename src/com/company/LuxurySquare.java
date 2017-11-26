public class LuxurySquare extends Square{
    public LuxurySquare(int index){
        super(index);
    }

    @Override
    public void doAction(Player player, Board board){
        System.out.println("I'm in free luxury square\n");
        board.logs += "I'm in free luxury square\n";
        player.amount.setAmount(player.amount.getAmount() - 75);
    }
}
