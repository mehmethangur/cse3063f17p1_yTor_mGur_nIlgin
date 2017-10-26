package com.company;
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
                System.out.print("your turn , " + player.name);
                player.TakeTurn();

                System.out.println(player.name + "now round -> " + player.rounds);

                if (player.rounds >= rounds) {
                    System.out.println("Win! " + player.name);
                    cont = false;
                    break;
                }
            }
        }

    }
}
