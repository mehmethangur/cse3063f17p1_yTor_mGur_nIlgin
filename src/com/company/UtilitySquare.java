public class UtilitySquare extends Square{
    private int price;
    private String name;
    private int rent;
    private int owner = -1;

    public UtilitySquare(int index, String name, int price){
        super(index);
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String  name) {
        this.name = name;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }


    @Override
    public void doAction(Player player, Board board){
        if(owner < 0){
            Die die = new Die();
            int face = die.playDice();
            if(face > 4 && player.amount.getAmount() >= price){
                System.out.println(player.name + "bought" + this.getName() + "\n");
                board.logs += player.name + "bought" + this.getName() + "\n";
                owner = player.getId();
                player.amount.setAmount(player.amount.getAmount() - this.price );
            }else{
                System.out.println(player.name + " rolls die smaller than 4 or doesn't have enough money" + "\n");
                board.logs += player.name + "rolls die smaller than 4 or doesn't have enough money" + "\n";
            }
        }else{
            if(owner != player.getId()){
                int rent = (player.face1 + player.face2) * 10;
                System.out.println("I'm in " + this.getName() + "\n");
                player.amount.setAmount(player.amount.getAmount() - rent);
                board.getPlayer(owner).amount.setAmount(player.amount.getAmount() + rent);
            }
        }
    }
}
