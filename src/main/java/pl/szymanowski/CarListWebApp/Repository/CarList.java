package pl.szymanowski.CarListWebApp.Repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CarList {


    public Car car;
    public List<Car> listOfCars = new ArrayList<>();

    public CarList() {
//        this.listOfCars = new ArrayList<>();
        listOfCars.add(new Car(1L, "VW", "Golf IV", Color.RED));
        listOfCars.add(new Car(2L, "Skoda", "Octavia IV", Color.WHITE));
        listOfCars.add(new Car(3L, "Nissan", "Almera", Color.GREEN));


    }

    public List<Car> getAllCars() {

        return listOfCars;

    }


    public Car getCarById(long id) {

        Car carById = listOfCars.stream()
                .filter(car -> car.getId() == id)
                .findFirst().get();


        return carById;
    }


    public Car addCar(Car car) {
        boolean addCarr = listOfCars.add(car);

        if (addCarr) {
            return car;

        } else {

            return null;
        }
    }


    public Car modCar(Car newCar) {
        Car carToModify = listOfCars.stream()
                .filter(car -> car.getId() == newCar.getId())
                .findFirst().get();


        listOfCars.remove(carToModify);
        listOfCars.add(newCar);
        return car;

    }


    public Car modPositionInCar(Car newCar, long id) {

      Car findPosToModify  = listOfCars.stream()
                .filter(car1 -> car1.getId() == id)
                .findFirst().get();

      if(findPosToModify.getModel() != null)
      {
          findPosToModify.setModel(newCar.getModel());
          return findPosToModify;
      }
      else if(findPosToModify.getColor() != null)
      {
          findPosToModify.setColor(newCar.getColor());
          return findPosToModify;
      }

      else if( findPosToModify.getBrand() != null)
      {
          findPosToModify.setBrand(newCar.getBrand());
          return findPosToModify;

      }
      return findPosToModify;

        }



    public Optional<Car> modCarPos(long id, Car newCar) {
        Optional<Car> modifyCar = listOfCars.stream()
                .filter(car -> car.getId() == id)
                .findFirst();
        if(modifyCar.isPresent()){
            if (newCar.getBrand() != null) modifyCar.get().setBrand(newCar.getBrand());
            if (newCar.getModel() != null) modifyCar.get().setModel(newCar.getModel());
            if (newCar.getColor() != null) modifyCar.get().setColor(newCar.getColor());
        }
        return modifyCar;
    }


    public boolean deleteCar(Car carToDelete)
    {

        Car carToRemove = listOfCars.stream()
                .filter(car1 -> car1.getId() == carToDelete.getId())
                .findFirst().get();

        return listOfCars.remove(carToRemove);

    }


public List<Car> getCarByColor( Color color)
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
            return listOfCarsWithColor;

//        boolean colorValidityCheck = true ;
//
//        for(Color chosenColor : Color.values())
//        {
//            if(chosenColor.equals(color))
//            {
//               colorValidityCheck = true;
//
//            }
//            else
//            {
//                colorValidityCheck = false;
//            }
//
//        }
//
//        if (colorValidityCheck)
//        {
//            List<Car> listOfCarsWithColor = new ArrayList<>();
//            for(Car car : listOfCars)
//            {
//                System.out.println(car.getColor());
//                if(car.getColor()== color)
//                {
//
//                    listOfCarsWithColor.add(car);
//                }
//            }
//            return listOfCarsWithColor;
//
//
//        }
//        else
//        {
//
//            return null;
//        }

    };;


}






//    public Car modCar( Car newCar)
//    {
//      Car modCar  = listOfCars.stream()
//                .filter(car -> car.getId() == newCar.getId())
//                .findFirst().get();
//
//
//
//            listOfCars.remove(modCar);
//            return listOfCars.add(newCar);
//
//
//
//    }





//
//
//
//
//    }

//
//    public Car addCar(Car car)
//    {
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




//    @GetMapping(produces =
//            {
//                    MediaType.APPLICATION_XML_VALUE,
//                    MediaType.APPLICATION_JSON_VALUE
//
//            }
//
//            )
//    public ResponseEntity<List<Car>> getCars(){
//
//        return new ResponseEntity<>(listOfCars, HttpStatus.OK);
//
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Car> getCarById(@PathVariable long id){
//
//        Optional<Car> carById = listOfCars.stream()
//                .filter(car -> car.getId()==id)
//                .findFirst();
//
//        if(carById.isPresent())
//        {
//            return new ResponseEntity<>(carById.get(),HttpStatus.OK);
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
//          if(newCar.getModel() !=  null)
//          {
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
//public ResponseEntity<List<Car>> getCarByColor(@PathVariable Color color)
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
//        return new ResponseEntity<>(listOfCarsWithColor,HttpStatus.ALREADY_REPORTED);
//
//    };



