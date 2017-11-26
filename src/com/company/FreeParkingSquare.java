public class FreeParkingSquare extends Square{
    public FreeParkingSquare(int index){
        super(index);
    }

    @Override
    public void doAction(Player player, Board board){
        System.out.println("I'm in free parking square\n");
        board.logs += "I'm in free parking square\n";
    }
}
