//point to tree node
//

/**
 *
 * @author B00330262
 */
public class Dealership {

    public class NotFoundException extends Exception {
    }

    public class NotUniqueException extends Exception {
    }
//public class NotFoundException extends Exception{}

    public class CarNode {

        public Car car;
        public CarNode next;
        //public CarNode carhead;
        public CarNode current;
        
       

    }

    protected class DealershipNode {

        public String Manufacturer;
        public DealershipNode left;
        public DealershipNode right;
        public CarNode carhead;
        

    }
    
    
    
    
    
    

    protected DealershipNode root;
    // set by find to allow remove to work
    protected DealershipNode current;
    protected DealershipNode parent;

    public String toString() {
        String treeDetails = new String();
        if (this.root != null) {
            treeDetails += this.getTree(this.root, 0);
        } else {
            treeDetails += "No manufacturers found";
        }
        return treeDetails;
    }

    public String getTree(DealershipNode current, Integer level) {
        String treeDetails = new String();
        level++;
        if (current != null) {
            treeDetails += this.getTree(current.right, level);
            for (Integer i = 0; i < level; i++) {
                treeDetails += "    ";
            }
            treeDetails += current.Manufacturer + "\n";
            if(current.carhead != null){

            treeDetails += current.carhead.car.toString() + "\n";
            if(current.carhead.next != null){
                treeDetails += current.carhead.next.car.toString() + "\n";
                
                
                
                
                   
  
        String listDetails = new String();
        if (current.carhead != null) {
            CarNode currentNode = current.carhead;
            while (current.carhead.next != null) {
                listDetails += current.carhead.next.car.toString() + "\n";
                currentNode = current.carhead.next;
            }
        }
        else{
            listDetails+="list is empty";
        }
        return listDetails;
    }
                
                
            }         
                
                
            
             
            treeDetails += this.getTree(current.left, level);

        } else {
            for (Integer i = 0; i < level; i++) {
                treeDetails += "    ";
            }
            treeDetails += "null\n";
        }
        return treeDetails;
    }

    
   /* working manufacturer display
    
        private String getTree(DealershipNode current, Integer level) {
        String treeDetails = new String();
        level++;
        if (current != null) {
            treeDetails += this.getTree(current.right, level);
            for (Integer i = 0; i < level; i++) {
                treeDetails += "    ";
            }
            treeDetails += current.Manufacturer + "\n";
            treeDetails += this.getTree(current.left, level);

        } else {
            for (Integer i = 0; i < level; i++) {
                treeDetails += "    ";
            }
            treeDetails += "null\n";
        }
        return treeDetails;
    }
*/
    
    
    
    
   /*
    

    private String getInOrder(DealershipNode current) {
        String inOrderDetails = new String();
        if (current != null) {
            inOrderDetails += this.getInOrder(current.left);
            inOrderDetails += current.Manufacturer + "  ";
            inOrderDetails += this.getInOrder(current.right);
        }
        return inOrderDetails;
    }

    private String getPreOrder(DealershipNode current) {
        String preOrderDetails = new String();
        if (current != null) {
            preOrderDetails += current.Manufacturer + "  ";
            preOrderDetails += this.getPreOrder(current.left);
            preOrderDetails += this.getPreOrder(current.right);
        }
        return preOrderDetails;
    }

    private String getPostOrder(DealershipNode current) {
        String postOrderDetails = new String();
        if (current != null) {
            postOrderDetails += this.getPostOrder(current.left);
            postOrderDetails += this.getPostOrder(current.right);
            postOrderDetails += current.Manufacturer + "  ";
        }
        return postOrderDetails;
    }

    private String getReverseOrder(DealershipNode current) {
        String reverseOrderDetails = new String();
        if (current != null) {
            reverseOrderDetails += this.getReverseOrder(current.right);
            reverseOrderDetails += current.Manufacturer + "  ";
            reverseOrderDetails += this.getReverseOrder(current.left);
        }
        return reverseOrderDetails;
    }
    */ 
    
    public void insert(Object car) throws NotUniqueException {

        DealershipNode newNode = new DealershipNode();
        newNode.Manufacturer = car.toString();
        if (this.root == null) {
            this.root = newNode;
        } else {
            this.insert(newNode, this.root);
        }
    }
    
    
    public void insert(DealershipNode newNode, DealershipNode current)
            throws NotUniqueException {

        if (newNode.Manufacturer.compareTo(current.Manufacturer) == 0) {
            throw new NotUniqueException();
        }
        if (newNode.Manufacturer.compareTo(current.Manufacturer) < 0) {
            if (current.left == null) {
                current.left = newNode;
                current.left.carhead = null;
            } else {
                this.insert(newNode, current.left);
                newNode.carhead = null;
            }
        } else if (current.right == null) {
            current.right = newNode;
            current.right.carhead = null;
        } else {
            this.insert(newNode, current.right);
        }
    }

    public Comparable find(Comparable Manufacturer) throws NotFoundException {
        return this.find(Manufacturer, this.root);
    }

    private Comparable find(Comparable Manufacturer, DealershipNode current)
            throws NotFoundException {

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
    
    
     public void insertCar(Car car, String Manufacturer) {

        
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
