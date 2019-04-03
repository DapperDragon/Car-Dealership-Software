
public abstract interface SortedADT {

    public void insertCar(String string);

    public void insert(Car currentCar);

    
    public class NotFoundException extends Exception{}

    public class NotUniqueException extends Exception{}

    
    // returns the object found
    public abstract Comparable remove(Comparable object) throws NotFoundException;

    // returns the object removed
    public abstract Comparable find(Comparable object) throws NotFoundException;
}
