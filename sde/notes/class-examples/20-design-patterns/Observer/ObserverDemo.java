package behavioral.observer;

public class ObserverDemo {
   public static void main(String[] args) {
      IntegerSubject subject = new IntegerSubject(8);

      HectalObserver h = new HectalObserver();
      OctalObserver o = new OctalObserver();
      BinaryObserver b = new BinaryObserver();
      //attach the observers
      subject.subscribe(h);
      subject.subscribe(o);
      subject.subscribe(b);

      //perform a state change
      System.out.println("First state change: 15");	
      subject.setState(15);
      
      //detach the binary observer
      subject.unsubscribe(b);
      
      //perform a second state change
      System.out.println("Second state change: 10");	
      subject.setState(10);
   }
}