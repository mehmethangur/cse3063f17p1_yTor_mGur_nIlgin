public class Jail extends Square{
    public Jail(int index){
        super(index);
    }

    @Override
    public void doAction(Player player, Board board){
        System.out.println("I'm in jail \n");
        player.amount.setAmount(player.amount.getAmount() - 50);
        player.punnish = 0;
    }
}
