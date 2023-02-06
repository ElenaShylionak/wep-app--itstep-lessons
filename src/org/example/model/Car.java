//пример использования Lombok/ -Ctrl + O

package org.example.model;

import lombok.Data;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

//Lombok - @Data
//@Data — это удобная сокращённая аннотация, которая содержит в себе возможности из @ToString, @EqualsAndHashCode, @Getter
//@Setter и @RequiredArgsConstructor.
 @Data
 @XmlRootElement
//@XmlRootElement определяет корневой элемент для содержимого XML.
public class Car {
    private int id;
    private String model;
    private double price;

    public Car(int id, String model, double price) { //конструктор с параметрами
        this.id = id;
        this.model = model;
        this.price = price;
    }
    public Car() { //дефолтный конструктор
    }

    public Car(int id) { //конструктор с параметром id
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
