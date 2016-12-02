package kotlin_generics

/**
 * Created by Johnny Dew on 11/30/16.
 */

class Publisher<out T>(val content: T) {

    fun publish() = content

}

