package Display;

/**<h3>Display Class Grid</h3>
 * Provides methods to create, store, and display characters using an array of bytes for {@link Display}
 * <p>Contains</p>
 * <pre>
 *     {@link Grid#Grid(byte[]) constructor}
 *     {@link Grid#Grid blank character constructor}
 *
 *     {@link Grid#toString() .toString()} -> String
 *     {@link Grid#hashCode() .hashCode()} -> int
 *     {@link Grid#getBoolean(int, int) .getBoolean(int, int)} -> boolean
 *     {@link Grid#check() .check()} -> boolean
 *
 *     {@link Grid#NUM(int) .NUM(int)} static -> Grid
 *     {@link Grid#CAP(int) .CAP(int)} static -> Grid
 * </pre>
 * @author William Wu
 * <p>All rights reserved</p>
 * @version 1.4 @Oct 28, 2024
 * <p>Upgrades: static declared final</p>*/
public final class Grid{
    /**The defined number of rows the grid can represent
     * @value 9*/
    public static final int rows = 9;
    /**The defined number of columns the grid can represent
     * @value 9*/
    public static final int cols = 5;
    private byte[] grid = new byte[]{0b000001, 0b000001, 0b000001, 0b000001, 0b000001, 0b000001, 0b000001, 0b000001, 0b000001};

    /**Constructor by an array (length 9) of bytes represent the grid
     * @param grid the array of bytes to represent the grid
     * @throws IllegalArgumentException for array length other than 9 or if the bytes did not pass the {@link Grid#check() check}*/
    public Grid(byte[] grid){
        if(grid.length != 9)throw new IllegalArgumentException("Grid rows must be 9");
        if(!check())throw new IllegalArgumentException("check bit is wrong");
        this.grid = grid;
    }
    /**Constructor: Empty character {@code ' '}*/
    public Grid(){}
    /**Display the grid with {@code '█'} for true and {@code ' '} for false*/
    public String toString() {
        String result = "";
        for (int i = 0; i < rows; i++) {
            for (int j = cols - 1; j >= 0; j--) {
                result += (grid[i] & (1 << (j + 1))) != 0 ? "██" : "  ";
            }
            result += '\n';
        }
        return result;
    }
    /**Return the hashCode of the character
     * @return hashcode (int)*/
    public int hashCode(){
        int code = 1;
        for (int i = 0; i < Grid.rows; i++) {
            for (int j = 0; j < Grid.cols; j++) {
                if(this.getBoolean(i, j))code +=7;
                code*=31;
            }
        }
        return code;
    }
    /**The boolean value of the grid in a specific position specified by row and column
     * @param column the column position of the boolean value to access
     * @param row the row position of the boolean value to access
     * @return value of the grid at the position (boolean)*/
    public boolean getBoolean(int row, int column){
        if(row < 0){row = 0;}else if(row >= rows){row = rows-1;}
        if(column < 0){column = 0;}else if(column >= cols){column = cols-1;}
        return(grid[row] & (1 << (cols-column))) != 0;
    }
    /**Check whether the grid is legal
     * @return true if the byte representation of the grid is legal*/
    public boolean check(){
        for (int i = 0; i < rows; i++) {
            int result = 0;
            for (int j = cols; j >= 0; j--) {
                result += (grid[i] & (1 << (j))) != 0 ? 1 : 0;
            }
            if(result%2==0)return false;
        }
        return true;
    }
    public static final Grid SPACE = new Grid(new byte[]{0b000001, 0b000001, 0b000001, 0b000001, 0b000001, 0b000001, 0b000001, 0b000001, 0b000001});
    public static final Grid BLACK = new Grid(new byte[]{0b111110, 0b111110, 0b111110, 0b111110, 0b111110, 0b111110, 0b111110, 0b111110, 0b111110});
    public static final Grid CAP_0 = new Grid(new byte[]{0b011100, 0b100011, 0b100110, 0b101010, 0b101010, 0b101010, 0b110010, 0b100011, 0b011100});
    public static final Grid CAP_1 = new Grid(new byte[]{0b001000, 0b011001, 0b101001, 0b001000, 0b001000, 0b001000, 0b001000, 0b001000, 0b111110});
    public static final Grid CAP_2 = new Grid(new byte[]{0b011100, 0b100011, 0b000010, 0b000010, 0b000100, 0b001000, 0b010000, 0b100000, 0b111110});
    public static final Grid CAP_3 = new Grid(new byte[]{0b011100, 0b100011, 0b000010, 0b000010, 0b011100, 0b000010, 0b000010, 0b100011, 0b011100});
    public static final Grid CAP_4 = new Grid(new byte[]{0b000100, 0b010101, 0b010101, 0b100101, 0b111110, 0b000100, 0b000100, 0b000100, 0b000100});
    public static final Grid CAP_5 = new Grid(new byte[]{0b111101, 0b100000, 0b100000, 0b100000, 0b011100, 0b000010, 0b000010, 0b000010, 0b111101});
    public static final Grid CAP_6 = new Grid(new byte[]{0b001101, 0b010000, 0b100000, 0b100000, 0b111101, 0b100011, 0b100011, 0b100011, 0b011100});
    public static final Grid CAP_7 = new Grid(new byte[]{0b111110, 0b000010, 0b000010, 0b000100, 0b000100, 0b001000, 0b001000, 0b010000, 0b010000});
    public static final Grid CAP_8 = new Grid(new byte[]{0b011100, 0b100011, 0b100011, 0b100011, 0b011100, 0b100011, 0b100011, 0b100011, 0b011100});
    public static final Grid CAP_9 = new Grid(new byte[]{0b011100, 0b100011, 0b100011, 0b100011, 0b011100, 0b000100, 0b001000, 0b010000, 0b100000});
    public static final Grid CAP_A = new Grid(new byte[]{0b001000, 0b010101, 0b010101, 0b100011, 0b100011, 0b111110, 0b100011, 0b100011, 0b100011});
    public static final Grid CAP_B = new Grid(new byte[]{0b111101, 0b100011, 0b100011, 0b100011, 0b111101, 0b100011, 0b100011, 0b100011, 0b111101});
    public static final Grid CAP_C = new Grid(new byte[]{0b001110, 0b010000, 0b100000, 0b100000, 0b100000, 0b100000, 0b100000, 0b010000, 0b001110});
    public static final Grid CAP_D = new Grid(new byte[]{0b111000, 0b100101, 0b100011, 0b100011, 0b100011, 0b100011, 0b100011, 0b100101, 0b111000});
    public static final Grid CAP_E = new Grid(new byte[]{0b111110, 0b100000, 0b100000, 0b100000, 0b111101, 0b100000, 0b100000, 0b100000, 0b111110});
    public static final Grid CAP_F = new Grid(new byte[]{0b111110, 0b100000, 0b100000, 0b100000, 0b111101, 0b100000, 0b100000, 0b100000, 0b100000});
    public static final Grid CAP_G = new Grid(new byte[]{0b001110, 0b010011, 0b100000, 0b100000, 0b100000, 0b100110, 0b100011, 0b010011, 0b001110});
    public static final Grid CAP_H = new Grid(new byte[]{0b100011, 0b100011, 0b100011, 0b100011, 0b111110, 0b100011, 0b100011, 0b100011, 0b100011});
    public static final Grid CAP_I = new Grid(new byte[]{0b111110, 0b001000, 0b001000, 0b001000, 0b001000, 0b001000, 0b001000, 0b001000, 0b111110});
    public static final Grid CAP_J = new Grid(new byte[]{0b011111, 0b000100, 0b000100, 0b000100, 0b000100, 0b000100, 0b100101, 0b010101, 0b001000});
    public static final Grid CAP_K = new Grid(new byte[]{0b100011, 0b100101, 0b100101, 0b101001, 0b101001, 0b110001, 0b101001, 0b100101, 0b100011});
    public static final Grid CAP_L = new Grid(new byte[]{0b100000, 0b100000, 0b100000, 0b100000, 0b100000, 0b100000, 0b100000, 0b100000, 0b111110});
    public static final Grid CAP_M = new Grid(new byte[]{0b100011, 0b100011, 0b110111, 0b110111, 0b101010, 0b101010, 0b101010, 0b101010, 0b100011});
    public static final Grid CAP_N = new Grid(new byte[]{0b100011, 0b110010, 0b110010, 0b101010, 0b101010, 0b101010, 0b100110, 0b100110, 0b100011});
    public static final Grid CAP_O = new Grid(new byte[]{0b001000, 0b010101, 0b100011, 0b100011, 0b100011, 0b100011, 0b100011, 0b010101, 0b001000});
    public static final Grid CAP_P = new Grid(new byte[]{0b111101, 0b100011, 0b100011, 0b100011, 0b111101, 0b100000, 0b100000, 0b100000, 0b100000});
    public static final Grid CAP_Q = new Grid(new byte[]{0b001000, 0b010101, 0b100011, 0b100011, 0b100011, 0b101010, 0b101010, 0b011100, 0b000010});
    public static final Grid CAP_R = new Grid(new byte[]{0b111101, 0b100011, 0b100011, 0b100011, 0b111101, 0b110001, 0b101001, 0b100101, 0b100011});
    public static final Grid CAP_S = new Grid(new byte[]{0b001110, 0b010000, 0b100000, 0b100000, 0b011100, 0b000010, 0b000010, 0b000100, 0b111000});
    public static final Grid CAP_T = new Grid(new byte[]{0b111110, 0b001000, 0b001000, 0b001000, 0b001000, 0b001000, 0b001000, 0b001000, 0b001000});
    public static final Grid CAP_U = new Grid(new byte[]{0b100011, 0b100011, 0b100011, 0b100011, 0b100011, 0b100011, 0b100011, 0b100011, 0b011100});
    public static final Grid CAP_V = new Grid(new byte[]{0b100011, 0b100011, 0b100011, 0b100011, 0b010101, 0b010101, 0b010101, 0b001000, 0b001000});
    public static final Grid CAP_W = new Grid(new byte[]{0b100011, 0b100011, 0b101010, 0b101010, 0b101010, 0b110111, 0b110111, 0b100011, 0b100011});
    public static final Grid CAP_X = new Grid(new byte[]{0b100011, 0b100011, 0b010101, 0b010101, 0b001000, 0b010101, 0b010101, 0b100011, 0b100011});
    public static final Grid CAP_Y = new Grid(new byte[]{0b100011, 0b100011, 0b100011, 0b010101, 0b010101, 0b001000, 0b001000, 0b001000, 0b001000});
    public static final Grid CAP_Z = new Grid(new byte[]{0b111110, 0b000010, 0b000100, 0b000100, 0b001000, 0b010000, 0b010000, 0b100000, 0b111110});

    public static final Grid CAP_PI= new Grid(new byte[]{0b000001, 0b000001, 0b111110, 0b010101, 0b010101, 0b010101, 0b010101, 0b010101, 0b000001});
    public static final Grid CAP_NL= new Grid(new byte[]{0b000001, 0b011100, 0b100011, 0b100011, 0b111110, 0b100000, 0b100000, 0b011100, 0b000001});
    public static final Grid CAP_GR= new Grid(new byte[]{0b000001, 0b000100, 0b101010, 0b101010, 0b101010, 0b011100, 0b001000, 0b001000, 0b000001});

    public static final Grid PLUS  = new Grid(new byte[]{0b000001, 0b000001, 0b001000, 0b001000, 0b111110, 0b001000, 0b001000, 0b000001, 0b000001});
    public static final Grid MINUS = new Grid(new byte[]{0b000001, 0b000001, 0b000001, 0b000001, 0b111110, 0b000001, 0b000001, 0b000001, 0b000001});
    public static final Grid PLOMI = new Grid(new byte[]{0b000001, 0b000001, 0b001000, 0b001000, 0b111110, 0b001000, 0b001000, 0b111110, 0b000001});
    public static final Grid MULTI = new Grid(new byte[]{0b000001, 0b000001, 0b100011, 0b010101, 0b001000, 0b010101, 0b100011, 0b000001, 0b000001});
    public static final Grid DIVIDE= new Grid(new byte[]{0b000001, 0b000001, 0b001000, 0b000001, 0b111110, 0b000001, 0b001000, 0b000001, 0b000001});
    public static final Grid CROSS = new Grid(new byte[]{0b000001, 0b000001, 0b100011, 0b010101, 0b001000, 0b010101, 0b100011, 0b000001, 0b000001});
    public static final Grid DOT   = new Grid(new byte[]{0b000001, 0b000001, 0b000001, 0b000001, 0b001101, 0b001101, 0b000001, 0b000001, 0b000001});
    public static final Grid POWER = new Grid(new byte[]{0b001000, 0b010101, 0b010101, 0b100011, 0b100011, 0b000001, 0b000001, 0b000001, 0b000001});
    public static final Grid ROOT  = new Grid(new byte[]{0b000010, 0b000100, 0b000100, 0b000100, 0b000100, 0b000100, 0b110100, 0b001101, 0b000100});
    public static final Grid EQUAL = new Grid(new byte[]{0b000001, 0b000001, 0b000001, 0b111110, 0b000001, 0b111110, 0b000001, 0b000001, 0b000001});
    public static final Grid UNEQL = new Grid(new byte[]{0b000001, 0b000001, 0b000010, 0b111110, 0b001000, 0b111110, 0b100000, 0b000001, 0b000001});
    public static final Grid GREAT = new Grid(new byte[]{0b000001, 0b000001, 0b100000, 0b011001, 0b000111, 0b011001, 0b100000, 0b000001, 0b000001});
    public static final Grid LESS  = new Grid(new byte[]{0b000001, 0b000001, 0b000010, 0b001101, 0b110001, 0b001101, 0b000010, 0b000001, 0b000001});
    public static final Grid GROEQL= new Grid(new byte[]{0b000001, 0b100000, 0b011001, 0b000111, 0b011001, 0b100011, 0b001101, 0b110001, 0b000001});
    public static final Grid GROLES= new Grid(new byte[]{0b000001, 0b000010, 0b001101, 0b110001, 0b001101, 0b100011, 0b011001, 0b000111, 0b000001});
    public static final Grid COLON = new Grid(new byte[]{0b000001, 0b000001, 0b000001, 0b000001, 0b001101, 0b001101, 0b000001, 0b001101, 0b001101});
    public static final Grid PERIOD= new Grid(new byte[]{0b000001, 0b000001, 0b000001, 0b000001, 0b000001, 0b000001, 0b000001, 0b001101, 0b001101});
    public static final Grid COMMA = new Grid(new byte[]{0b000001, 0b000001, 0b000001, 0b000001, 0b000001, 0b001101, 0b001101, 0b000100, 0b001000});
    //collections
    /**Return collection of number by index
     * @param index index of the character in the collection
     * @value <ul>
     *     <li>0 -> Blank character</li>
     *     <li>1 -> Solid black character</li>
     *     <li>2~11 -> characters of 0~9</li>
     *     <li>12~14 -> Pi, e, golden ratio</li>
     *     <li>15 -> Plus</li>
     *     <li>16 -> Minus</li>
     *     <li>17 -> Plus or Minus character</li>
     *     <li>18 -> Multiply</li>
     *     <li>19 -> Divide</li>
     *     <li>20 -> Cross</li>
     *     <li>21 -> Dot</li>
     *     <li>22 -> Power symbol</li>
     *     <li>23 -> Root Symbol</li>
     *     <li>24 -> Equal sign</li>
     *     <li>25 -> Unequal sign</li>
     *     <li>26 -> Greater than sign</li>
     *     <li>27 -> Lesser than sign</li>
     *     <li>28 -> Greater than or equal sign</li>
     *     <li>29 -> Lesser than or equal sign</li>
     * </ul>*/
    public static Grid NUM(int index){
        return switch(index){
            case 1  -> BLACK;
            case 2  -> CAP_0;
            case 3  -> CAP_1;
            case 4  -> CAP_2;
            case 5  -> CAP_3;
            case 6  -> CAP_4;
            case 7  -> CAP_5;
            case 8  -> CAP_6;
            case 9  -> CAP_7;
            case 10 -> CAP_8;
            case 11 -> CAP_9;
            case 12 -> CAP_PI;
            case 13 -> CAP_NL;
            case 14 -> CAP_GR;
            case 15 -> PLUS;
            case 16 -> MINUS;
            case 17 -> PLOMI;
            case 18 -> MULTI;
            case 19 -> DIVIDE;
            case 20 -> CROSS;
            case 21 -> DOT;
            case 22 -> POWER;
            case 23 -> ROOT;
            case 24 -> EQUAL;
            case 25 -> UNEQL;
            case 26 -> GREAT;
            case 27 -> LESS;
            case 28 -> GROEQL;
            case 29 -> GROLES;
            default -> SPACE;
        };
    }

    /**Return collection of capital characters by index
     * @param index index of the character in the collection
     * @value <ul>
     *     <li>0 -> Blank character</li>
     *     <li>1 -> Solid black character</li>
     *     <li>2~11 -> characters of 0~9</li>
     *     <li>12~37 -> characters of A~Z</li>
     * </ul>*/
    public static Grid CAP(int index){
        return switch(index){
            case 1  -> BLACK;
            case 2  -> CAP_0;
            case 3  -> CAP_1;
            case 4  -> CAP_2;
            case 5  -> CAP_3;
            case 6  -> CAP_4;
            case 7  -> CAP_5;
            case 8  -> CAP_6;
            case 9  -> CAP_7;
            case 10 -> CAP_8;
            case 11 -> CAP_9;
            case 12 -> CAP_A;
            case 13 -> CAP_B;
            case 14 -> CAP_C;
            case 15 -> CAP_D;
            case 16 -> CAP_E;
            case 17 -> CAP_F;
            case 18 -> CAP_G;
            case 19 -> CAP_H;
            case 20 -> CAP_I;
            case 21 -> CAP_J;
            case 22 -> CAP_K;
            case 23 -> CAP_L;
            case 24 -> CAP_M;
            case 25 -> CAP_N;
            case 26 -> CAP_O;
            case 27 -> CAP_P;
            case 28 -> CAP_Q;
            case 29 -> CAP_R;
            case 30 -> CAP_S;
            case 31 -> CAP_T;
            case 32 -> CAP_U;
            case 33 -> CAP_V;
            case 34 -> CAP_W;
            case 35 -> CAP_X;
            case 36 -> CAP_Y;
            case 37 -> CAP_Z;
            default -> SPACE;
        };
    }
}
