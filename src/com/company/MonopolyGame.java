import java.util.*;

public class MonopolyGame {

    public static void main(String[] args) {
        Board board = new Board();
        String output = "\t\tMonopoly\n\n\t\tEnter a player count : ";
        Scanner keyboard = new Scanner(System.in);

        System.out.print(output);

        int playerCount = keyboard.nextInt();

        ArrayList players = new ArrayList(playerCount);

        for (int i = 0; i < playerCount; i++) {
            System.out. print("\t\tEnter " + (i + 1) + ". player's name : ");
            Player player = new Player(keyboard.next(), board);
            players.add(player);
        }

        System.out.print("\t\tEnter rounds count : ");

        int rounds = keyboard.nextInt();

        boolean cont = true;
        while (cont) {

            for (Iterator iter = players.iterator(); iter.hasNext(); ) {
                Player player = (Player) iter.next();
                System.out.print("Your turn, " + player.name + "\n");
                System.out.print(player.name + " has " + player.amount.getAmount() +  "\n");

                if (player.isBankruptcy()){
                    System.out.println("I am bankruptcy, you are go on. \n");
                    continue;
                }

                if (hasWinner(players, playerCount)){
                    System.out.println("Has winner \n\n\n\n");
                    cont=false;
                    break;
                }


                if (player.location == 30 && player.punnish != 0 && player.amount.getAmount() > 50){
                    System.out.println("I paid money to be free." + player.amount.getAmount());
                    Jail jail = new Jail(30);
                    jail.doAction(player, board);
                    continue;
                }else if (player.location == 30 && player.punnish != 0){
                    System.out.println("I have no money." + player.amount.getAmount());
                    player.TakeTurn();
                    if (player.isDieDouble()){
                        System.out.println("It's double dice, I am free.");
                        player.punnish = 0;
                        continue;
                    }else{
                        System.out.println("I could not dice double.");
                        player.punnish--;
                        continue;
                    }
                }

                player.TakeTurn();
                player.moveSquare();

                if(player.location == 0){
                    GoSquare go = new GoSquare(0);
                    go.doAction(player, board);
                }
                else if(player.location == 10) { //gojail
                    GoJail move = new GoJail(30);
                    move.doAction(player, board);
                }else if (player.location == 20) {
                    FreeParkingSquare free = new FreeParkingSquare(20);
                    free.doAction(player, board);
                }else if (player.location == 4) {
                    IncomeSquare income = new IncomeSquare(4);
                    income.doAction(player, board);
                }else if (player.location == 38) {
                    LuxurySquare luxury = new LuxurySquare(38);
                    luxury.doAction(player, board);
                }
                System.out.println(player.name + "initial round -> " + player.rounds + "\n");

                if (player.rounds >= rounds) {
                    System.out.println("Win! " + player.name + "/n" + player.amount.getAmount() + " \n");
                    cont = false;
                    break;
                }
            }
        }

    }

    private static boolean hasWinner(ArrayList players, int playCount){
        int count = 0;
        for (Iterator iter = players.iterator(); iter.hasNext(); ) {
            Player player = (Player) iter.next();
            if (player.isOut){
                count++;
            }
        }

        if(count == (playCount - 1)){
            return true;
        }
        return false;
    }
}