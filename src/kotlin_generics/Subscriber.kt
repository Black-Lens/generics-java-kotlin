package kotlin_generics

/**
 * Created by Johnny Dew on 11/30/16.
 */

class Subscriber<in T> {

    fun onEvent(event: T) {
        // do something with the event.
        System.out.println("onEvent: $event")
    }

}
