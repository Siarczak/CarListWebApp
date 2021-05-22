package pl.szymanowski.CarListWebApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szymanowski.CarListWebApp.Repository.Car;
import pl.szymanowski.CarListWebApp.Repository.CarList;
import pl.szymanowski.CarListWebApp.Repository.Color;

import java.util.List;

@Service
public class CarService {


    CarList carList;


    @Autowired
    public CarService(CarList carList)
    {
        this.carList = carList;

    }

    public List<Car> getCars(){

        return carList.getAllCars();

    }

    public Car getCarById(long id){
            return carList.getCarById(id);

    }




    public Car addCar( Car car)
    {

        return carList.addCar(car);
    }

    public Car modCar (Car car)
    {
        return carList.modCar(car);

    }

    public boolean deleteCar(Car car)
    {
        return carList.deleteCar(car);

    }

    public List<Car> carsByColor(Color color)
    {

        return carList.getCarByColor(color);
    }


//    public Car getCarById(@PathVariable long id){
//
//        Optional<Car> carById = carList.getAllCars().stream()
//                .filter(car -> car.getId()==id)
//                .findFirst();
//
//        if(carById.isPresent())
//        {
//            return new ResponseEntity<>(carById.get(), HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        }
//
//    }
//
//    @PostMapping
//    public ResponseEntity<Car> addCar(@Valid @RequestBody Car car)
//    {
//
//        boolean addCarr= listOfCars.add(car);
//
//        if(addCarr)
//        {
//            return new ResponseEntity<>(HttpStatus.CREATED);
//
//
//        }
//        else
//        {
//
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//
//    }
//
//    @PutMapping
//    public ResponseEntity<Car> modCar(@RequestBody Car newCar)
//    {
//        Optional<Car> carToModify = listOfCars.stream()
//                .filter(car -> car.getId() == newCar.getId())
//                .findFirst();
//
//        if(carToModify.isPresent())
//        {
//            listOfCars.remove(carToModify.get());
//            listOfCars.add(newCar);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//
//        }
//        else
//        {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//
//    @PatchMapping("/{id}")
//    ResponseEntity<Car> posInCarToModyfy(@RequestBody Car newCar, @PathVariable long id)
//    {
//
//        Optional<Car> findPosToModyfy = listOfCars.stream()
//                .filter(car -> car.getId() == id)
//                .findFirst();
//
//        if(findPosToModyfy.isPresent())
//        {
//            if(newCar.getModel() !=  null)
//            {
//                findPosToModyfy.get().setModel(newCar.getModel());
//                return new ResponseEntity<>(HttpStatus.OK);
//
//            }
//            else if(newCar.getColor() != null)
//            {
//                findPosToModyfy.get().setColor(newCar.getColor());
//                return new ResponseEntity<>(HttpStatus.OK);
//
//            }
//            else if(newCar.getBrand() != null)
//            {
//                findPosToModyfy.get().setBrand(newCar.getBrand());
//                return new ResponseEntity<>(HttpStatus.OK);
//            }
//
//
//
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//
//    @DeleteMapping
//    public  ResponseEntity<Car> deleteCar(@PathVariable long id)
//    {
//        Optional<Car> findCarToDelete = listOfCars.stream()
//                .filter(car -> car.getId() == id )
//                .findFirst();
//
//        if(findCarToDelete.isPresent())
//        {
//            listOfCars.remove(findCarToDelete);
//            return new ResponseEntity<>(HttpStatus.GONE);
//
//        }
//        else
//        {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        }
//
//    }
//
//    @GetMapping("/color/{color}")
//    public ResponseEntity<List<Car>> getCarByColor(@PathVariable Color color)
//    {
//
//        List<Car> listOfCarsWithColor = new ArrayList<>();
//
//        for(Car car : listOfCars)
//        {
//            System.out.println(car.getColor());
//            if(car.getColor()== color)
//            {
//
//                listOfCarsWithColor.add(car);
//            }
//        }
//        return new ResponseEntity<>(listOfCarsWithColor, HttpStatus.ALREADY_REPORTED);
//
//    };

}
