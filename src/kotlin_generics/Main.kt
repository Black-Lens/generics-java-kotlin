package kotlin_generics

import java_generics.entity.Animal
import java_generics.entity.Cat
import java_generics.entity.PersianCat
import java_generics.entity.ScottishFoldCat

/**
 * Created by Johnny Dew on 11/28/16.
 */

fun main(args: Array<String>) {
    testAnyBox()
    testGenericBox()
    testPublisher()
    testSubscriber()
}

fun testAnyBox() {
    val anyBox = AnyBox()
    anyBox.content = "Hello Any Box"
    // anyString is a type of Any not String
    val anyString = anyBox.content
    System.out.println("testAnyBox: string content: $anyString")

    // ClassCastException
//    val anyInt = anyBox.content as Integer
//    System.out.println("testAnyBox: integer content: $anyInt")
}

fun testGenericBox() {
    val genericBox1 = GenericBox<String>()
    genericBox1.content = "Hello Generic box"

    val genericBox2 = GenericBox<String>()
    genericBox2.copyFromBox(genericBox1)

    val genericBox3 = GenericBox<String>()
    genericBox2.copyToBox(genericBox3)

    System.out.println("testGenericBox: box1 content: ${genericBox1.content}")
    System.out.println("testGenericBox: box2 content: ${genericBox2.content}")
    System.out.println("testGenericBox: box3 content: ${genericBox3.content}")
}

/**
 * Illustrate
 * - Declaration-site variance.
 * - Producer.
 * - Covariant.
 */
fun testPublisher() {
    // This is covariant since Publisher of Animal can references to Publisher of Cat.
    val publisher: Publisher<Animal> = Publisher(Cat())
    // got Cat instance
    System.out.println("testPublisher: publisher publishes ${publisher.publish()}")

}

/**
 * Illustrate
 * - Declaration-site variance.
 * - Consumer.
 * - Contravariant.
 */
fun testSubscriber() {
    // This is contravariant since Subscriber of PersianCat can references to Subscriber of Cat.
    val persianCatSubscriber: Subscriber<PersianCat> = Subscriber<Cat>()
    persianCatSubscriber.onEvent(PersianCat())
    // compile error.
//    persianCatSubscriber.onEvent(ScottishFoldCat())
//    persianCatSubscriber.onEvent(Cat())

    // This is also contravariant.
    val scottishFoldCatSubscriber: Subscriber<ScottishFoldCat> = Subscriber<Cat>()
    scottishFoldCatSubscriber.onEvent(ScottishFoldCat())
}



