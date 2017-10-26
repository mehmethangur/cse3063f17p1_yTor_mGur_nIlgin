package monopolygame;

public class Player {
	
	String name;
	int location;
	int rounds;
	Piece piece;
	private Board  board;

	public Player(String name, Board board){
		this.name = name;
		Square square = new Square(0);
		piece = new Piece(square);
		this.board = board;
	}
	
	public void TakeTurn(){
		int moveCount=0;
		Die die = new Die();
		Die die2 = new Die();
		moveCount += die.playDice();
		moveCount += die2.playDice();
		System.out.println(" \t Die1 : " + die.face + " Die2 : " + die2.face);
		System.out.println("initial location -> " + this.location);
		this.location += moveCount;

		if (this.location > 39) {
			increaseRound();
		}

		System.out.println("final location -> " + this.location);

		Square newLocation = board.getSquare(piece.getLocation(), moveCount);
		piece.setLocation(newLocation);
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
