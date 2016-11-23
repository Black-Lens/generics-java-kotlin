package java_generics;

import java_generics.entity.Animal;

/**
 * Created by Johnny Dew on 11/21/16.
 *
 * Generic box which upper bound is Animal.
 *
 */

public class AnimalBox<T extends Animal> {

    private T animal;

    public T getAnimal() {
        return animal;
    }

    public void setAnimal(T animal) {
        this.animal = animal;
    }

    public String poke() {
        return animal.say();
    }

}
