package kotlin_generics

/**
 * Created by Johnny Dew on 11/28/16.
 */

// default upper bound is Any? So, use Any to leverage non-null type.
class GenericBox<T : Any> {

    companion object {
        fun <T : Any> copy(source: GenericBox<T>, dest: GenericBox<T>) {
            dest.content = source.content
        }
        fun <T : Any> copy2(source: GenericBox<out T>, dest: GenericBox<in T>) {
            dest.content = source.content
        }
    }

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

}
