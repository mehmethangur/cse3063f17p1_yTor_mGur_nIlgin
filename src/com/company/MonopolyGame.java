//import com.sun.tools.corba.se.idl.constExpr.Or;

import java.util.*;

public class MonopolyGame {

    public static void main(String[] args) {
        Board board = new Board();
        String output = "\t\tMonopoly\n\n\t\tEnter a player count : ";
        Scanner keyboard = new Scanner(System.in);

        System.out.print(output);

        int playerCount = keyboard.nextInt();

        board.players = new Player[playerCount];

        for (int i = 0; i < playerCount; i++) {
            System.out. print("\t\tEnter " + (i + 1) + ". player's name : ");
            Player player = new Player(keyboard.next(), board, i);
            board.players[i] = player;
        }

        System.out.print("\t\tEnter rounds count : ");

        int rounds = keyboard.nextInt();

        boolean cont = true;
        while (cont) {

            for (int i=0; i < playerCount; i++) {
                Player player = board.players[i];
                System.out.print("Turn, " + player.name + "\n");
                System.out.print(player.name + " has " + player.amount.getAmount() +  "\n");

                if (player.isBankruptcy()){
                    System.out.println("I am bankruptcy, you are go on. \n");
                    continue;
                }

                if (hasWinner(board.players, playerCount)){
                    System.out.println("Has winner \n\n\n\n");
                    cont=false;
                    break;
                }


                if (player.location == 11 && player.punnish != 0 && player.amount.getAmount() > 50){
                    System.out.println("I paid money to be free." + player.amount.getAmount());
                    board.squares[11].doAction(player, board);
                    continue;
                }else if (player.location == 11 && player.punnish != 0){
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

                board.squares[player.location].doAction(player, board);

                System.out.println(player.name + " initial round -> " + player.rounds + "\n");

                if (player.rounds >= rounds) {
                    System.out.println("Win! " + player.name + "/n" + player.amount.getAmount() + " \n");
                    cont = false;
                    break;
                }
            }
        }

    }

    private static boolean hasWinner(Player[] players, int playCount){
        int count = 0;
        for(int i=0; i < playCount; i++) {
            Player player = players[i];
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