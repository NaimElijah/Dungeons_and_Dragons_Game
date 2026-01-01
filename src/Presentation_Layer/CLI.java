package Presentation_Layer;
import Business_Layer.*;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class CLI { // ******  To Do: make it 2 players, KeyListener, System.Timestamp, sprites, new classes, bosses can use different special abilities, etc.  ******
    public static void main(String[] args) {  /////  all the sout's and visuals are here
        boolean is_game_again = true;
        while (is_game_again){
            Scanner reader = new Scanner(System.in);  // this will read the user's input

            System.out.println();
            System.out.println("          **********************          Welcome to Dungeons_and_Dragons Remastered  !!          **********************");
            System.out.println();
            System.out.println("We are 'Legion', a group of fierce warriors, mages and rogues from all over the continent.");
            System.out.println("as the oldest member of this group, I, Yohan glusunbourough, will give you a quick start on what our goal is.");
            System.out.println("We fight for humanity's survival through all the hardships it ever faces, that is exactly why we're here freshman.");
            System.out.println("I believe you've heard about the dungeons below us right now.");
            System.out.println("Many of our comrades have tried and failed to get out of these horrific dungeons alive.");
            System.out.println("These awful dungeons haven't been fully explored yet, therefore they pose an imminent threat to us all !");
            System.out.println("But that's why they brought you here isn't it ? we've heard, we've heard it all, all the stories about you.");
            System.out.println("That's exactly why you're our last hope, a ray of sunlight in these dark times.");
            System.out.println("I see that you're ready to go now...");
            System.out.println("We all wish you the best of luck,");
            System.out.println("The last stand for humanity's sake has just begun...");
            System.out.println("Just remember,");
            System.out.println("Whatever it takes...");   /////////  a line from "Avengers Endgame".
            System.out.println();
            System.out.println();
            System.out.println("Select a player for your journey:"); /// the player selection screen
            System.out.println();
            System.out.println("Warriors:");
            System.out.println(".1. Jon Snow          Health: 300/300          Attack: 30          Defense: 4          Level: 1");  /// stats
            System.out.println("          Experience: 0/50          Cooldown: 0/3");
            System.out.println();
            System.out.println(".2. The Hound          Health: 400/400          Attack: 20          Defense: 6          Level: 1");  /// stats
            System.out.println("          Experience: 0/50          Cooldown: 0/5");
            System.out.println();
            System.out.println("Mages:");
            System.out.println(".3. Melisandre          Health: 100/100          Attack: 5          Defense: 1          Level: 1");  /// stats
            System.out.println("          Experience: 0/50          Mana: 75/300          Spell Power: 15");
            System.out.println();
            System.out.println(".4. Thoros of Myr          Health: 250/250          Attack: 25          Defense: 4          Level: 1");  /// stats
            System.out.println("          Experience: 0/50          Mana: 37/150          Spell Power: 20");
            System.out.println();
            System.out.println("Rogues:");
            System.out.println(".5. Arya Stark          Health: 150/150          Attack: 40          Defense: 2          Level: 1");  /// stats
            System.out.println("          Experience: 0/50          Energy: 100/100");
            System.out.println();
            System.out.println(".6. Bronn          Health: 250/250          Attack: 35          Defense: 3          Level: 1");  /// stats
            System.out.println("          Experience: 0/50          Energy: 100/100");
            System.out.println();
            System.out.println("Hunters:");
            System.out.println(".7. Ygritte          Health: 220/220          Attack: 30          Defense: 2          Level: 1");  /// stats
            System.out.println("          Experience: 0/50          Arrows: 10          Range: 6");
            System.out.println();
            //  first the representation of all the players, ordered by their types, is shown and the player picks a player, receive that in player

            int player = 8;
            while (player > 7 || player < 1) {
                System.out.println("Select a player: (enter 1/2/3/4/5/6/7)"); /// the player selection screen
                player = reader.nextInt();
            }
            System.out.println("You have selected:");    ///////  player selection
            if (player == 1){ System.out.println("Jon Snow");}
            else if(player == 2){System.out.println("The Hound");}
            else if(player == 3){System.out.println("Melisandre");}
            else if(player == 4){System.out.println("Thoros of Myr");}
            else if(player == 5){System.out.println("Arya Stark");}
            else if(player == 6){System.out.println("Bronn");}
            else {System.out.println("Ygritte");}




            File directory = new File("./data/levels_dir");               ///////// counting how many levels are there in the levels directory
            int fileCount= Objects.requireNonNull(directory.list(), "levels_dir not found").length;


            boolean is_enemies = true, is_alive = true;
            Player theplayer = new Warrior(1, 1, "temp", 1, 1, 1, 1);  ////  initializing randomly, won't matter because inside it is set as we wish
            reader.nextLine();

            for (int line = 1; line <= fileCount; line++) {   ////////////////////////////////////////////////////   change in levels
//                Game_Board game = new Game_Board("D:\\levels_dir\\levels_dir\\level" + line +".txt", player);   ////////  gets the file_path and the player and builds
//                Game_Board game = new Game_Board(args[0] + "\\level" + line +".txt", player);   ////////  gets the file_path and the player and builds
                Game_Board game = new Game_Board("./data/levels_dir/level" + line + ".txt", player);


                if ( line > 1 ){
                    theplayer.setCoordinate(game.getThe_player().getCoordinate());    ///  to the starting position in the new level.
                    game.setThe_player(theplayer);    ///  setting the player to the player from the prev stage
                }

                String move = "";

                while (is_enemies && is_alive) {   //////////////////////////////////////////////////////   change in game tick
                    System.out.println(game);     //////  showing the game board
                    System.out.println(game.getThe_player().description());  /////  showing the player's current status

                    while (!move.equals("w") && !move.equals("a") && !move.equals("d") && !move.equals("s") && !move.equals("e") && !move.equals("q")) {
                        System.out.println("Enter your move:");
                        move = reader.nextLine();
                    }
                    System.out.println();

                    int curr_playerX = game.getThe_player().getCoordinate().getX_coor();
                    int curr_playerY = game.getThe_player().getCoordinate().getY_coor();

                    if(move.equals("w")){    //////  up !!                     ///////  can easily make it 2 or more players
                        game.getThe_player().move(game.getArrays_Board().get(curr_playerY-1).get(curr_playerX), game);
                    } else if (move.equals("s")) {    ////// down !!
                        game.getThe_player().move(game.getArrays_Board().get(curr_playerY+1).get(curr_playerX), game);
                    }else if (move.equals("a")) {    ////// left !!
                        game.getThe_player().move(game.getArrays_Board().get(curr_playerY).get(curr_playerX-1), game);
                    }else if (move.equals("d")) {    ////// right !!
                        game.getThe_player().move(game.getArrays_Board().get(curr_playerY).get(curr_playerX+1), game);
                    }else if (move.equals("e")) {    ////// special ability !!
                        game.getThe_player().Ability_Cast(game);
                    }
//                    else if (move.equals("q")) { }  ////// do nothing !! can delete this because it will do nothing

                    //////  now do the on_tick_do's of all the units in the Game_Board game
                    game.game_tick();
                    ////////////////////////////////////////////////////////////////////////////////////////////////////////


                    ////// now checking if the game goes on or not and changing is_alive and is_monsters accordingly
                    is_enemies = game.getMonsters().size() > 0 || game.getTraps().size() > 0;
                    is_alive = game.getThe_player().getHealth().getHealth_amount() > 0;
                    ////////////////////////////////////////////////////////////////////////////////////////////////////////
                    move = "";
                }
                if (!is_alive){
                    System.out.println(game);     //////  showing the game board with the player's description one last time
                    System.out.println(game.getThe_player().description());
                    break;
                }
                is_enemies = true;
                if (line < fileCount) {
                    System.out.println();
                    System.out.println("          *******************        You've reached Stage " + (line + 1) + ", keep going lad you've got this !!!        *******************");
                    System.out.println();
                }
                theplayer = game.getThe_player();  ////  saving the player for the next level
            }

            if (!is_alive) {
                System.out.println();
                System.out.println("          ****************************************        Game over        ****************************************");
                System.out.println();
            }else {
                System.out.println();
                System.out.println("          ****************************************        You Won !!        ****************************************");
                System.out.println();
            }

            System.out.println();
            System.out.println("Do you want to play again ?  (enter 'yes' to play again, enter anything else to quit)");
            String yes_or_no = reader.nextLine();
            is_game_again = yes_or_no.equals("yes");
        }
    }
}
