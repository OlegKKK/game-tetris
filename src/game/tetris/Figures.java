package game.tetris;

import java.awt.Color;

public class Figures {
    final static short SIZE = 25;
    final static Color[] COLORS = {new Color(64,0,128), Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.WHITE};
    final static boolean[][][] FIGURES =
            {
                    {
                            {false, false, false, false},	//....
                            {true , true , true , false},	//###.
                            {false, false, true , false},	//..#.
                            {false, false, false, false}	//....
                    },
                    {
                            {false, false, false, false},	//....
                            {true , true , true , false},	//###.
                            {false, true , false, false},	//.#..
                            {false, false, false, false}	//....
                    },
                    {
                            {false, false, false, false},	//....
                            {false, false, true , false},	//..#.
                            {true , true , true , false},	//###.
                            {false, false, false, false}	//....
                    },
                    {
                            {false, false, false, false},	//....
                            {true , true , true , true },	//####
                            {false, false, false, false},	//....
                            {false, false, false, false}	//....
                    },
                    {
                            {false, false, false, false},	//....
                            {false, true , true , false},	//.##.
                            {false, true , true , false},	//.##.
                            {false, false, false, false}	//....
                    },
                    {
                            {false, false, false, false},	//....
                            {false, true , true , false},	//.##.
                            {true , true , false, false},	//##..
                            {false, false, false, false}	//....
                    },
                    {
                            {false, false, false, false},	//....
                            {true , true , false, false},	//##..
                            {false, true , true , false},	//.##.
                            {false, false, false, false}	//....
                    }
            };
}
