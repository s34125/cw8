public class Main {
    public static void main(String[] args) {
        try {
            // Tworzenie kontenerów
            LiquidContainer fuelContainer = new LiquidContainer(240, 1500, 600, 10000, true);
            LiquidContainer milkContainer = new LiquidContainer(240, 1500, 600, 10000, false);
            GasContainer heliumContainer = new GasContainer(220, 1800, 550, 8000, 200);
            RefrigeratedContainer bananaContainer = new RefrigeratedContainer(
                    230, 2000, 590, 12000, "Bananas", 14.0
            );

            // Załadunek kontenerów
            fuelContainer.loadCargo(4500);  // 50% max (bez błędu)
            milkContainer.loadCargo(8500);  // 85% max (bez błędu)
            heliumContainer.loadCargo(7000);
            bananaContainer.loadCargo(11000);

            // Tworzenie kontenerowca
            ContainerShip ship = new ContainerShip(25.5, 5, 50);

            // Załadunek na statek
            ship.loadContainer(fuelContainer);
            ship.loadContainer(milkContainer);
            ship.loadContainer(heliumContainer);
            ship.loadContainer(bananaContainer);

            // Operacje na statku
            ship.unloadContainer(milkContainer);  // Opróżnienie kontenera
            ship.removeContainer(heliumContainer); // Usunięcie kontenera

            // Informacje o statku
            System.out.println("\n=== Informacje o statku ===");
            ship.printShipInfo();

            // Informacje o kontenerze
            System.out.println("\n=== Informacje o kontenerze ===");
            ship.printContainerInfo(fuelContainer);

        } catch (OverfillException | IllegalArgumentException e) {
            System.err.println("Błąd: " + e.getMessage());
        }
    }
}