package java_generics;

/**
 * Created by Johnny Dew on 11/21/16.
 */

public class Box<T> {

    public static <U> void transferContent(Box<U> from, Box<U> to) {
        to.setContent(from.getContent());
    }

    public void copyContentFromBox(Box<T> source) {
        setContent(source.getContent());
    }

    public void copyContentFromBoxUsingWildcards(Box<? extends T> source) {
        setContent(source.getContent());
    }

    public void copyContentToBox(Box<T> target) {
        target.setContent(getContent());
    }

    public void copyContentToBoxUsingWildcards(Box<? super T> target) {
//        ScottishFoldCat x = (ScottishFoldCat) target.getContent();
//        x.scottishFoldCatSpecificMethod();
        target.setContent(getContent());
    }

    ///

    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

}
