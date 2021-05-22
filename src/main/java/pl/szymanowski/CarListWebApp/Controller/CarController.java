package pl.szymanowski.CarListWebApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.szymanowski.CarListWebApp.Repository.Car;
import pl.szymanowski.CarListWebApp.Repository.Color;
import pl.szymanowski.CarListWebApp.Service.CarService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cars")
public class CarController {

    CarService carService;

    @Autowired
    public CarController(CarService carService)
    {
        this.carService = carService;

    }



    @GetMapping(produces =
            {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
            )
    public ResponseEntity<List<Car>> getCars(){
if(carService.getCars().isEmpty())
{
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);


}
else
{
    return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);

}


    }

    @GetMapping(value = "/{id}", produces =
            {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<Car>getCarById(@PathVariable long id)
    {
        Optional<Car> carToModify = carService.getCars().stream()
                .filter(car1 -> car1.getId() == id)
                .findFirst();

        if(carToModify.isPresent())
        {

            return new ResponseEntity<>(carService.getCarById(id), HttpStatus.CREATED);

        }
        else
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }



    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car)
    {

        if(car != null)
        {

            return new ResponseEntity<>(carService.addCar(car), HttpStatus.CREATED);

        }
        else
        {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }

    }
@PutMapping(value = "/{id}")
    public ResponseEntity<Car> modifyCar(@RequestBody Car car)
    {

        Optional<Car> carToModify = carService.getCars().stream()
                .filter(car1 -> car1.getId() == car.getId())
                .findFirst();

        if(carToModify.isPresent())
        {
            return new ResponseEntity<>(carService.modCar(car), HttpStatus.CREATED);

        }
        else
        {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Car> posInCarToModyfy(@RequestBody Car newCar, @PathVariable long id)
    {

        Optional<Car> findPosToModyfy = carService.getCars().stream()
                .filter(car -> car.getId() == id)
                .findFirst();

        if(findPosToModyfy.isPresent())
        {
            if(newCar.getModel() !=  null)
            {
                findPosToModyfy.get().setModel(newCar.getModel());
                return new ResponseEntity<>(HttpStatus.OK);

            }
            else if(newCar.getColor() != null)
            {
                findPosToModyfy.get().setColor(newCar.getColor());
                return new ResponseEntity<>(HttpStatus.OK);

            }
            else if(newCar.getBrand() != null)
            {
                findPosToModyfy.get().setBrand(newCar.getBrand());
                return new ResponseEntity<>(HttpStatus.OK);
            }



        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "{/id}")
     public ResponseEntity<Car> deleteCar(Car car)
    {
        Optional<Car> carToRemove = carService.getCars().stream()
                .filter(car1 -> car1.getId() == car.getId())
                .findFirst();

        if(carToRemove.isPresent())
        {
            carService.deleteCar(car);
            return new ResponseEntity<>(HttpStatus.GONE);

        } else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> carsByColor(@PathVariable Color color)
    {
        carService.carsByColor(color);

        if(color != null)
        {
           return  new ResponseEntity<>(carService.carsByColor(color), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }


    }

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



//    @PatchMapping("/{id}")
//    ResponseEntity<Car> ColorInCarToModyfy(@RequestBody Car newCar, @PathVariable long id)
//    {
//
//        Optional<Car> findPosToModify =  carService.getCars().stream()
//                .filter(car -> car.getId() == id)
//                .findFirst();
//
//        if(findPosToModify.isPresent()) {
//
//
//            findPosToModify.get().setColor(newCar.getColor());
//            findPosToModify.get().setModel(newCar.getModel());
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//
//        else
//        {
//
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        }
//    }
//
//    @PatchMapping("/{id}")
//    ResponseEntity<Car> ModelInCarToModyfy(@RequestBody Car newCar, @PathVariable long id)
//    {
//
//        Optional<Car> findPosToModify =  carService.getCars().stream()
//                .filter(car -> car.getId() == id)
//                .findFirst();
//
//        if(findPosToModify.isPresent()) {
//
//            findPosToModify.get().setModel(newCar.getModel());
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//
//        else
//        {
//
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        }
//    }



//        @PutMapping("/{id}")
//        public ResponseEntity modifyCarParameter(@PathVariable long id, @RequestParam Optional<String> newMark,
//        @RequestParam Optional<String> newColor, @RequestParam Optional<String> newModel) {
//        Optional<Car> first = carsList.stream().filter(car -> car.getId() == id).findFirst();
//        if (first.isPresent()) {
//            first.get().setMark(newMark.orElseGet(() -> first.get().getMark()));
//            first.get().setColor(newColor.orElseGet(() -> first.get().getColor()));
//            first.get().setModel(newModel.orElseGet(() -> first.get().getModel()));
//            return new ResponseEntity(HttpStatus.OK);
//        }
//        return new ResponseEntity(HttpStatus.NOT_FOUND);




//        if(findPosToModyfy.isPresent())
//        {
//          if(newCar.getModel() !=  null)
//          {
//
//
//              findPosToModyfy.get().setModel(newCar.getModel());
//              return new ResponseEntity<>(HttpStatus.OK);
//
//          }
//          else if(newCar.getColor() != null)
//          {
//              findPosToModyfy.get().setColor(newCar.getColor());
//              return new ResponseEntity<>(HttpStatus.OK);
//
//          }
//          else if(newCar.getBrand() != null)
//            {
//                findPosToModyfy.get().setBrand(newCar.getBrand());
//                return new ResponseEntity<>(HttpStatus.OK);
//            }





    }




