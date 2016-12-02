package kotlin_generics

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
        // You cannot read T from consumer.
//        content = box.content
        box.content = content
    }

}
