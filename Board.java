import java.util.*;

public class Board {
    ArrayList squares = new ArrayList(40);

    public Board(){
        createSquares();
    }

    public Square getSquare(Square start, int distance)
    {
        int endIndex = (start.getIndex() + distance) % 40;
        return (Square) squares.get(endIndex);
    }

    public Square getStartSquare()
    {
        return (Square) squares.get(0);
    }

    public void createSquares(){
        for (int i=0; i < 40 ; i++){
            Square square = new OrdinarySquare(i);
            squares.add(square);
        }
    }
}