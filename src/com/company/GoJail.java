public class GoJail extends Square{
    public GoJail(int index){
        super(index);
    }

    @Override
    public void doAction(Player player, Board board){
        System.out.println("\n\nI'm going to jail\n\n");
        player.location = 11;
        player.punnish = 3;
    }
}
