public abstract class Square {
    private int index;

    public Square (int number){
        setIndex(number);
    }

    public int getIndex(){
        return this.index;
    }
    public String getName(){
        return "Square" + this.index;
    }


    public void setIndex(int index){
        this.index = index;
    }

    public abstract void doAction(Player player, Board board);

}

