package pl.szymanowski.CarListWebApp.Repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarList {

    private final List<Car> listOfCars;

    public CarList() {
        this.listOfCars = new ArrayList<>();
        listOfCars.add(new Car(1L, "VW", "Golf IV", Color.RED));
        listOfCars.add(new Car(2L, "Skoda", "Octavia IV", Color.WHITE));
        listOfCars.add(new Car(3L, "Nissan", "Almera", Color.GREEN));

    }

    public List<Car> getListOfCars() {
        return listOfCars;
    }
}



