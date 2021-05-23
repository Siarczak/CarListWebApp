package pl.szymanowski.CarListWebApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szymanowski.CarListWebApp.Repository.Car;
import pl.szymanowski.CarListWebApp.Repository.CarList;
import pl.szymanowski.CarListWebApp.Repository.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImplementation implements CarSeviceInterface {

    private final CarList carList;

    @Autowired
    CarServiceImplementation(CarList carList) {

        this.carList = carList;

    }

    @Override
    public List<Car> getCars() {

        return carList.getListOfCars();
    }

    @Override
    public Car getCarById(long id) {

        Car carById = carList.getListOfCars().stream()
                .filter(car -> car.getId() == id)
                .findFirst().get();

        return carById;
    }

    @Override
    public Car addCar(Car car) {
        boolean addCarr = carList.getListOfCars().add(car);

        if (addCarr) {
            return car;

        } else {

            return null;
        }
    }

    @Override
    public Car modCar(Car newCar) {
        Car carToModify = carList.getListOfCars().stream()
                .filter(car -> car.getId() == newCar.getId())
                .findFirst().get();


        carList.getListOfCars().remove(carToModify);
        carList.getListOfCars().add(newCar);
        return newCar;

    }

    @Override
    public Car modPositionInCar(Car newCar, long id) {

        Car findPosToModify = carList.getListOfCars().stream()
                .filter(car1 -> car1.getId() == id)
                .findFirst().get();


        if (findPosToModify.getModel() != null) {
            findPosToModify.setModel(newCar.getModel());
            return findPosToModify;
        } else if (findPosToModify.getColor() != null) {
            findPosToModify.setColor(newCar.getColor());
            return findPosToModify;
        } else if (findPosToModify.getBrand() != null) {
            findPosToModify.setBrand(newCar.getBrand());
            return findPosToModify;

        }
        return null;

    }

    @Override
    public boolean deleteCar(long id) {

        Optional<Car> first = carList.getListOfCars().stream().filter(car -> car.getId() == id).findFirst();

        if (first.isPresent()) {

            carList.getListOfCars().remove((int) first.get().getId() - 1);
            return true;
        } else {
            return false;

        }

    }


    @Override
    public List<Car> getCarByColor(Color color) {

        List<Car> listOfCarsWithColor = new ArrayList<>();
        for (Car car : carList.getListOfCars()) {

            if (car.getColor() == color) {

                listOfCarsWithColor.add(car);
            }
        }
        return listOfCarsWithColor;


    }


}



