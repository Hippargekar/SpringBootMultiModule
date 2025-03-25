package org.root.generic;

import java.util.*;

public class GenericTest {
//    S is-a subtype of T
//    Box<S> is NOT a subtype of Box<T>

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal());
        animals.add(new Dog());
        animals.add(new BlackDog());

//      Covariance
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog());
//      List<Animal> animal1 = dogs; // compilation error
        List<? extends Animal> animal1 = dogs;
        processAnimals(dogs);
    }

//    public static void main(String[] args) {
//        List<Animal> animals = new ArrayList<>();
//        animals.add(new Animal());
//        animals.add(new Dog());
//
////      Contravariance
////      List<Dog> dogs1 = animals; // compilation error
//        List<? super Dog> dogs1 = animals;
//        processParents(animals);
//    }

    public static void processAnimals(List<? extends Animal> animals) {
//        animals.add(new Dog()); animals.add(new Animal()); // compile time error
        animals.add(null);
        Animal getDog = animals.get(0);
        for (Animal animal : animals) {
            animal.getType();
        }

    }

//    List<Dog>
//    List<Animal> (since Animal is a superclass of Dog)
//    List<Object> (since Object is the root superclass of all classes)
    public static void processParents(List<? super Dog> dogs) {
       // dogs.add(new Animal());
        dogs.add(new Dog());
        dogs.add(new BlackDog());

//        Dog getDog = dogs.get(0);// compile time error, can't guarantee the exact type
        Object o = dogs.get(0);// Valid, since any supertype of Dog can be assigned to Object
    }
}
