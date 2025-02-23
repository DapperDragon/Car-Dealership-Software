
public abstract class SortedLinkedList implements SortedADT {

   
    
    public class ListNode {

        public Comparable object;
        public ListNode next;
        //public ListNode head;

    }

    public ListNode head;
    // set by find to allow remove to remove the found node
    private ListNode current;
    private ListNode previous;

    public String toString() {
        /* algorithm
            set up a string to contain the list details
            if list not empty then
                set current node to head of list
                while nodes remain loop
                    add the node object to the string
                    move to next node
                end loop
            else
                add empty list message to the string
            end if
         */
        String listDetails = new String();
        if (this.head != null) {
            ListNode current = this.head;
            while (current != null) {
                listDetails += current.object + "\n";
                current = current.next;
            }
        } else {
            listDetails += "list is empty";
        }
        return listDetails;
    }

    public void insertCar(Comparable object) throws NotUniqueException {
        /* algorithm		
            create a new node //will create component object reference
            if list empty then
                add to head of list
            else
                set current node to head of list
                while insertion position not yet found loop
                    if current object matches object to be added then
                        // attempt to add duplicate object
                        throw not unique exception
                    end if
                    if insertion point found then
                        link the new node to the current node
                        if current node is the head then
                            // add to the front of the list
                            link the head to the new node
                        else
                            link the previous node to the new node
                        end if
                    else
                        if current node is the last in the list
                            // add at end
                            link the current node to the new node
                        else
                            // move to the next node
                            set the previous node as the current node
                            set the current node to the next node
                        end if
                    end if
                end loop
            end if				
         */
        ListNode newNode = new ListNode();
        newNode.object = object;
        if (this.head == null) {
            this.head = newNode;
        } else {
            ListNode current = this.head;
            // require to explicitly set to null to avoid compilation error
            ListNode previous = null;
            Boolean insertionPositionFound = false;
            while (!insertionPositionFound) {
                if (object.compareTo(current.object) == 0) {
                    throw new NotUniqueException();
                }
                if (object.compareTo(current.object) < 0) {
                    insertionPositionFound = true;
                    newNode.next = current;
                    if (current == this.head) {
                        this.head = newNode;
                    } else {
                        previous.next = newNode;
                    }
                } else if (current.next == null) {
                    insertionPositionFound = true;
                    current.next = newNode;
                } else {
                    previous = current;
                    current = current.next;
                }
            }
        }
    }

    public Comparable find(Comparable object) throws NotFoundException {
        /* algorithm
            if list empty then
                throw not found exception
            end if
            set current node to head of list
            while object not found loop
                if object matches current node object then
                    object found
                else
                    if object is less than current object then
                        // object is not in the list
                        throw not found exception
                    else
                        if no more objects to compare with then
                            // object is not in the list
                            throw not found exception
                        else
                            // move to the next node
                            set the previous node to the current node
                            set the current node to the next node
                        end if
                    end if
                end if
            end loop
         */
        if (this.head == null) {
            throw new NotFoundException();
        }
        Comparable foundObject = null;
        this.current = this.head;
        while (foundObject == null) {
            if (object.compareTo(this.current.object) == 0) {
                foundObject = this.current.object;
            } else if (object.compareTo(this.current.object) < 0) {
                throw new NotFoundException();
            } else if (this.current.next == null) {
                throw new NotFoundException();
            } else {
                this.previous = this.current;
                this.current = this.current.next;
            }
        }
        return foundObject;
    }

    public Comparable remove(Comparable object) throws NotFoundException {
        /* algorithm
            find the object //sets up current and previous
            if current node is the head then
                link the head to the next node
            else
                link the previous node to the next node
            end if
            remove object and link from current node
         */
        // current will refer to the node to be removed
        // previous will refer to the node immediately before the one to be removed
        Comparable removedObject = this.find(object);
        if (this.current == this.head) {
            this.head = this.current.next;
        } else {
            this.previous.next = this.current.next;
        }
        this.current.object = null;
        this.current.next = null;
        return removedObject;
    }

}
