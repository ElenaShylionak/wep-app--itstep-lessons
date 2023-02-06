package org.example.dao.impl;

import org.example.model.Car;

import java.util.ArrayList;
import java.util.List;



public class CarsDAO {

    private static CarsDAO instance = null;
    private static List<Car> cars = new ArrayList<>(); //создаем объект

    static {
        cars.add(new Car(1,"BMW M3", 51000)); //поля инициализации, мнинмая база данных для тестов
        cars.add(new Car(2,"AUDI RS3", 55000));
        cars.add(new Car(3,"BMW M5", 60000));
    }

    private CarsDAO(){} //закрытый конструктор (синглонтон)

    public static CarsDAO getInstance() {
        if(instance == null){
            instance = new CarsDAO();
        }
        return instance; // Instance - это экземпляр.
    }

    public List<Car> all(){
        return cars;
    }

    public int add(Car car){ //метод add - чтобы добавить машину машину в список
        int newId = cars.size() + 1;
        car.setId(newId);
        cars.add(car);
        return newId;
    }

    public Car getById(int id){ //получить по ид
        Car car = new Car(id);
        int pos = cars.indexOf(car);
        if(pos >= 0){
            return cars.get(pos);
        }
        return null;
    }

    public boolean update(Car car){ //обновить
        int pos = cars.indexOf(car);
        if(pos >= 0){
            cars.set(pos, car);
            return true;
        }
        return false;
    }

    public boolean delete(int id){ //удалить
        Car car = new Car(id);
        int pos = cars.indexOf(car);
        if(pos >= 0){
            return cars.remove(car);
        }
        return false;
    }




}
