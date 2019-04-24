import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Dealership {

    //list of manufacturers and all cars
    private static List instancesOfManufacturers = new ArrayList();
    private static List instancesOfAllCars = new ArrayList(); 
    
    //return list of manufacturers to DealershipTest
    public static List getInstances() {
        return instancesOfManufacturers;
    }
    
    //return sorted list of cars to DealershipTest
    public static List getAllCars() {
        Collections.sort(instancesOfAllCars); 
        return instancesOfAllCars;
    }

    public class NotFoundException extends Exception {
    }

    public class NotUniqueException extends Exception {
    }
    
    public abstract class SortedLinkedList implements SortedADT {
    }
    
    //car node used within tree
    public ListNode head;

    public class CarNode {
        public Car car;
        public CarNode next;
        public CarNode current;
    }

    //dealership node used within tree
    protected class DealershipNode {
        public String Manufacturer;
        public DealershipNode left;
        public DealershipNode right;
        public CarNode carhead;
    }
    
    //initial dealership nodes
    protected DealershipNode root;
    protected DealershipNode current;
    protected DealershipNode parent;
 
    
    //creates string used to build tree
    public String toString(Integer option) {
        String treeDetails = new String();
        //if root of tree is null then no manufacturers messege displayed
        if (this.root != null) {
            treeDetails += this.getTree(this.root, 0, option);
        } else {
            treeDetails += "No manufacturers found";
        }
        return treeDetails;
    }

    //create tree, option is sent from DealershipTest to determine if cars is added or not
    public String getTree(DealershipNode current, Integer level, Integer option) {
        String treeDetails = new String();
        level++;
        if (current != null) {
            treeDetails += this.getTree(current.right, level, option);
            
            //spacing for manufacturer names in tree
            for (Integer i = 0; i < level; i++) {
                treeDetails += "    ";
            }
            
            //add manufacturer name
            treeDetails += current.Manufacturer + "\n";
            
            //checks options sent from DealershpTest
            //if the option is 1, add car details
            //else the tree only shows manufacturers
            if(option ==1){
                //check list of all cars for cars made by said manufacturer
                //adds below manufacturer name
                for(int j=0; j< instancesOfAllCars.size(); j++){
                    if(instancesOfAllCars.get(j).toString().contains(current.Manufacturer + "\t" + "\t")){
                        for (Integer i = 0; i < level; i++) {
                            treeDetails += "    ";   
                        }
                    treeDetails += instancesOfAllCars.get(j);
                    }
                }
            }
            treeDetails += this.getTree(current.left, level, option);

        } else {
            for (Integer i = 0; i < level; i++) {
                treeDetails += "    ";
            }
            treeDetails += "null\n";
        }
        return treeDetails;
    }
    
   
    
    
    //insert manufacturer
    //takes manufacturer from the car and adds to list of manufacturer
    //creates node for the manufacturer
    public void insert(Object car) throws NotUniqueException {
        DealershipNode newNode = new DealershipNode();
        newNode.Manufacturer = car.toString();
        if (this.root == null) {
            this.root = newNode;
        } else {
            this.insert(newNode, this.root);    
        }
        instancesOfManufacturers.add(newNode.Manufacturer +"\n");
    }
    
    //compares maufacturer to check they are unique
    public void insert(DealershipNode newNode, DealershipNode current)throws NotUniqueException {
        //manufacturer already added
        if (newNode.Manufacturer.compareTo(current.Manufacturer) == 0) {
            throw new NotUniqueException();
        }

        //manufacturer added to left of root
        if (newNode.Manufacturer.compareTo(current.Manufacturer) < 0) {
            if (current.left == null) {
                current.left = newNode;
                current.left.carhead = null;
            } else {
                this.insert(newNode, current.left);
                newNode.carhead = null;
            }
            
        //manufacturer added to right of root
        } else if (current.right == null) {
            current.right = newNode;
            current.right.carhead = null;
        } else {
            this.insert(newNode, current.right);
        }
    }

    //find manufacturer
    public Comparable find(Comparable Manufacturer) throws NotFoundException {
        return this.find(Manufacturer, this.root);
    }

    //checks nodes for matching manufacturers
    private Comparable find(Comparable Manufacturer, DealershipNode current)throws NotFoundException {
        Comparable foundObject;
        if (current != null) {
            if (Manufacturer.compareTo(current.Manufacturer) == 0) {
                this.current = current;
                foundObject = current.Manufacturer;
            } else {
                this.parent = current;
                if (Manufacturer.compareTo(current.Manufacturer) < 0) {
                    foundObject = this.find(Manufacturer, current.left);
                } else {
                    foundObject = this.find(Manufacturer, current.right);
                }
            }
        } else {
            throw new NotFoundException();
        }
        return foundObject;
    }

    
    
    
    //remove manufacturer
    public Comparable remove(Comparable Manufacturer) throws NotFoundException {
        // sets up parent and current
        Comparable removedObject = this.find(Manufacturer);
        if (this.current.left == null && this.current.right == null) {
            this.replaceNode(null);
        } else if (this.current.left != null && this.current.right == null) {
            this.replaceNode(this.current.left);
            this.current.left = null;
        } else if (this.current.left == null && this.current.right != null) {
            this.replaceNode(this.current.right);
            this.current.right = null;
        } else {
            this.replaceWithNextLargest(this.current, this.current, this.current.right);
        }
        for(int j=0; j< instancesOfAllCars.size(); j++){
            if(instancesOfAllCars.get(j).toString().contains(Manufacturer + "\t" + "\t"))
                instancesOfAllCars.remove(instancesOfAllCars.get(j));
        }
        instancesOfManufacturers.remove(Manufacturer.toString()+"\n");
        return removedObject;
    }

    private void replaceNode(DealershipNode replacement) {
        if (this.current == this.root) // removing root
        {
            this.root = replacement;
        } else if (this.current == this.parent.left) {
            this.parent.left = replacement;
        } else {
            this.parent.right = replacement;
        }
        this.current.Manufacturer = null;
    }

    private void replaceWithNextLargest(DealershipNode nodeForDeletion, DealershipNode parent, DealershipNode current) {
        if (current.left == null) {
            nodeForDeletion.Manufacturer = current.Manufacturer;
            if (parent == nodeForDeletion) {
                parent.right = current.right;
            } else {
                parent.left = current.right;
            }
            current.Manufacturer = null;
            current.right = null;
        } else {
            this.replaceWithNextLargest(nodeForDeletion, current, current.left);
        }
    }
    
    
    //insert car
    //adds cars.toString to the list of all cars
    public void insertCar(Car car, String Manufacturer) { 

        //does not allow multiple cars to use the same registration number
        if(instancesOfAllCars.toString().contains(car.RegistrationNumber)){
            System.out.println("Registration has already been used!");
        } 
        else{
            instancesOfAllCars.add(Manufacturer + "\t" + "\t" +car.toString()+"\n");
            CarNode newNode = new CarNode();
            newNode.car = car;
            if (this.current.carhead == null) {
                this.current.carhead = newNode;
            } else {
                Dealership.CarNode curr = this.current.carhead;
                this.current.carhead = newNode;
                newNode.next = curr;
            }
        }
    }    

    //remove car
    //removes from the list of all cars, information inside brackets must match what is added exactly!
    public void removeCar(Car car, String Manufacturer){
        instancesOfAllCars.remove(Manufacturer + "\t" + "\t" +car.toString()+"\n"); 
    }
}

    

