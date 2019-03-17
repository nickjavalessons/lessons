package lesson4;

public class Element <T> {
    private Element previous;
    private Element next;
    private int index;
    private T value;
    public Element(T value){
        this.value = value;
    }
    public Element getPrevious() {
        return previous;
    }
    protected void setPrevious(Element previous) {
        this.previous = previous;
    }
    public Element getNext() {
        return next;
    }
    protected void setNext(Element next) {
        this.next = next;
    }
    public int getIndex() {
        return index;
    }
    protected void setIndex(int index) {
        this.index = index;
    }
    public T getValue() {
        return value;
    }
    protected void setValue(T value) {
        this.value = value;
    }
}
