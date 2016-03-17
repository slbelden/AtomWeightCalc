/**
 * This program asks for a molecular formula in simplified form,
 * then calculates the total atomic weight of one molecule.
 * It does NOT calculate molecular weight.
 * 
 * I think it works.
 * I took chemistry about a decade ago, so I have no idea what I'm doing.
 * I also have no idea what I'm doing with Java. I just shoved more if-
 * statements in there until it worked. It's probably a terrible design.
 * 
 * This licenced under the MIT licence.
 * https://opensource.org/licenses/MIT
 * @author Fargo Pelz
 * @version 2016-03-17
 * 
 * Important:
 * Formulas are case-sensitive. Multi-character elements must have the
 * first character upper case, and the second lower case.
 * Formulas must have no extra characters. No spaces or symbols.
 * No parenthetical expressions.
 * 
 * Valid Examples:
 * HO
 * H2O
 * HO2
 * H2O2
 * HeN2Si40PClArBBe12Li
 */

package Chemistry;

import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class AtomicWeightCalc {
    // Maps elements to their atomic weight
    protected static Map<String, Double> symbols =
            new HashMap<String, Double>();

    // Global data needed for reading the input
    protected static String formula = "";
    protected static String currentElement = "";
    protected static String currentNumber = "0";
    protected static double total = 0.0;

    // Character References
    protected static String[] uppers = new String[26];
    protected static String[] lowers = new String[26];
    protected static String[] numbers = new String[10];

    public static void main(String[] args) {
        // Instantiate the list of elements
        symbols.put("H", 1.0079);
        symbols.put("He", 4.0026);
        symbols.put("Li", 6.941);
        symbols.put("Be", 9.0122);
        symbols.put("B", 10.811);
        symbols.put("C", 12.0107);
        symbols.put("N", 14.0067);
        symbols.put("O", 15.9994);
        symbols.put("F", 18.9984);
        symbols.put("Ne", 20.1797);
        symbols.put("Na", 22.9897);
        symbols.put("Mg", 24.305);
        symbols.put("Al", 26.9815);
        symbols.put("Si", 28.0855);
        symbols.put("P", 30.9738);
        symbols.put("S", 32.065);
        symbols.put("Cl", 35.453);
        symbols.put("Ar", 39.948);
        symbols.put("K", 39.0983);
        symbols.put("Ca", 40.078);
        symbols.put("Sc", 44.9559);
        symbols.put("Ti", 47.867);
        symbols.put("V", 50.9415);
        symbols.put("Cr", 51.9961);
        symbols.put("Mn", 54.938);
        symbols.put("Fe", 55.845);
        symbols.put("Co", 58.9332);
        symbols.put("Ni", 58.6934);
        symbols.put("Cu", 63.546);
        symbols.put("Zn", 65.39);
        symbols.put("Ga", 69.723);
        symbols.put("Ge", 72.64);
        symbols.put("As", 74.9216);
        symbols.put("Se", 78.96);
        symbols.put("Br", 79.904);
        symbols.put("Kr", 83.8);
        symbols.put("Rb", 85.4678);
        symbols.put("Sr", 87.62);
        symbols.put("Y", 88.9059);
        symbols.put("Zr", 91.224);
        symbols.put("Nb", 92.9064);
        symbols.put("Mo", 95.94);
        symbols.put("Tc", 98.0);
        symbols.put("Ru", 101.07);
        symbols.put("Rh", 102.9055);
        symbols.put("Pd", 106.42);
        symbols.put("Ag", 107.8682);
        symbols.put("Cd", 112.411);
        symbols.put("In", 114.818);
        symbols.put("Sn", 118.71);
        symbols.put("Sb", 121.76);
        symbols.put("Te", 127.6);
        symbols.put("I", 126.9045);
        symbols.put("Xe", 131.293);
        symbols.put("Cs", 132.9055);
        symbols.put("Ba", 137.327);
        symbols.put("La", 138.9055);
        symbols.put("Ce", 140.116);
        symbols.put("Pr", 140.9077);
        symbols.put("Nd", 144.24);
        symbols.put("Pm", 145.0);
        symbols.put("Sm", 150.36);
        symbols.put("Eu", 151.964);
        symbols.put("Gd", 157.25);
        symbols.put("Tb", 158.9253);
        symbols.put("Dy", 162.5);
        symbols.put("Ho", 164.9303);
        symbols.put("Er", 167.259);
        symbols.put("Tm", 168.9342);
        symbols.put("Yb", 173.04);
        symbols.put("Lu", 174.967);
        symbols.put("Hf", 178.49);
        symbols.put("Ta", 180.9479);
        symbols.put("W", 183.84);
        symbols.put("Re", 186.207);
        symbols.put("Os", 190.23);
        symbols.put("Ir", 192.217);
        symbols.put("Pt", 195.078);
        symbols.put("Au", 196.9665);
        symbols.put("Hg", 200.59);
        symbols.put("Tl", 204.3833);
        symbols.put("Pb", 207.2);
        symbols.put("Bi", 208.9804);
        symbols.put("Po", 209.0);
        symbols.put("At", 210.0);
        symbols.put("Rn", 222.0);
        symbols.put("Fr", 223.0);
        symbols.put("Ra", 226.0);
        symbols.put("Ac", 227.0);
        symbols.put("Th", 232.0381);
        symbols.put("Pa", 231.0359);
        symbols.put("U", 238.0289);
        symbols.put("Np", 237.0);
        symbols.put("Pu", 244.0);
        symbols.put("Am", 243.0);
        symbols.put("Cm", 247.0);
        symbols.put("Bk", 247.0);
        symbols.put("Cf", 251.0);
        symbols.put("Es", 252.0);
        symbols.put("Fm", 257.0);
        symbols.put("Md", 258.0);
        symbols.put("No", 259.0);
        symbols.put("Lr", 262.0);
        symbols.put("Rf", 261.0);
        symbols.put("Db", 262.0);
        symbols.put("Sg", 266.0);
        symbols.put("Bh", 264.0);
        symbols.put("Hs", 277.0);
        symbols.put("Mt", 268.0);

        // Instantiate the character references
        for (int i = 0; i < 26; i++)
            uppers[i] = ("" + (char) (i + 'A'));
        for (int i = 0; i < 26; i++)
            lowers[i] = ("" + (char) (i + 'a'));
        for (int i = 0; i < 10; i++)
            numbers[i] = ("" + i);

        // Run the thing
        formula = getFormula();
        readLetter();

        // Print result
        System.out.println();
        System.out.println("Total atomic weight: ");
        System.out.print(total);
    }

    // Helper function
    // Returns true iff symbol is in set
    static boolean contains(String symbol, String[] set) {
        for (String s : set)
            if (symbol.equals(s)) return true;
        return false;
    }

    // Get user input
    static String getFormula() {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input the formula:");
        String formula = ""; // Definitely not spelled "fourmula"
        try {
            formula = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        return formula;
    }

    // Adds one atom's weight to the total weight
    // This method is run only after we've read one full atomic symbol,
    // and zero or more numbers.
    static void addWeight() {
        // Exit immediately if we don't recognize the element
        if (!symbols.containsKey(currentElement)) {
            System.out.print("ERROR: Element "
                    + currentElement + " not found.");
            System.exit(1);
        } else {
            int number;
            // If currentNumber has noting concatenated onto it, the atomic
            // symbol exists alone, without a number. So only one atom.
            if (currentNumber.length() == 1) number = 1;
            // Turn strings into variables we can do math with
            else number = new Integer(currentNumber);
            double elementWeight = symbols.get(currentElement);
            // This step is optional, it lists each addition step individually.
            System.out.println("Adding " + number + " atoms of "
                    + currentElement + " at " + elementWeight);
            // The final calculation
            total = total + (number * elementWeight);
        }
        // Reset everything, and try to read another atom
        currentNumber = "0";
        currentElement = "";
        readLetter();
    }

    // This method is run if we expect the next character to be a letter
    static void readLetter() {
        // Make sure we have something to read
        if (!formula.isEmpty()) {
            // Read in the next character and cut it off
            String temp = formula.substring(0, 1);
            formula = formula.substring(1, formula.length());
            // If the character we just read is upper case...
            if (contains(temp, uppers)) {
                currentElement = currentElement.concat(temp);
                // ...and the next character is a number, read in a number
                if (!formula.isEmpty()
                        && contains(formula.substring(0, 1), numbers))
                    readNumber();
                // ...and the next character is upper case,
                // this element name has ended
                else if (!formula.isEmpty()
                        && contains(formula.substring(0, 1), uppers))
                    addWeight();
                // ...and the next character is lower case (or anything else)
                // read in another character
                else readLetter();  
            } // If the current character is lower case...
            else if (contains(temp, lowers)) {
                currentElement = currentElement.concat(temp);
                // ...and the next character is a number, read in a number
                if (!formula.isEmpty()
                        && contains(formula.substring(0, 1), numbers))
                    readNumber();
                // ...and the next character is upper case or a number
                // (or anything else) this element name has ended
                else addWeight();
            }
        }
        // If there are no more characters to read, but we have read at least
        // one symbol, we need to add the last atom to the total.
        else if (!currentElement.equals("")) addWeight();
    }

    // This method is run if we think the next character might not be a letter
    static void readNumber() {
        // Make sure we have something to read
        if (!formula.isEmpty()) {
            // Read in the next character
            String temp = formula.substring(0, 1);
            // Only proceed if this character is actually a number
            if (contains(temp, numbers)) {
                // Cut off this character
                formula = formula.substring(1, formula.length());
                // Add to the total
                currentNumber = currentNumber.concat(temp);
                readNumber();
            }
            // If this character isn't a number, it's the start of a new
            // element name, so we need to finish up the current atom.
            else addWeight();
        }
        // If there are no more numbers to read, but we have read at least
        // one number, we need to add the last atom to the total.
        else if (!currentNumber.equals("0")) addWeight();
    }
}
