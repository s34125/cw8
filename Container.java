import java.util.HashMap;
import java.util.Map;

public abstract class Container {
    private double massCargo; // masa ładunku w kg
    private final int height; // wysokość w cm
    private final double tareWeight; // waga własna w kg
    private final int depth; // głębokość w cm
    private final String serialNumber; // numer seryjny
    private final double maxPayload; // maksymalna ładowność w kg

    private static final Map<String, Integer> typeCounters = new HashMap<>();

    public Container(int height, double tareWeight, int depth, double maxPayload, String type) {
        this.height = height;
        this.tareWeight = tareWeight;
        this.depth = depth;
        this.maxPayload = maxPayload;

        // Generowanie unikalnego numeru seryjnego
        int count = typeCounters.getOrDefault(type, 0) + 1;
        typeCounters.put(type, count);
        this.serialNumber = "KON-" + type + "-" + count;
    }

    public abstract void loadCargo(double mass) throws OverfillException;
    public abstract void emptyCargo();

    // Gettery
    public double getMassCargo() { return massCargo; }
    protected void setMassCargo(double massCargo) { this.massCargo = massCargo; }
    public int getHeight() { return height; }
    public double getTareWeight() { return tareWeight; }
    public int getDepth() { return depth; }
    public String getSerialNumber() { return serialNumber; }
    public double getMaxPayload() { return maxPayload; }
    public double getTotalWeight() { return tareWeight + massCargo; } // waga całkowita
}