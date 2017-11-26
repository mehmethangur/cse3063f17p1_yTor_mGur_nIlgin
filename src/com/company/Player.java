import javax.xml.stream.FactoryConfigurationError;

public class Player {

    String name;
    int face1;
    int id;
    int face2;
    int punnish;
    Money amount;
    int location;
    int rounds;
    Piece piece;
    boolean isOut;
    private Board  board;

    public Player(String name, Board board, int id){
        this.id = id;
        this.punnish = 0;
        this.isOut = false;
        this.name = name;
        GoSquare square = new GoSquare(0);
        piece = new Piece(square);
        this.board = board;
        this.amount = new Money(200);
    }

    public void TakeTurn(){
        Die die = new Die();
        Die die2 = new Die();
        this.face1 = die.playDice();
        this.face2 = die.playDice();

        System.out.println("Die1 : " + this.face1 + " Die2 : " + this.face2);
        board.logs += "Die1 : " + this.face1 + " Die2 : " + this.face2;
        System.out.println("Initial location: " + this.location);
        board.logs += "Initial location: " + this.location;
    }

    public boolean isDieDouble(){
        System.out.println("Checking is it double or not.. \n\n");
        board.logs += "Checking is it double or not.. \n\n";
        return this.face1 == this.face2;
    }

    public boolean isBankruptcy(){
        if (this.amount.getAmount() <= 0){
            this.isOut = true;
        }
        return this.isOut;
    }

    public void moveSquare(){
        int moveCount=0;

        moveCount += this.face1;
        moveCount += this.face2;

        System.out.println("Go up to " + moveCount + " square.");
        board.logs += "Go up to " + moveCount + " square.";

        this.location += moveCount;

        if (this.location > 39) {
            increaseRound();
        }

        System.out.println("Final location -> " + this.location +"\n");
        board.logs += "Final location -> " + this.location +"\n";

        Square newLocation = board.getSquare(piece.getLocation(), moveCount);
        piece.setLocation(newLocation);

    }

    public int getId(){
        return this.id;
    }

    public int whichRounds(){
        return this.rounds;
    }

    public void increaseRound(){
        this.rounds++;
        this.location = this.location % 40;
    }

    public String playerName(){
        return this.name;
    }

}