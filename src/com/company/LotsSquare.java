public class LotsSquare extends Square{

    private int price;
    public String name;
    int owner=-1;
    private int rent;


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }


    public LotsSquare(int index, int price, int rent){
        super(index);
        this.price = price;
        this.rent = rent;
    }


    @Override
    public void doAction(Player player, Board board){
        int totalFace = 0;
        if(owner < 0){
            Die die = new Die();
            totalFace += die.playDice();
            totalFace += die.playDice();
            if(totalFace > 4 && player.amount.getAmount() >= price){
                System.out.println(player.name + " bought " + this.getName() + "\n");
                owner = player.getId();
                player.amount.setAmount(player.amount.getAmount() - this.price );
            }else
                System.out.println(player.name + " rolls die smaller than 4 or doesn't have enough money " + this.getName() + "\n");
        }else{
            if(owner != player.getId()){
                System.out.println("I'm in " + this.getName() + "\n");
                player.amount.setAmount(player.amount.getAmount() - this.rent);
                board.getPlayer(owner).amount.setAmount(player.amount.getAmount() + this.rent);
            }
        }

    }
}
