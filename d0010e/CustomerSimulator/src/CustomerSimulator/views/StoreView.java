package CustomerSimulator.views;

import java.util.Observable;
import java.util.Observer;

import CustomerSimulator.models.Store;

public class StoreView implements Observer {
	private int counter = 0;
	public void update(Observable o, Object arg) {
		Store store = (Store) o;
		if(counter < 1) {
			displayStart(store);
		}
		else if (store.getEvQueue().getSize() == 0) {
			displayResult(store);
		}
		else {
			displayEvents(store);
		}
		counter++;
	}
	
	public void displayStart(Store o) {
		System.out.println("PARAMETRAR");
        System.out.println("==========");
        System.out.println("Antal kassor, N..........:" + o.getAmOfRegs());
        System.out.println("Max som ryms, M..........:" + o.getMaxPeople());
        System.out.println("Ankomshastighet, lambda..:" + o.getLambda());
        System.out.println("Plocktider, [P_min..Pmax]: " + o.getPickMinMax());
        System.out.println("Betaltider, [K_min..Kmax]: " + o.getPayMinMax());
        System.out.println("Fr�, f...................:" + o.getSeed());

        System.out.println("F�RLOPP");
        System.out.println("=======");
        System.out.println(
                "  Tid    H�ndelse    Kund    ?    led    ledT    I    $    :-(    k�at    k�T    k�ar    [Kassak�..]");
	}
	
	public void displayEvents(Store o) {
		String time = String.valueOf(String.format("%.2f", o.getCurrentTime())) + "    ";

        String event;

        if (o.getEventName() == "Arrive") {
            event = "Arrive     ";
        } else if (o.getEventName() == "Pick") {
            event = "Pick       ";
        } else if (o.getEventName() == "Pay") {
            event = "Pay        ";
        } else if (o.getEventName() == "Close") {
            event = "Close      ";
        } else if (o.getEventName() == "Open") {
            event = "Open       ";
        } else {
            event = o.getEventName();
        }

        String custNum = String.valueOf(o.getCurrentCustomerId() + "      ");
        String open = (o.getStatus()) ? "�     " : "S     ";
        String amOfFreeRegs = String.valueOf(o.getFreeRegs()) + "     ";
        String sumTimeFreeRegs = String.valueOf(String.format("%.2f", o.getFreeTime())) + "    ";
        String amOfCusts = String.valueOf(o.getPeopleInStore()) + "    ";
        String doneCusts = String.valueOf(o.getMoney()) + "     ";
        String missedCusts = String.valueOf(o.getTotmissedCustomers()) + "      ";
        String totAmOfQueuedPeeps = String.valueOf(o.getPayQueueMaxSize()) + "      ";
        String timeQueued = String.valueOf(String.format("%.2f", o.getInLineTime())) + "     ";
        String inLine = String.valueOf(o.getQueueSize()) + "        ";
        String wholeQueue = o.getQueue() + " ";
        
        String infoRow;
        
        if (event == "Open       " || event == "Close      ") {
            infoRow = time + event;
        }
        
        else {
            infoRow = time + event + custNum + open + amOfFreeRegs + sumTimeFreeRegs + amOfCusts + doneCusts
                    + missedCusts + totAmOfQueuedPeeps + timeQueued + inLine + wholeQueue;
        }
        System.out.println("  " + infoRow);
	}
	
	public void displayResult(Store o) {
	 	System.out.println("RESULTAT");
        System.out.println("========");
        System.out.println("1) av " + o.getTotAmountOfCustomers() + " kunder handlade "
                + String.valueOf(o.getTotAmountOfCustomers() - o.getTotmissedCustomers()) + " medan "
                + o.getTotmissedCustomers() + " missades.");
        System.out.println("2) Totalt tid " + o.getAmOfRegs() + " kassor har varit" + " lediga: "
                + String.format("%.0f",o.getFreeTime()) + " te.");
        double snittTid = o.getFreeTime() / o.getAmOfRegs();
        double percentTid = 100*snittTid / o.getTime();
        System.out.println("Genomsnittlig ledig kassatid: " + String.format("%.2f",snittTid) + "(dvs " + String.format("%.2f",percentTid)
                + "% av tiden fr�n �ppning till sista kunden betalat).");

        int totAmQueuedPeeps = o.TotPeopleInQueue();
        double snittKoTid = o.TotPeopleInQueueTime() / totAmQueuedPeeps;
        System.out.println("3) Total tid " + totAmQueuedPeeps + " kunder" + " tvingats koa: "
                + String.format("%.2f",o.TotPeopleInQueueTime()) + " te.");
        System.out.print("Genomsnittlig kotid: " + String.format("%.2f",snittKoTid) + " te.");
	}
}