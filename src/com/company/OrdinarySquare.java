public class OrdinarySquare extends Square{
    public OrdinarySquare(int index){
        super(index);
    }

    @Override
    public void doAction(Player player, Board board){
        System.out.println("I'm in Ordinary square");
        board.logs += "I'm in Ordinary square";
    }
}
