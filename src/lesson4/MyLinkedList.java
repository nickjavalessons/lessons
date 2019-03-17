package lesson4;

public class MyLinkedList<T> {
    private Element<T> tail;
    private Element<T> head;

    public void add(T value){
        Element<T> element = new Element(value);
        if(head == null){
            head = element;
        }
        if(tail == null){
            element.setIndex(0);
            tail = element;
        } else {
            Element<T> tailElement = tail;
            tailElement.setNext(element);
            element.setPrevious(tailElement);
            element.setIndex(tailElement.getIndex() + 1);
            tail = element;
        }
    }
    public void add(T value, int index){
        if(tail == null || index > tail.getIndex()){
            add(value);
            return;
        }
        Element<T> elementToMove = get(index);
        Element<T> newElement = new Element<>(value);
        newElement.setPrevious(elementToMove.getPrevious());
        newElement.setNext(elementToMove);
        newElement.setIndex(index);
        elementToMove.setPrevious(newElement);
        if(elementToMove != head) {
            newElement.getPrevious().setNext(newElement);
        } else {
            head = newElement;
        }
        increaseIndexes(newElement);
    }
    public T remove(){
        if(isEmpty()) return null;
        T value = tail.getValue();
        Element<T> previousElement = tail.getPrevious();
        if(previousElement != null) {
            previousElement.setNext(null);
        } else {
            head = null;
        }
        tail = previousElement;
        return value;
    }
    public T remove(int index){
        if(isEmpty()) return null;
        if(index > tail.getIndex()) return remove();
        Element<T> elementToRemove = get(index);
        if(elementToRemove != head) {
            elementToRemove.getPrevious().setNext(elementToRemove.getNext());
        } else {
            head = elementToRemove.getNext();
        }
        elementToRemove.getNext().setPrevious(elementToRemove.getPrevious());
        decreaseIndexes(elementToRemove);
        return elementToRemove.getValue();
    }
    public Element<T> get(int index){
        if(tail == null || index > tail.getIndex()){
            return null;
        }
        if(index <= 0) return head;
        Element<T> el = head;
        while(el.getIndex() != index){
            el = el.getNext();
        }
        return el;
    }
    public boolean isEmpty(){
        if(tail == null) return true;
        return false;
    }
    public boolean contains(T value){
        if( isEmpty()) return false;
        Element<T> el = head;
        while(el != null){
            if(el.getValue() == value) return true;
            el = el.getNext();
        }
        return false;
    }
    public int size(){
        if(isEmpty()) return 0;
        return tail.getIndex() + 1;
    }

    private void increaseIndexes(Element<T> newElement) {
        Element<T> el = newElement;
        if(el == head) el.setIndex(0);
        while (el.getNext() != null){
            el = el.getNext();
            el.setIndex(el.getIndex() + 1);
        }
    }
    private void decreaseIndexes(Element<T> oldElement) {
        Element<T> el = oldElement;
        while (el.getNext() != null){
            el = el.getNext();
            el.setIndex(el.getIndex() - 1);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[ ");
        Element<T> el = head;
        while(el != null){
            sb.append(el.getIndex()).append(":").append(el.getValue().toString()).append(" ");
            Element nextEl = el.getNext();
            el = nextEl;
        }
        sb.append("]");
        return sb.toString();
    }
}
