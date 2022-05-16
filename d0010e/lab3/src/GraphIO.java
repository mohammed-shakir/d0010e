import java.util.Scanner;

import java.io.*;

public class GraphIO {
	static public void main(String[] args) {
		readFile(null, "gridgraph");
	}
	
	static public void readFile(Graph g, String filename) {
		File file = new File(filename);
		Scanner scnr = null;
        try {
            scnr = new Scanner(file);

        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        int n = scnr.nextInt();
        
        for(int i = 0; i < n; i++) {
        	g.addNode(scnr.nextInt(), scnr.nextInt(), scnr.nextInt());
        }
        while (scnr.hasNext()) {
        	g.addEdge(scnr.nextInt(), scnr.nextInt(), scnr.nextInt());
        }
        scnr.close();
	}
}