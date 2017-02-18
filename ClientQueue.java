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

import java.util.ArrayList;

public class ClientQueue {

    private ArrayList<Client> items;

    // constructor
    public ClientQueue() {
        items = new ArrayList<Client>(200);
    }

    public void addClient(Client customer) {
        for (int i = 0; i <= size(); i++) {
            items.add(i, customer);
            break;
        }
    }

    public void removeFirstClient() {
        items.remove(0);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
