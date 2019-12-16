package mooc.vandy.java4android.diamonds.logic;

import android.util.Log;
import mooc.vandy.java4android.diamonds.ui.OutputInterface;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early
 * Android interactions.  Designing the assignments this way allows
 * you to first learn key 'Java' features without having to beforehand
 * learn the complexities of Android.
 */
public class Logic
       implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG = Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface [MainActivity.java].
     * <p>
     * It is called 'out' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'out' is good enough).
     */
    private OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance (which
     * implements [OutputInterface]) to 'out'.
     */
    public Logic(OutputInterface out){
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labeled 'Process...' is pressed.
     */

    // the 'main' method that checks the size of the diamond and then calls the appropriate method
    public void process(int size) {

        if (size == 1) {
            diamondLessThanTwo();
        } else {
            diamondGreaterThanTwo(size);
        }
    }

    // easy way to print a diamond size of one
    // ¯\_(ツ)_/¯
    private void diamondLessThanTwo() {
        mOut.println("+--+\n|<>|\n+--+");
    }

    private void diamondGreaterThanTwo(int size) {

        // disclaimer that I have no idea how I figured all these formulas and variables out
        // the triangles are a heavily modified version of the code below
        // https://gist.github.com/Cleanshooter/d1764a4d487bb8424f635caadfe69167

        // calculates the actual height based on input size
        int height = (size * 2) - 1;
        // height incrementing variable
        int heightInc = height + 2;
        // some variable for the bottom half of the diamond
        // can't remember how the fuck this works
        int i1 = (height + 1) / 2;
        // global incrementing variable for top border
        // and figuring out even and odd lines
        int globalInc = 1;
        // variable to know when it's at the middle
        int middle = 1;

        String BegEnd;

        // For building a string to display each line
        StringBuilder str = new StringBuilder();
        // For the top and bottom borders
        StringBuilder begEnd = new StringBuilder();

        // begin overall for loop to build the top part of the diamond
        for (int i = i1; i >= 2; i--) {

            // build the top border if at the first line
            if (globalInc == 1) {
                // append each character to a string
                begEnd.append("+");
                for (int ii = 1; ii <= (height + 1); ii++) {
                    begEnd.append("-");
                }
                begEnd.append("+");
                // print out the string we built
                BegEnd = begEnd.toString();
                mOut.println(BegEnd);
                // increment global incrementing variable
                globalInc++;
            }

            // loop for building the actual diamond
            for (int j = 1; j <= i - 1; j++) {
                // begin triangle 1 consisting of the top leading spaces
                str.append(" ");
            }
            // end triangle 1
            // begin triangle 2 consisting of the top of the actual diamond
            for (int j = 1; j <= (heightInc - i * 2) + 1; j++) {
                // if first non space character, append a forward slash
                if (j == 1) {
                    str.append("/");
                    // if last non space character, append a backward slash
                } else if (j == (heightInc - i * 2) + 1) {
                    str.append("\\");
                    // if not a first or last non space character...
                } else {
                    // MODULUS, MOTHERFUCKER
                    if (globalInc % 2 == 1) {
                        // if our global increment is odd then append a dash
                        str.append("-");
                    } else {
                        // otherwise it's even and append an equals sign
                        str.append("=");
                    }
                }
            }
            // end triangle 2
            // begin triangle 3 consisting of the top trailing spaces
            // these are so the border shows up correctly
            for (int j = 1; j <= i - 1; j++) {
                str.append(" ");
            }
            // end triangle 3
            // print our constructed string variable with borders
            mOut.println("|" + str + "|");
            // clear out our string variable for the next iteration
            str.setLength(0);
            // increment our global increment variable
            globalInc++;
        }
        // end for loop to build the top part of the diamond
        // begin for loop to build the bottom part of the diamond
        for (int i = 1; i <= i1; i++) {
            for (int j = 1; j <= i - 1; j++) {
                // begin triangle 4 consisting of the bottom leading spaces
                str.append(" ");
            }
            // end triangle 4
            // begin triangle 5 consisting of the bottom of the actual diamond
            for (int j = 1; j <= (heightInc - i * 2) + 1; j++) {
                // if we're at the beginning of the middle line...
                if (j == 1 && middle == 1) {
                    // append an arrow
                    str.append("<");
                    // if we're at the end of the middle line...
                } else if (j == ((heightInc - i * 2) + 1) && middle == 1) {
                    // append an arrow
                    str.append(">");
                    // change our middle variable so we know we're not in the middle anymore
                    middle = 0;
                    // if we're at the first non space, non middle character append a backslash
                } else if (j == 1 && middle == 0) {
                    str.append("\\");
                    // if we're at the last non space, non middle character append a forward slash
                } else if (j == ((heightInc - i * 2) + 1) && middle == 0) {
                    str.append("/");
                } else {
                    // otherwise append a dash or equals sign if odd or even
                    if (globalInc % 2 == 1) {
                        str.append("-");
                    } else {
                        str.append("=");
                    }
                }
            }
            // end triangle 5
            // begin triangle 6 consisting of the bottom trailing spaces
            for (int j = 1; j <= i - 1; j++) {
                str.append(" ");
            }
            // end triangle 6
            // print out our created string along with borders
            mOut.println("|" + str + "|");
            // clear our string variable
            str.setLength(0);
            // increment our global incrementing variable
            globalInc++;

        }
        // end for loop to build the bottom part of the diamond
        // print out the bottom border
        BegEnd = begEnd.toString();
        mOut.println(BegEnd);
    }


}
