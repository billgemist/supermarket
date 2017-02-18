/*
 * Βασίλης Γεμιστός / Vasilis Gemistos
 * 
 * mail: cst11010@uop.gr
 * 
 * AM : 2025201100010
 * 
 * ----------------------------------------------
 * 
 * Παναγιώτης Κουτσιώρας / Panagiotis Koutsioras
 * 
 * mail: cst11037@uop.gr
 * 
 * AM : 2025201100037
 * 
 */
package main;

import java.util.Random;

public class Client {

    private static final int MAX_TIME = 43200;
    private static final int MAX_ITEMS = 50;
    private final double P_IS_SPECIAL = 0.01; // the probability of creation a special client
    private final double P_PAYS_CASH = 0.4; // the probability of the client pays with cash
    public int priority; // the priority of the client
    private int numItems; // the total number of the client's items
    private boolean paysCash;
    private boolean isSpecial;
    
    Random rand = new Random();

    // constructor
    public Client(int creationTime) {
        isSpecial = RandomSpecial();
        if (isSpecial == true) {
            this.priority = (creationTime - MAX_TIME);
        } else {
            this.priority = creationTime;
        }
        this.numItems = RandomMaxItems();
        this.paysCash = RandomPaysCash();

    }

    private int RandomMaxItems() {
        int maxItems = rand.nextInt(50) + 1;
        return maxItems;
    }

    private boolean RandomPaysCash() {
        double k;
        k = rand.nextDouble();
        if (k <= P_PAYS_CASH) {
            return true;
        } else {
            return false;
        }

    }

    private boolean RandomSpecial() {
        double k;
        k = rand.nextDouble();
        if (k == P_IS_SPECIAL) {
            return true;
        } else {
            return false;
        }

    }

    // calculates the service time of the client
    public int serviceTime() {
        int time = (this.numItems * 5);
        int k;

        if (this.paysCash == true) {
            time += 120;
        } else {
            time += 90;
        }
        k = rand.nextInt(121);
        time += k;

        return time;

    }

    // cashier selection by the client
    public int selectCashier(Cashier Array[]) {

        // if client has 10 or less item goes to express cashier
        if (numItems <= 10) {
            return 0;
        }
        // if client has more than 10 items decides the cahier with the less clients in his queue
        int k = Array[1].clientInQueue();
        int l = 1;
        for (int i = 2; i < 7; i++) {
            if ((k > Array[i].clientInQueue()) && (Array[i].open == true)) {
                k = Array[i].clientInQueue();
                l = i;
            }
        }
        return l;
    }
} 
           
