package com.company;

import static  org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class MonopolyGameTest {

    @Test
    void test_die_not_equal_zero(){
        Die die = new Die();
        int face = die.playDice();
        assertEquals(true, face != 0 );
    }


    @Test
    void test_die_face(){
        Die die = new Die();
        int face1 = die.playDice();
        assertEquals(true, face1 <=6 );
    }

    @Test
    void test_die_face_total(){
        Die die = new Die();
        int face1 = die.playDice();
        Die die2 = new Die();
        int face2 = die2.playDice();
        assertEquals(true, (face1 + face2) <= 12 );
    }

    @Test
    void test_player_starting_round_count(){
        Board board = new Board();
        Player player = new Player("Test user", board);
        assertEquals(true, player.whichRounds() == 0);
    }

    @Test
    void test_player_movement(){
        Board board = new Board();
        Player player = new Player("Test user", board);
        int movement = player.TakeTurn();
        assertEquals(true, player.location == movement);
    }

    @Test
    void test_player_increase_round(){
        Board board = new Board();
        Player player = new Player("Test user", board);
        player.location = 39;
        int movement = player.TakeTurn(); // now it must be increare round to 1
        assertEquals(true, player.whichRounds() == 1);
    }

    @Test
    void test_player_increase_round_turn_to_start_point(){
        Board board = new Board();
        Player player = new Player("Test user", board);
        player.location = 39;
        int movement = player.TakeTurn(); // now it must turn to start point
        assertEquals(true, player.location == (39 + movement) % 40);
    }

    @Test
    void test_player_isBankruptcy(){
        Player player = new Player();
        player.amount.setAmount(this.amount.getAmount() - 1000);
        assertEquals(true, player.isBankruptcy());
    }

    @Test
    void test_is_die_double(){
        Player player = new Player();
        player.face1 = 2;
        player.face2 = 2;
        assertEquals(true, player.isDieDouble());
    }

    @Test
    void test_go_jail(){
        Player player = new Player();
        Board board = new Board();

        player.location = 10;

        GoJail move = new GoJail(10);
        move.doAction(player, board);
        assertEquals(true, player.location == 30);
        assertEquals(true, player.punnish == 3);
    }

    @Test
    void test_go_free_parking(){
        Player player = new Player();
        Board board = new Board();

        int initialMoney = player.amount.getAmount();

        FreeParkingSquare parking = new FreeParkingSquare(20);
        parking.doAction(player, board);

        assertEquals(true, player.amount.getAmount() == initialMoney);
    }

    @Test
    void test_go_luxury(){
        Player player = new Player();
        Board board = new Board();

        int initialMoney = player.amount.getAmount();
        int lastMoney = player.amount.getAmount() - 75;

        LuxurySquare luxury = new LuxurySquare(38);
        luxury.doAction(player, board);

        assertEquals(false, lastMoney == player.amount.getAmount());
    }

    @Test
    void test_income_square(){
        Player player = new Player();
        Board board = new Board();

        int initilMoney = player.amount.getAmount();
        int lastMoney = initilMoney - (initilMoney * 0.1);

        IncomeSquare income = new IncomeSquare(4);
        income.doAction(player, board);
        assertEquals(true, player.amount.getAmount() == lastMoney);

    }


}