import java.util.HashMap;
import java.util.Map;

public class RefrigeratedContainer extends Container {
    private final String productType;
    private final double temperature; // temperatura w °C

    private static final Map<String, Double> requiredTemperatures = new HashMap<>();
    static {
        requiredTemperatures.put("Bananas", 13.3);
        requiredTemperatures.put("Chocolate", 18.0);
        requiredTemperatures.put("Fish", 2.0);
        requiredTemperatures.put("Meat", -15.0);
        requiredTemperatures.put("Ice cream", -18.0);
        requiredTemperatures.put("Frozen pizza", -30.0);
        requiredTemperatures.put("Cheese", 7.2);
        requiredTemperatures.put("Sausages", 5.0);
        requiredTemperatures.put("Butter", 20.5);
        requiredTemperatures.put("Eggs", 19.0);
    }

    public RefrigeratedContainer(int height, double tareWeight, int depth,
                                 double maxPayload, String productType, double temperature) {
        super(height, tareWeight, depth, maxPayload, "C");
        this.productType = productType;
        this.temperature = temperature;

        validateTemperature();
    }

    private void validateTemperature() {
        Double requiredTemp = requiredTemperatures.get(productType);
        if (requiredTemp == null) {
            throw new IllegalArgumentException("Nieznany typ produktu: " + productType);
        }
        if (temperature < requiredTemp) {
            throw new IllegalArgumentException("Temperatura " + temperature +
                    "°C jest zbyt niska dla " + productType + ". Wymagane min. " + requiredTemp + "°C");
        }
    }

    @Override
    public void loadCargo(double mass) throws OverfillException {
        if (mass > maxPayload) {
            throw new OverfillException("Próba załadowania " + mass + " kg do kontenera " +
                    getSerialNumber() + " przekraczająca maksymalną ładowność " + maxPayload + " kg");
        }
        setMassCargo(mass);
    }

    @Override
    public void emptyCargo() {
        setMassCargo(0);
    }
}