package java_generics;

import java_generics.entity.Animal;
import java_generics.entity.Cat;
import java_generics.entity.PersianCat;
import java_generics.entity.ScottishFoldCat;

/**
 * Created by Johnny Dew on 11/21/16.
 */

public class Main {

    public static void main(String... args) {
        testOldBox();

        // uncomment each one to test.
//        testOldBoxClassCastException();
//        testOldBoxSetWrongType();

        testGenericBox();
        testAnimalBox();
        testVariance();
        testTransferContent();
        testCopyFromBox();
        testCopyFromBoxUsingWildcards();
        testCopyContentToBox();
        testCopyToBoxUsingWildcards();
    }

    // ================================ Pre-generics era test ====================== //

    private static void testOldBox() {
        OldBox myBox = new OldBox();
        myBox.setContent("I am a String in a little old box");
        String boxString = (String) myBox.getContent();
        System.out.println("testOldBox: " + boxString);
    }

    private static void testOldBoxClassCastException() {
        OldBox myBox = new OldBox();
        myBox.setContent("I am a String in a little old box");
        Integer boxInteger = (Integer) myBox.getContent();  // ClassCastException
        System.out.println("testOldBoxClassCastException: " + boxInteger);
    }

    private static void testOldBoxSetWrongType() {
        OldBox myBox = new OldBox();
        myBox.setContent("I am a String in a little old box");
        myBox.setContent(1);    // yes, you can do this.
        String boxString = (String) myBox.getContent();     // ClassCastException
        System.out.println("testOldBoxSetWrongType: " + boxString);
    }

    // ================================ Generics era test ====================== //

    private static void testGenericBox() {
        Box<String> myBox = new Box<>();
        myBox.setContent("testGenericBox: I am a String in a generic box");
        String boxString = myBox.getContent();  // no casting required
        System.out.println(boxString);
//        myBox.setContent(1);    // compile error
//        Integer boxInteger = myBox.getContent();    // compile error
    }

    // ================================ Bounded Type test ====================== //

    private static void testAnimalBox() {
//        AnimalBox<String> stringBox = new AnimalBox<>();      // compile error
        AnimalBox<Cat> catBox = new AnimalBox<>();
        Cat myCat = new Cat();
        catBox.setAnimal(myCat);
//        catBox.setAnimal("Animal");     // compile error
        System.out.println("testAnimalBox: " + catBox.poke());
    }


    // ================================ Variance test ====================== //

    private static void testVariance() {
        Animal animal = new Cat();      // this is fine
        Box<Animal> box = new Box<>();    // this is fine

//        Box<Animal> box2 = new Box<Cat>();    // compile error, Invariant: Box<Cat> is not a subclass of Box<Animal>

        Box<Cat> catBox = new Box<>();
        catBox.setContent(new Cat());
        Box<? extends Animal> box3 = catBox;    // Covariant
        System.out.println("testInvariance: " + box3.getContent().say());    // "Meow"

        Box<Cat> newCatBox = new Box<>();
        Box<? super Cat> box4 = newCatBox;      // Contravariant
        box4.setContent(new Cat());
        System.out.println("testInvariance: " + newCatBox.getContent().say());    // "Meow"

    }

    // ===================== Box.transferContent test ====================== //

    private static void testTransferContent() {
        // transfer content of type T
        Box<Cat> catBox1 = new Box<>();
        catBox1.setContent(new PersianCat());
        Box<Cat> catBox2 = new Box<>();
        catBox2.setContent(new ScottishFoldCat());
        Box.transferContent(catBox1, catBox2);
        System.out.println("testTransferContent: " + catBox1.getContent().say());   // "Meow Persian"
        System.out.println("testTransferContent: " + catBox2.getContent().say());   // "Meow Persian"
//        ((ScottishFoldCat) catBox2.getContent()).scottishFoldCatSpecificMethod();   // crashed
    }

    // ===================== copy content test ====================== //

    private static void testCopyFromBox() {
        Box<PersianCat> persianCatBox1 = new Box<>();
        persianCatBox1.setContent(new PersianCat());
        Box<PersianCat> persianCatBox2 = new Box<>();
        persianCatBox2.copyContentFromBox(persianCatBox1);
        System.out.println("testCopyFromBox: " + persianCatBox2.getContent().say());   // "Meow Persian"

        Box<PersianCat> persianCatBox = new Box<>();
        persianCatBox.setContent(new PersianCat());
        Box<Cat> catBox = new Box<>();
//        catBox.copyContentFromBox(persianCatBox);     // compile error
    }

    private static void testCopyFromBoxUsingWildcards() {
        Box<PersianCat> persianCatBox = new Box<>();
        persianCatBox.setContent(new PersianCat());
        Box<Cat> catBox = new Box<>();
        catBox.copyContentFromBoxUsingWildcards(persianCatBox);
        System.out.println("testCopyFromBoxUsingWildcards: " + catBox.getContent().say());
    }

    // ===================== set content test ====================== //

    private static void testCopyContentToBox() {
        Box<ScottishFoldCat> scottishFoldCatBox1 = new Box<>();
        scottishFoldCatBox1.setContent(new ScottishFoldCat());
        Box<ScottishFoldCat> scottishFoldCatBox2 = new Box<>();
        scottishFoldCatBox1.copyContentToBox(scottishFoldCatBox2);
        System.out.println("testCopyContentToBox: " + scottishFoldCatBox2.getContent().say());

        Box<Cat> catBox = new Box<>();
//        scottishFoldCatBox1.copyContentToBox(catBox);     // compile error
    }

    private static void testCopyToBoxUsingWildcards() {
        Box<ScottishFoldCat> scottishFoldCatBox = new Box<>();
        scottishFoldCatBox.setContent(new ScottishFoldCat());
        Box<Cat> catBox = new Box<>();
        scottishFoldCatBox.copyContentToBoxUsingWildcards(catBox);
        System.out.println("testCopyToBoxUsingWildcards: " + catBox.getContent().say());
    }



}
