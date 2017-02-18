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

public class Shop {

    private static final int MAX_TIME = 43200;
    private static final int NUM_CASHIERS = 7;
    private final int P_CREATE_CLIENT = 1; // the probability of the creating a client
    Random rand = new Random();
    int time = 0; // counts the time of the working hours
    int everyHalfHour = 0; // counts thirty minutes
    int howManyClients = 0; // the total number of the market's clients 
    Cashier cashiersArray[] = new Cashier[NUM_CASHIERS]; // creation of an Array with Cashiers

    public void runSim() {
        int serviceTime = 0; // is the time of every client, which the cashier needs to service him
        int cashierSelection = 0; // returned to this variable the cashier selection of the client


        // creation of the market's cashiers
        Cashier cashier1 = new Cashier(true); //express cashier
        Cashier cashier2 = new Cashier(true);
        Cashier cashier3 = new Cashier(false);
        Cashier cashier4 = new Cashier(false);
        Cashier cashier5 = new Cashier(false);
        Cashier cashier6 = new Cashier(false);
        Cashier cashier7 = new Cashier(false);

        cashiersArray[0] = cashier1;
        cashiersArray[1] = cashier2;
        cashiersArray[2] = cashier3;
        cashiersArray[3] = cashier4;
        cashiersArray[4] = cashier5;
        cashiersArray[5] = cashier5;
        cashiersArray[6] = cashier5;

        for (time = 0; time <= MAX_TIME; time++) {
            everyHalfHour++;
            for (int i = 0; i < 7; i++) {
                if (cashiersArray[i].open == true) {
                    cashiersArray[i].serviceQueue(i); // every cashier services his clients every time
                }

            }

            adjustCashiers(); // decides every time if will open or close a cashier

            boolean probabilityCreateClient = createClient(); // the probability of creating a client, returns true if the client will be created
            if (probabilityCreateClient == true) {
                
                serviceTime = 0; // is the time of every client, which the cashier needs to service him
                cashierSelection = 0; // returned to this variable the cashier selection of the client
                
                Client marketClient = new Client(time); // creates a new object of the class Client 
                serviceTime = marketClient.serviceTime(); // calculates the service time of the client
                cashierSelection = marketClient.selectCashier(cashiersArray); // decides the cashier with the less clients in queue
                cashiersArray[cashierSelection].enqueueClient(marketClient); // adds the client into the cahier's queue which selected
                cashiersArray[cashierSelection].serviceTimeEachClient(serviceTime); // gives into this method the service time of the client 
                
                howManyClients++; // increases the total number of clients
                
                adjustCashiers(); // decides every time if will open or close a cashier

            }

            // prints the situation of the cashier's queue every half hour
            if (everyHalfHour == 1800) {
                everyHalfHour = 0;
                System.out.println("After 30 minutes: ");
                System.out.println("------------------");
                for (int i = 0; i < 7; i++) {
                    System.out.println("cashier " + i + ", clients in queue: " + cashiersArray[i].clientInQueue() + "\n");
                }
                System.out.println("\n");
            }
        }

        // services and removes the clients who remain in the cashier's queue the last time of working hours
        for (int i = 0; i < 7; i++) {
            if (cashiersArray[i].open == true) {
                do {
                    cashiersArray[i].serviceQueue(i);
                } while (cashiersArray[i].clientInQueue() != 0);
            }

        }

        // prints the cashier's queue in the end of the working hour
                System.out.println("Last time: ");
                System.out.println("---------------");
                for (int i = 0; i < 7; i++) {
                    System.out.println("cashier " + i + ", clients in queue: " + cashiersArray[i].clientInQueue() + "\n");
                }
                System.out.println("\n");
            
        System.out.println("Total clients : " + howManyClients); // prints the total clients of the market 

    }

    //the probability to create a client
    private boolean createClient() {
        int k;
        k = rand.nextInt(71);
        if (k <= P_CREATE_CLIENT) {
            return true;
        } else {
            return false;
        }
    }

    //this method decides to open or close cashiers
    private void adjustCashiers() {
        for (int i = 0; i < 7; i++) {
            if (i <= 5) {
                // open a cahier if the previous cashier has 180 clients
                if ((cashiersArray[i].clientInQueue() == 180) && (cashiersArray[i + 1].open == false)) {
                    for (int j = 0; j < 7; j++) {
                        if (cashiersArray[j].open != true) {
                            cashiersArray[j].open = true;
                            return;
                        }
                    }
                    // close a cahier if his idle time is 10 minutes
                } else if (cashiersArray[i].idleTime > 600) {

                    if (cashiersArray[i].open == true) {
                        cashiersArray[i].open = false;
                        return;
                    }


                }
            }
        }
    }
}
