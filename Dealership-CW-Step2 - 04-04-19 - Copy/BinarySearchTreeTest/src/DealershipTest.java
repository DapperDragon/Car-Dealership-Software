
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DealershipTest {
    
        public class CarNode {

        public Car car;
        public CarNode next;
        //public CarNode carhead;
        public CarNode current;
        
       

    }
        
        
            private class ListNode {
        private Comparable object;
        private ListNode next;
         public Car car;
         public CarNode current;
         public String NodeDetails;
    }

    public static void main(String[] args) throws SortedADT.NotUniqueException {
        Dealership dealership = new Dealership();
        String Manufacturer;
        Integer option = null;
        Integer optioncheck;
        String RegistrationNumber;
        String ModelName;
        String colour;
        //SortedADT allCars = new SortedLinkedList() {};
        
       String[] where = new String [100000];
        //allCars = new String[]{};
        Car car;


        do {
            System.out.println("0: quit");
            System.out.println("1: Add manufacturer");
            System.out.println("2: Remove manufacturer");
            System.out.println("3: Find");
            System.out.println("4: Display manufacturers");
            System.out.println("5: Add Car");
            System.out.println("6: Remove Car");
            System.out.println("7: Display tree of manufacturers AND cars");
            System.out.println("8: Display all cars");
            System.out.println("9: Display tree of manufacturers ONLY");



    
            
            Scanner sc=new Scanner(System.in);
            try{
            //option = Input.getInteger("option: ");
            System.out.println("option: ");
            option = sc.nextInt();
            }
            catch(InputMismatchException exception)
            {
                System.out.println("Not a Number");
                option = null;
            }
            
            if(option != null){
            switch (option) {
                
                //quit program
                case 0:
                    try{
                    System.out.println("1: Quit");
                    System.out.println("2: Cancel");
                    
                    System.out.println("Are you sure you want to quit?");
                    option = sc.nextInt();
                    }
                    catch(InputMismatchException exception)
                    {
                        System.out.println("Not a Number");
                        option = 2;
                    }
                    
                    if(option == 1){
                        System.out.println("Goodbye");
                        System.exit(0);
                    } else {
            }       System.out.println("Returning to Menu");
                    break;
                    
                    
                
                //add maufacturer
                case 1:
                    Manufacturer=Input.getString("Manufacturer: ");
                    try{
                        dealership.insert(Manufacturer);
                        System.out.println("inserted");
                    }
                    catch(Dealership.NotUniqueException e){
                        System.out.println("insert invalid - Manufacturer not unique");
                    }
                   break;
                   
                   
                
                   
                //remove manufacturer
                case 2:
                    Manufacturer=Input.getString("Manufacturer: ");
                    try{
                        Manufacturer=(String)dealership.remove(Manufacturer);
                        System.out.println(Manufacturer+" removed");
                    }
                    catch(Dealership.NotFoundException e){
                        System.out.println("remove invalid - Manufacturer not found");
                    }
                    break;
                    
                    
                    
                
                //find manufacturer
                case 3:
                    Manufacturer=Input.getString("Manufacturer: ");
                    if(Manufacturer.equals("") || Manufacturer.equals(" ") ){
                        System.out.println("No valid Input - returning to menu");
                        
                        break;
                    }
                    else{
                    try{
                        Manufacturer=(String)dealership.find(Manufacturer);
                        System.out.println(Manufacturer+" found");
                    }
                    catch(Dealership.NotFoundException e){
                        System.out.println("Manufacturer not found");
                    }
                    }
                    break;
                    
                    
                
                    
                //display manufacturers
                case 4:
                    
                    if(Dealership.getInstances().isEmpty()){
                        System.out.println("We currently have no manufaturers on sale");
                        
                        
                    }
                    else{
                        //uglist formatting in existsance but didnae touch it
                    String output = ("All Manufacturers we sell: " + "\n" + (Dealership.getInstances())).toString().replace("[","").replace("]","").replace(",","");
//                    output.toString().replace("[","").replace("]","").replace(",","");
                    System.out.println(output);
                    }
                    break;
 
                    
                    
                //add a car    - find way to turn manufacturer name into string to create a linked list for that manufacter to add the car to
                case 5:
                    Manufacturer=Input.getString("Manufacturer of Car: ");
                    try{
                        Manufacturer=(String)dealership.find(Manufacturer);
                        System.out.println(Manufacturer+" found");
                        
                    RegistrationNumber = Input.getString("Registration Number: ");
                    ModelName = Input.getString("Model Name: ");
                    colour = Input.getString("Vehicle Colour: ");
                    if(
                    RegistrationNumber.equals("") || RegistrationNumber.equals(" ") ||
                    ModelName.equals("") || ModelName.equals(" ") ||
                    colour.equals("") || colour.equals(" ") )
                    {
                        System.out.println("Car details cannot be blank. Returning to Main Menu");
                    }
                    
                    
                    
                    
                    

                   Car currentCar = new Car();
                   currentCar.addDetails(RegistrationNumber, ModelName, colour);
                   dealership.insertCar(currentCar, Manufacturer); //Manufacturer=(String)Dealership.find(Manufacturer);
                   //SortedADT manufacturer name = new SortedLinkedList();
                System.out.println("inserted");
                //allCars.insert(Manufacturer + currentCar);                   
                  StringBuilder allCars = new StringBuilder();
                  allCars.append("Manufacturer:" + Manufacturer + "/n" + "currentCar:" + currentCar);
                  where[0] = allCars.toString();
                        
                        
                   }
                   catch(Dealership.NotFoundException e){
                       System.out.println("Manufacturer not found - returning to menu");
                    }
                    
                    
                    
                    

                    break;
                    
                    
                
                //remove a car    
                case 6:
                        Manufacturer=Input.getString("Manufacturer of Car: ");
                    try{
                        Manufacturer=(String)dealership.find(Manufacturer);
                        System.out.println(Manufacturer+" found");
                                         }
                   catch(Dealership.NotFoundException e){
                       System.out.println("Manufacturer not found - returning to menu");
                    }
                
                    
                    RegistrationNumber = Input.getString("Registration Number: ");
                    ModelName = Input.getString("Model Name: ");
                    colour = Input.getString("Vehicle Colour: ");
                    Car currentCar = new Car();
                    currentCar.addDetails(RegistrationNumber, ModelName, colour);
                    
                    dealership.removeCar(currentCar, Manufacturer);
                    System.out.println("removed");
                    System.out.println(Dealership.getAllCars());
                break;
                    
           
                //display tree of manufacturers    
                case 7:         
            
                    System.out.println(dealership);
                break;
                
                
                case 8:
                    if(Dealership.getAllCars().isEmpty()){
                        System.out.println("We currently have no cars on sale");    
                    }
                    else{
                    System.out.println("All cars we sell: " + "\n" + "Registration" + "\t" + "\t" + "Model" + "\t" + "\t" + "vehicle colour" + "\n" + Dealership.getAllCars());
                    }
                    
                break;
                
                case 9:         
            
                    System.out.println(dealership);
                break;
  

                  
                    
                    
                    
                    
                    
                    
                    
                    
                    
                default:
                    System.out.println("\n" + "invalid option" + "\n");
            }
            }
        } while (option == null || option != 0);
    }
    
}