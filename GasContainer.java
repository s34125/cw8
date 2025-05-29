public class GasContainer extends Container implements IHazardNotifier {
    private final double pressure; // ciśnienie w atmosferach

    public GasContainer(int height, double tareWeight, int depth,
                        double maxPayload, double pressure) {
        super(height, tareWeight, depth, maxPayload, "G");
        this.pressure = pressure;
    }

    @Override
    public void loadCargo(double mass) throws OverfillException {
        if (mass > maxPayload) {
            sendHazardNotification("Próba załadowania " + mass + " kg gazu do kontenera " +
                    getSerialNumber() + " przekraczająca maksymalną ładowność " + maxPayload + " kg");
            throw new OverfillException("Przekroczono maksymalną ładowność");
        }
        setMassCargo(mass);
    }

    @Override
    public void emptyCargo() {
        setMassCargo(getMassCargo() * 0.05); // pozostawienie 5% ładunku
    }

    @Override
    public void sendHazardNotification(String message) {
        System.out.println("[NOTYFIKACJA] " + message);
    }
}