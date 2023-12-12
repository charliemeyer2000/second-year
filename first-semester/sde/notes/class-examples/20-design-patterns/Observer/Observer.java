package behavioral.observer;

public interface Observer<E> {
    public abstract void update(E e);
}
