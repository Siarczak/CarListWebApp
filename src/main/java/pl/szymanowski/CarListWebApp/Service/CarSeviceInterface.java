package pl.szymanowski.CarListWebApp.Service;

import pl.szymanowski.CarListWebApp.Repository.Car;
import pl.szymanowski.CarListWebApp.Repository.Color;

import java.util.List;

public interface CarSeviceInterface {

    List<Car> getCars();

    Car getCarById(long id);

    Car addCar(Car car);

    Car modCar(Car car);

    Car modPositionInCar(Car newCar, long id);

    boolean deleteCar(long id);

    List<Car> getCarByColor(Color color);

}