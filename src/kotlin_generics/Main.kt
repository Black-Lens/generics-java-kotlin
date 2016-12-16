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
    testTypeProjection()
    testTypeProjectionWithBoxCopy()
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

fun testTypeProjection() {
    val mutableList = mutableListOf(1, 2, 3)
    System.out.println("testTypeProjection: initial mutable list: $mutableList")
    mutableList.add(4)
    System.out.println("testTypeProjection: mutable list after add a new member: $mutableList")

    // out-projected
    val outProjectedList: MutableList<out Int> = mutableList
    // compile error. outProjectedList is out-projected type. So, cannot use add()
//    outProjectedList.add(5)
    val last1 = outProjectedList.last()
    System.out.println("testTypeProjection: out-projected list: $outProjectedList")
    System.out.println("testTypeProjection: out-projected got last element as type: ${last1.javaClass.canonicalName}")

    // in-projected
    val inProjectedList: MutableList<in Int> = mutableList
    inProjectedList.add(5)
    System.out.println("testTypeProjection: in-projected list: $inProjectedList")
    // Although, you can call last() but you will get Any? not Int.
    val last2 = inProjectedList.last()
}

fun testTypeProjectionWithBoxCopy() {
    val catBox = GenericBox<Cat>()
    catBox.content = Cat()
    val catBox2 = GenericBox<Cat>()
    GenericBox.copy(catBox, catBox2)
    System.out.println("testTypeProjectionWithBoxCopy: catBox contains ${catBox.content}, catBox2 contains ${catBox2.content}")
    val animalBox = GenericBox<Animal>()
    // compile error
//    GenericBox.copy(catBox, animalBox)
    // this is ok
    GenericBox.copy2(catBox, animalBox)
    System.out.println("testTypeProjectionWithBoxCopy: catBox contains ${catBox.content}, animalBox contains ${animalBox.content}")
}


