public class LiquidContainer extends Container implements IHazardNotifier {
    private final boolean isDangerous;

    public LiquidContainer(int height, double tareWeight, int depth,
                           double maxPayload, boolean isDangerous) {
        super(height, tareWeight, depth, maxPayload, "L");
        this.isDangerous = isDangerous;
    }

    @Override
    public void loadCargo(double mass) throws OverfillException {
        double allowedCapacity = isDangerous ? maxPayload * 0.5 : maxPayload * 0.9;

        if (mass > maxPayload) {
            throw new OverfillException("Próba załadowania " + mass + " kg do kontenera " +
                    getSerialNumber() + " przekraczająca maksymalną ładowność " + maxPayload + " kg");
        }

        if (mass > allowedCapacity) {
            sendHazardNotification("Niebezpieczna próba załadowania " + mass + " kg " +
                    (isDangerous ? "niebezpiecznego " : "") + "płynu do kontenera " + getSerialNumber());
            throw new OverfillException("Przekroczono dozwoloną pojemność: " + allowedCapacity + " kg");
        }

        setMassCargo(mass);
    }

    @Override
    public void emptyCargo() {
        setMassCargo(0);
    }

    @Override
    public void sendHazardNotification(String message) {
        System.out.println("[NOTYFIKACJA] " + message);
    }
}