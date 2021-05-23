package pl.szymanowski.CarListWebApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.szymanowski.CarListWebApp.Repository.Car;
import pl.szymanowski.CarListWebApp.Repository.Color;
import pl.szymanowski.CarListWebApp.Service.CarServiceImp;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cars")
public class CarController {

    CarServiceImp carServiceImp;

    @Autowired
    public CarController(CarServiceImp carServiceImp) {
        this.carServiceImp = carServiceImp;

    }


    @GetMapping(produces =
            {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<List<Car>> getCars() {
        if (carServiceImp.getCars().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        } else {
            return new ResponseEntity<>(carServiceImp.getCars(), HttpStatus.OK);

        }


    }

    @GetMapping(value = "/{id}", produces =
            {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> carToModify = carServiceImp.getCars().stream()
                .filter(car1 -> car1.getId() == id)
                .findFirst();

        if (carToModify.isPresent()) {

            return new ResponseEntity<>(carServiceImp.getCarById(id), HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {

        if (car != null) {

            return new ResponseEntity<>(carServiceImp.addCar(car), HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Car> modifyCar(@RequestBody Car car) {

        Optional<Car> carToModify = carServiceImp.getCars().stream()
                .filter(car1 -> car1.getId() == car.getId())
                .findFirst();

        if (carToModify.isPresent()) {
            return new ResponseEntity<>(carServiceImp.modCar(car), HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Car> posInCarToModyfy(@RequestBody Car newCar, @PathVariable long id) {


        if (carServiceImp.modPositionInCar(newCar, id) != null) {

            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {

            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping(path = "{/id}")
    public ResponseEntity<Car> deleteCar(Car car) {
        Optional<Car> carToRemove = carServiceImp.getCars().stream()
                .filter(car1 -> car1.getId() == car.getId())
                .findFirst();

        if (carToRemove.isPresent()) {
            carServiceImp.deleteCar(car);
            return new ResponseEntity<>(HttpStatus.GONE);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> carsByColor(@PathVariable Color color) {
        carServiceImp.carsByColor(color);

        if (color != null) {
            return new ResponseEntity<>(carServiceImp.carsByColor(color), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }

    }


}




