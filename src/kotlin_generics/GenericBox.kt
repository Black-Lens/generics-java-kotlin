package kotlin_generics

import java_generics.entity.Cat
import java_generics.entity.PersianCat

/**
 * Created by Johnny Dew on 11/28/16.
 */


// default upper bound is Any? So, use Any to leverage non-null type.
class GenericBox<T : Any> {

    lateinit var content: T

    // use-site variance
    fun copyFromBox(box: GenericBox<out T>) {
        // You cannot write data to Producer.
//        box.content = content
        content = box.content
    }

    // use-site variance
    fun copyToBox(box: GenericBox<in T>) {
        // Although, you can read content from consumer. You only get Any?
//        val x = box.content

        box.content = content
    }

    fun displayBoxContent(box: GenericBox<*>) {
        System.out.println(box.content)

        // compile error, setter for box.content is removed by type projection.
//        box.content = this.content

        // compile error, required T found Any
//        this.content = box.content

    }

}

fun <T : Any> copyBox(source: GenericBox<T>, dest: GenericBox<T>) {
    dest.content = source.content
}

fun <T : Any> copyBox2(source: GenericBox<out T>, dest: GenericBox<in T>) {
    dest.content = source.content
}

class CatBox<T : Cat> {

    lateinit var content: T

    fun listenToUnknownTypeCat(box: CatBox<*>) {
        // type of cat is Cat.
        val cat = box.content
        cat.say()

        // setter for box.content is removed by type projection.
//        box.content = content
    }

    fun listenToSameTypeCat(box: CatBox<out T>) {
        // type of specificCat is T
        val specificCat = box.content
        specificCat.say()
        // setter for box.content is removed by type projection.
//        box.content = content
    }

}

fun testCatBox() {
    val list1 = listOf<String>()
    val list2 = listOf("a")

    val catBox = CatBox<Cat>()
    val catBox2 = CatBox<Cat>()
    val persianCatBox = CatBox<PersianCat>()
    val persianCatBox2 = CatBox<PersianCat>()
    // TODO : set content for those boxes.

    catBox.listenToUnknownTypeCat(catBox2)
    catBox.listenToUnknownTypeCat(persianCatBox)
    catBox.listenToSameTypeCat(catBox2)
    catBox.listenToSameTypeCat(persianCatBox)

    persianCatBox.listenToUnknownTypeCat(catBox)
    persianCatBox.listenToUnknownTypeCat(persianCatBox2)
    persianCatBox.listenToSameTypeCat(persianCatBox2)
    // error, expected box of PersianCat
//    persianCatBox.listenToSameTypeCat(catBox)

}

fun checkAny(payload: Any) {
    if (payload is CatBox<*>) {
        if (payload.content is PersianCat) {
            // do something
        }
    }

    // compile error, cannot check for instance of erased type.
//    if (payload is CatBox<PersianCat>) {
//        // do something
//    }
}

interface Consumer<in T> {
    fun consume(value: T)
}

interface Producer<out T> {
    fun produce(): T
}

class BoxConsumer<out T>(val content: T) : Producer<T> {

    override fun produce() = content

//    override fun consume(value: T) {
//
//    }

}

fun testBoxConsumer() {
    val bc = BoxConsumer(Cat())

//    if (bc is BoxConsumer) {
//    }

//    if (bc is Producer<Dog>) {
//
//    }

//    if (bc is Consumer<Cat>) {
//    }
//
//    if (bc is List<Cat>) {
//    }

    val list = listOf(1, 2, 3)
//    val list = listOf("a")
//    if (list is List<String>) {}
    if (list is List<*>) {
    }

    if (list is List<Int>) {
    }

}