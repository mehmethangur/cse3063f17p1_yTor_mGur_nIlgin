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

}