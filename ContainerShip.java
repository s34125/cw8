import java.util.ArrayList;
import java.util.List;

public class ContainerShip {
    private final List<Container> containers = new ArrayList<>();
    private final double maxSpeed; // w węzłach
    private final int maxContainerCount; // maks. liczba kontenerów
    private final double maxWeight; // maks. waga w tonach

    public ContainerShip(double maxSpeed, int maxContainerCount, double maxWeight) {
        this.maxSpeed = maxSpeed;
        this.maxContainerCount = maxContainerCount;
        this.maxWeight = maxWeight;
    }

    public void loadContainer(Container container) throws OverfillException {
        validateLoad(container);
        containers.add(container);
    }

    public void loadContainers(List<Container> containersToAdd) throws OverfillException {
        for (Container container : containersToAdd) {
            validateLoad(container);
        }
        containers.addAll(containersToAdd);
    }

    private void validateLoad(Container container) throws OverfillException {
        if (containers.size() >= maxContainerCount) {
            throw new OverfillException("Osiągnięto maksymalną liczbę kontenerów: " + maxContainerCount);
        }

        double currentTotalWeight = containers.stream()
                .mapToDouble(Container::getTotalWeight)
                .sum() / 1000; // konwersja kg na tony

        double newWeight = currentTotalWeight + container.getTotalWeight() / 1000;
        if (newWeight > maxWeight) {
            throw new OverfillException("Przekroczono maksymalną wagę ładunku. Aktualnie: " +
                    currentTotalWeight + " t, maks: " + maxWeight + " t");
        }
    }

    public void removeContainer(Container container) {
        containers.remove(container);
    }

    public void unloadContainer(Container container) {
        container.emptyCargo();
    }

    public void replaceContainer(String serialNumber, Container newContainer) throws OverfillException {
        Container toRemove = null;
        for (Container c : containers) {
            if (c.getSerialNumber().equals(serialNumber)) {
                toRemove = c;
                break;
            }
        }

        if (toRemove != null) {
            containers.remove(toRemove);
            loadContainer(newContainer);
        }
    }

    public void moveContainer(Container container, ContainerShip destination) throws OverfillException {
        if (containers.contains(container)) {
            destination.loadContainer(container);
            containers.remove(container);
        }
    }

    public void printContainerInfo(Container container) {
        System.out.println("Kontener " + container.getSerialNumber() + ":");
        System.out.println(" - Typ: " + container.getClass().getSimpleName());
        System.out.println(" - Masa ładunku: " + container.getMassCargo() + " kg");
        System.out.println(" - Waga własna: " + container.getTareWeight() + " kg");
        System.out.println(" - Waga całkowita: " + container.getTotalWeight() + " kg");
    }

    public void printShipInfo() {
        System.out.println("Kontenerowiec (max prędkość: " + maxSpeed + " węzłów)");
        System.out.println(" - Maks. kontenerów: " + maxContainerCount);
        System.out.println(" - Maks. waga ładunku: " + maxWeight + " t");
        System.out.println(" - Załadowane kontenery: " + containers.size());
        System.out.println(" - Łączna waga: " +
                containers.stream().mapToDouble(Container::getTotalWeight).sum() / 1000 + " t");

        System.out.println("\nLista kontenerów:");
        for (Container container : containers) {
            System.out.println(" > " + container.getSerialNumber());
        }
    }
}