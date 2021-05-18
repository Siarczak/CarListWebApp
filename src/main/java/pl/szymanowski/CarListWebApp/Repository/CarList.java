package pl.szymanowski.CarListWebApp.Repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarList {


    private Car car;
    private List<Car> listOfCars;

    public CarList() {
        this.listOfCars = new ArrayList<>();
        listOfCars.add(new Car(1L,"VW","Golf IV", Color.RED));
        listOfCars.add(new Car(2L,"Skoda","Octavia IV",Color.WHITE));
        listOfCars.add(new Car(3L,"Nissan","Almera",Color.GREEN));


    }

    @GetMapping(produces =
            {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE

            }

            )
    public ResponseEntity<List<Car>> getCars(){

        return new ResponseEntity<>(listOfCars, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id){

        Optional<Car> carById = listOfCars.stream()
                .filter(car -> car.getId()==id)
                .findFirst();

        if(carById.isPresent())
        {
            return new ResponseEntity<>(carById.get(),HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @PostMapping
    public ResponseEntity<Car> addCar(@Valid @RequestBody Car car)
    {

        boolean addCarr= listOfCars.add(car);

        if(addCarr)
        {
            return new ResponseEntity<>(HttpStatus.CREATED);


        }
        else
        {

            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @PutMapping
    public ResponseEntity<Car> modCar(@RequestBody Car newCar)
    {
        Optional<Car> carToModify = listOfCars.stream()
                .filter(car -> car.getId() == newCar.getId())
                .findFirst();

        if(carToModify.isPresent())
        {
            listOfCars.remove(carToModify.get());
            listOfCars.add(newCar);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PatchMapping("/{id}")
    ResponseEntity<Car> posInCarToModyfy(@RequestBody Car newCar, @PathVariable long id)
    {

        Optional<Car> findPosToModyfy = listOfCars.stream()
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


    @DeleteMapping
    public  ResponseEntity<Car> deleteCar(@PathVariable long id)
    {
        Optional<Car> findCarToDelete = listOfCars.stream()
                .filter(car -> car.getId() == id )
                .findFirst();

        if(findCarToDelete.isPresent())
        {
            listOfCars.remove(findCarToDelete);
            return new ResponseEntity<>(HttpStatus.GONE);

        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/color/{color}")
public ResponseEntity<List<Car>> getCarByColor(@PathVariable Color color)
    {

        List<Car> listOfCarsWithColor = new ArrayList<>();

        for(Car car : listOfCars)
        {
            System.out.println(car.getColor());
            if(car.getColor()== color)
            {

                listOfCarsWithColor.add(car);
            }
        }
        return new ResponseEntity<>(listOfCarsWithColor,HttpStatus.ALREADY_REPORTED);

    };


}
