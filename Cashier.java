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

public class Cashier {

    public ClientQueue clientsQueue; 
    Client currentClient; 
    public boolean open; 
    private int serviceTime = 0; //time with a customer in the cashier
    private int timeForClient = 0;
    public int idleTime; //time without a customer

    //constructor
    public Cashier(boolean itsOpen) {
        clientsQueue = new ClientQueue();
        this.open = itsOpen;
    }


    public void serviceQueue(int no) {
        if (clientsQueue.isEmpty() == true) {
            idleTime ++;
            idleTime = 0;
        } else {
            serviceTime ++;
            if (serviceTime >= this.timeForClient) {
                clientsQueue.removeFirstClient();
                serviceTime = 0;
            }
        }
    }

    // takes the service time for each client
    public void serviceTimeEachClient(int time) {
        this.timeForClient = time;
    }

    //adds a customer to the cashier's queue
    public void enqueueClient(Client customer) {
        clientsQueue.addClient(customer);
        currentClient = customer;
    }

    //prints how many clients are on the cashier
    public int clientInQueue() {
        return clientsQueue.size();

    }
}
