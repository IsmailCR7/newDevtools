package ru.mentee.power.devtools.student;

import java.io.Serializable;
import java.util.Objects;

public record Student(String name, String city) implements Serializable {
    
    // Исправлено: camelCase и пробел после if
    public boolean isFromCity(String targetCity) {
        if (targetCity == null) {
            return false;
        }
        return Objects.equals(this.city, targetCity);
    }
}
