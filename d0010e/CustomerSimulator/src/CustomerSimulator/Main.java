package CustomerSimulator;

public class Main {
	public static void main(String[] args) {
		Simulator sim = new Simulator();
		sim.run(Double.parseDouble(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]), Double.parseDouble(args[5]), Double.parseDouble(args[6]), Double.parseDouble(args[7]), Long.parseLong(args[8]));
	}
}
