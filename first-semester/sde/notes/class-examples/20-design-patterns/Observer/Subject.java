package behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject<E> {
    List<Observer<E>> observers = new ArrayList<>();

    public void subscribe(Observer<E> observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer<E> observer) {
        observers.remove(observer);
    }

    public void notifyObservers(E state) {
        for (Observer<E> observer : observers) {
            observer.update(state);
        }
    }
}
