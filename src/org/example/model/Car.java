//пример использования Lombok/ -Ctrl + O

package org.example.model;

import lombok.Data;

@Data
public class Car {
    private Long id;
    private String color;
}
