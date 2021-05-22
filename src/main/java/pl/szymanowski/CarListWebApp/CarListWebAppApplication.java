package pl.szymanowski.CarListWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CarListWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarListWebAppApplication.class, args);


//		Car car1= new Car(1,"cs","cs", Color.RED);
//		CarList newCar = new CarList();
//
//		newCar.getAllCars();
//
//		System.out.println(newCar.getCarById(1));
//		System.out.println(newCar.getCarById(4));
//
//		CarService service = new CarService(newCar);




	}





}
