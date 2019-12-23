package ru.job4j.carstore.persistence;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;

import org.junit.Test;

import ru.job4j.carstore.models.*;


public class DBCarStoreageTest {

CarStoreageDB store = CarStoreageDB.getInstance();
	
	@Test
	public void whenAddCarEntityThanGetItFromDB() {
		
		Body body = new Body("coupe");
		Engine engine = new Engine(FuelType.DIESEL, 1.3, 134);
		Car car = new Car(body, engine);
		
		store.save(car);
		List<Car> cars = store.getAll();
		Car result = cars.get(0);
		
		assertThat(result, is(car));
	}

	@Test
	public void whenAddandDeleteEntityThenEmptyBase() {
		List<Car> cars = store.getAll();
		Car result = cars.get(0);
		System.out.println(result);
		//assertThat(result, is(car));
	}

}
