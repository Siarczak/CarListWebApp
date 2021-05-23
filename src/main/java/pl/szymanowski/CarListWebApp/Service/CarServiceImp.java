package pl.szymanowski.CarListWebApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szymanowski.CarListWebApp.Repository.Car;
import pl.szymanowski.CarListWebApp.Repository.Color;

import java.util.List;

@Service
public class CarServiceImp {


    CarServiceMethods carServiceMethods;


    @Autowired
    public CarServiceImp(CarServiceMethods carServiceMethods) {
        this.carServiceMethods = carServiceMethods;

    }

    public List<Car> getCars() {

        return carServiceMethods.getCars();

    }

    public Car getCarById(long id) {
        return carServiceMethods.getCarById(id);

    }


    public Car addCar(Car car) {

        return carServiceMethods.addCar(car);
    }

    public Car modCar(Car car) {
        return carServiceMethods.modCar(car);

    }

    public boolean deleteCar(Car car) {
        return carServiceMethods.deleteCar(car);

    }

    public List<Car> carsByColor(Color color) {

        return carServiceMethods.getCarByColor(color);
    }

    public Car modPositionInCar(Car car,Long id)
    {

        return carServiceMethods.modPositionInCar(car,id);
    }

}
