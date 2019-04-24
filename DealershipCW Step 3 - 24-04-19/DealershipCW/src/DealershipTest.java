//imports
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DealershipTest {
    
    //car nodes for tree
    public class CarNode {
        public Car car;
        public CarNode next;
        public CarNode current;
        }
        
       
    //decalring variables
    public static void main(String[] args) throws SortedADT.NotUniqueException, Dealership.NotFoundException {
        Dealership dealership = new Dealership();
        String Manufacturer;
        Integer option = null;
        String RegistrationNumber;
        String ModelName;
        String colour;
        List specificMake = new ArrayList();
        String[] where = new String [100000];


        //menu options inside a loop
        do {
            System.out.println("0: quit");
            System.out.println("1: Add manufacturer");
            System.out.println("2: Remove manufacturer");
            System.out.println("3: Find Manufacturer");
            System.out.println("4: Add Car");
            System.out.println("5: Remove Car");
            System.out.println("6: Display tree of manufacturers AND cars");
            System.out.println("7: Display all cars - alphabetical by manufacturer");
            System.out.println("8: Display tree of manufacturers ONLY");
            System.out.println("9: Search for cars by manufacturer");
            System.out.println("10: Search for cars by manufacturer AND model");


            //use scanner for option input to catch input mismatches
            Scanner sc=new Scanner(System.in);
            try{
                System.out.println("option: ");
                option = sc.nextInt();
            }
            catch(InputMismatchException exception){
                System.out.println("Not a Number");
                option = null;
            }
            
            
            
            //avoids crashing when nothing is entered at menu
            if(option != null){
                switch (option) {

                    //quit program
                    case 0:
                        try{
                        System.out.println("1: Quit");
                        System.out.println("2: Cancel");

                        //quit confirmation
                        System.out.println("Are you sure you want to quit?");
                        option = sc.nextInt();
                        }
                        catch(InputMismatchException exception)
                        {
                            System.out.println("Not a Number");
                            option = 2;
                        }

                        //quit confirmed
                        if(option == 1){
                            System.out.println("Goodbye");
                            System.exit(0);
                        }
                        //quit cancelled
                        else{
                            System.out.println("Returning to Menu");
                        }
                    break;

                    
                    
                    


                    //add manufacturer
                    //with insert message, non unique manufacturers not allowed
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
                    //checks that manufacturer has been added before removing
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
                    //does not allow blank input
                    case 3:
                        Manufacturer=Input.getString("Manufacturer: ");
                        if(Manufacturer.equals("") || Manufacturer.equals(" ")){
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



                    
                  
                    
                    

                    //adding cars
                    //checks manufacturer exists then asks for details, these details cannot be blank
                    case 4:
                        Manufacturer=Input.getString("Manufacturer of Car: ");
                        try{
                            Manufacturer=(String)dealership.find(Manufacturer);
                            RegistrationNumber = Input.getString("Registration Number: ");
                            ModelName = Input.getString("Model Name: ");
                            colour = Input.getString("Vehicle Colour: ");
                                if(
                                RegistrationNumber.equals("") || RegistrationNumber.equals(" ") ||
                                ModelName.equals("") || ModelName.equals(" ") ||
                                colour.equals("") || colour.equals(" ") )
                                {
                                System.out.println("Car details cannot be blank. Returning to Main Menu");
                                break;
                                }


                            //new car instance created with entered details from above
                            //car is inserted to tree and list of all cars entered
                            Car currentCar = new Car();
                            currentCar.addDetails(RegistrationNumber, ModelName, colour);
                            dealership.insertCar(currentCar, Manufacturer);
                            System.out.println("Car successfully added!");
                            StringBuilder allCars = new StringBuilder();
                            allCars.append("Manufacturer:").append(Manufacturer).append("/n" + "currentCar:").append(currentCar);
                            where[0] = allCars.toString();
                        }
                        catch(Dealership.NotFoundException e){
                           System.out.println("Manufacturer not found - returning to menu");
                        }
                    break;


                    
                    
                    
                    

                    //remove a car
                    //checks manufacturer of car exists before asking for more details 
                    case 5:
                        Manufacturer=Input.getString("Manufacturer of Car: ");
                        try{
                            Manufacturer=(String)dealership.find(Manufacturer);
                        }
                        catch(Dealership.NotFoundException e){
                            System.out.println("Manufacturer not found - returning to menu");
                        }

                        //details entered sa they were when adding cars
                        //similarly if details are blank, exits to menu
                        RegistrationNumber = Input.getString("Registration Number: ");
                        ModelName = Input.getString("Model Name: ");
                        colour = Input.getString("Vehicle Colour: ");
                        if(
                            RegistrationNumber.equals("") || RegistrationNumber.equals(" ") ||
                            ModelName.equals("") || ModelName.equals(" ") ||
                            colour.equals("") || colour.equals(" ") )
                            {
                            System.out.println("Car details cannot be blank. Returning to Main Menu");
                            break;
                        }

                        //new instance of car is created with entered details
                        //if another instances that has been added matches, it is removed
                        
                        Car currentCar = new Car();
                        currentCar.addDetails(RegistrationNumber, ModelName, colour);
                        if(Dealership.getAllCars().contains(Manufacturer + "\t" + "\t" +currentCar.toString()+"\n")){
                            dealership.removeCar(currentCar, Manufacturer);
                            System.out.println("Car successfully removed!");
                        }
                        //if car cannot be found, display message
                        else{
                            System.out.println("Car not found!");   
                        }
                    break;


                    
                    
                                      
                    
                    
                    //display tree of manufacturers AND cars
                    //see the "toString" and "getTree" methods in dealership
                    case 6: 
                        System.out.println("Tree view of all cars on sale:");
                        System.out.println(dealership.toString(1));
                    break;

                    
                                       
                    
                    
                    //display all cars alphabetically
                    //(regardless of manufacturer)
                    //checks if list of all cars is empty
                    case 7:
                        if(Dealership.getAllCars().isEmpty()){
                            System.out.println("We currently have no cars on sale:" + "\n"); 
                        }
                        //displays any cars in the list and the total number of cars added - ".getAllCars().size();"
                        else{
                            Dealership.getAllCars();
                            System.out.println("We currently sell " + Dealership.getAllCars().size() + ": " + "\n" + "Manufacturer" + "\t" + "Registration" + "\t" + "Model" + "\t" + "\t" + "vehicle colour" + "\n" + Dealership.getAllCars().toString().replace("[","").replace("]","").replace(",",""));
                            StringBuilder getallcars = new StringBuilder();
                            getallcars.append(Dealership.getAllCars());
                        }
                    break;

                    
                    
                    
                    //displays a tree with only manufacturers
                    //a modified version of the manufacturers and cars tree code from case 7
                    case 8: 
                        System.out.println("Tree view of all manufacturers:" + "\n");
                        System.out.println(dealership.toString(2));
                    break;

                    
                    
                    
                    
                    //display cars from specific manufacturer
                    //checks entered manufacturer exists
                    case 9:  
                        specificMake.clear();
                        Manufacturer=Input.getString("Manufacturer of Car: ");
                        try{
                            Manufacturer=(String)dealership.find(Manufacturer);
                            
                            //check list of all cars
                            //if an element contains the entered manufacturer add to new list "specific make"
                            List allCars = Dealership.getAllCars();
                            for(int j=0; j< allCars.size(); j++){
                                if(allCars.get(j).toString().contains(Manufacturer))
                                    specificMake.add(allCars.get(j));
                            }
                            
                            //if manufacturer exists but no cars have been entered
                            //display unique message
                            if(specificMake.isEmpty()){
                                System.out.println(specificMake.size() + " cars from " + Manufacturer);    
                            }
                            
                            //else print matching cars ("specific make") and the number of them
                            else{
                            System.out.println(specificMake.size() + " cars from " + Manufacturer + "\n" + "Manufacturer" + "\t" + "Registration" + "\t" + "\t" + "Model" + "\t" + "\t" + "vehicle colour" + "\n" + specificMake.toString().replace("[","").replace("]","").replace(",",""));
                            }
                        }catch(Dealership.NotFoundException e){
                           System.out.println("Manufacturer not found - returning to menu");
                        }
                    break;
                    
                    
                    
                    
                    //display sepecif make from specific manufacturer
                    //clears specific make and model lists to avoid incorrect results
                    //checks manufacturer exists
                    case 10:
                        List specificModel = new ArrayList();
                        specificModel.clear();
                        specificMake.clear();
                        Manufacturer=Input.getString("Manufacturer of Car: ");
                        try{
                            Manufacturer=(String)dealership.find(Manufacturer);
                            
                            //checks list of all cars
                            //if an element contains entered manufacturer, adds to specific make list
                            List allCars = Dealership.getAllCars();
                            for(int j=0; j< allCars.size(); j++){
                                if(allCars.get(j).toString().contains(Manufacturer))
                                    specificMake.add(allCars.get(j));
                            }
                            
                            //if no cars match the entered manufacturer, will not ask for a model and return to menu
                            if(specificMake.isEmpty())
                                System.out.println("No cars from " + Manufacturer + ", returning to main menu");
                            
                            
                            //asks for model 
                            //goes through list of all cars
                            //if an element contains entered model, adds to specific model list
                            else{
                                String Model = Input.getString("Model of Car: ");
                                for(int j=0; j< specificMake.size(); j++){
                                    if(specificMake.get(j).toString().contains(Model))
                                        specificModel.add(specificMake.get(j));
                                }

                                //if no cars match the model, display unique messege
                                if(specificModel.isEmpty()){
                                    System.out.println("No Cars found!");
                                }
                                
                                //display matching cars along with total number of matches
                                else{
                                System.out.println(specificModel.size() + " " + Manufacturer + " " + Model + " for sale" + "\n" + "Manufacturer" + "\t" + "Registration" + "\t" + "\t" + "Model" + "\t" + "\t" + "vehicle colour" + "\n" + specificModel.toString().replace("[","").replace("]","").replace(",",""));
                                }
                            }
                        }catch(Dealership.NotFoundException e){
                           System.out.println("Manufacturer not found - returning to menu");
                        }
                        break;
  
                    //handles any invalid menu options entered by user
                    default:
                        System.out.println("\n" + "invalid option" + "\n");
                }
            }
        //loop until user wants to exit
        } while (option == null || option != 0);
    }    
}