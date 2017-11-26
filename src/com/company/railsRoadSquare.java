public class railsRoadSquare extends Square{
    int price;
    String name;
    int owner=-1;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String getName() {
        return "RailRoad" + this.getIndex();
    }

    public railsRoadSquare(int index, int price){
        super(index);
        this.price = price;

    }

    @Override
    public void doAction(Player player, Board board){
        if(owner < 0){
            Die die = new Die();
            int face = die.playDice();
            if(face > 4 && player.amount.getAmount() >= price){
                System.out.println(player.name + " bought " + this.getName() + "\n");
                board.logs += player.name + " bought " + this.getName() + "\n";
                owner = player.getId();
            }else{
                System.out.println(player.name + "rolls die smaller than 4 or doesn't have enough money" + "\n");
                board.logs += player.name + "rolls die smaller than 4 or doesn't have enough money" + "\n";
            }
        }else{
            if(owner != player.getId()){
                int rent= ( (player.face1 + player.face2) * 5) + 25;
                System.out.println("I'm in " + this.getName() + "\n");
                board.logs += "I'm in " + this.getName() + "\n";
                player.amount.setAmount(player.amount.getAmount() - rent);
                board.getPlayer(owner).amount.setAmount(player.amount.getAmount() + rent);
            }
        }


    }
}
