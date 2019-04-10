import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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

    public static void main(String[] args) throws SortedADT.NotUniqueException, Dealership.NotFoundException {
        Dealership dealership = new Dealership();
        String Manufacturer;
        Integer option = null;
        String RegistrationNumber;
        String ModelName;
        String colour;
        List specificMake = new ArrayList();
        
        String[] where = new String [100000];
        Car car = null;


        do {
            System.out.println("0: quit");
            System.out.println("1: Add manufacturer");
            System.out.println("2: Remove manufacturer");
            System.out.println("3: Find");
            System.out.println("4: Display manufacturers");
            System.out.println("5: Add Car");
            System.out.println("6: Remove Car");
            System.out.println("7: Display tree of manufacturers AND cars");
            System.out.println("8: Display all cars - alphabetical by manufacturer");
            System.out.println("9: Display tree of manufacturers ONLY");
            System.out.println("10: Display cars of specific manufacturer");
            System.out.println("11: Display cars of specific manufacturer AND model");


            Scanner sc=new Scanner(System.in);
            try{
                //option = Input.getInteger("option: ");
                System.out.println("option: ");
                option = sc.nextInt();
            }
            catch(InputMismatchException exception){
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
                        }
                        else{
                            System.out.println("Returning to Menu");
                        }
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




                    //display manufacturers
                    case 4:

                        if(Dealership.getInstances().isEmpty()){
                            System.out.println("We currently have no manufaturers on sale");


                        }
                        else{
                            //uglist formatting in existsance but didnae touch it
                            String output = ("All Manufacturers we sell: " + "\n" + (Dealership.getInstances())).toString().replace("[","").replace("]","").replace(",","");
                            System.out.println(output);
                        }
                    break;



                    //add a car    - find way to turn manufacturer name into string to create a linked list for that manufacter to add the car to
                    case 5:
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
                                }


                            Car currentCar = new Car();
                            currentCar.addDetails(RegistrationNumber, ModelName, colour);
                            dealership.insertCar(currentCar, Manufacturer);
                            System.out.println("inserted");
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
                            System.out.println("All cars we sell: " + "\n" + "Manufacturer" + "\t" + "Registration" + "\t" + "Model" + "\t" + "\t" + "vehicle colour" + "\n" + Dealership.getAllCars().toString().replace("[","").replace("]","").replace(",",""));
                            StringBuilder getallcars = new StringBuilder();
                            getallcars.append(Dealership.getAllCars());
                        }

                    break;

                    case 9: 
                        
                        System.out.println("All manufacturers on sale: " + "\n" + Dealership.getInstances().toString().replace("[","").replace("]","").replace(",",""));
                        StringBuilder getallmanu = new StringBuilder();
                            getallmanu.append(Dealership.getInstances());
                    break;

                    case 10:
                        
                        specificMake.clear();
                        Manufacturer=Input.getString("Manufacturer of Car: ");
                        try{
                            Manufacturer=(String)dealership.find(Manufacturer);
                            List allCars = Dealership.getAllCars();
                            for(int j=0; j< allCars.size(); j++){
                                if(allCars.get(j).toString().contains(Manufacturer))
                                    specificMake.add(allCars.get(j));
                            }
                            System.out.println(specificMake.size() + " cars from " + Manufacturer + "\n" + "Manufacturer" + "\t" + "Registration" + "\t" + "\t" + "Model" + "\t" + "\t" + "vehicle colour" + "\n" + specificMake.toString().replace("[","").replace("]","").replace(",",""));


                        }catch(Dealership.NotFoundException e){
                           System.out.println("Manufacturer not found - returning to menu");
                        }

                    break;
                    
                    
                    case 11:
                        List specificModel = new ArrayList();
                        specificModel.clear();
                        specificMake.clear();
                        Manufacturer=Input.getString("Manufacturer of Car: ");
                        try{
                            Manufacturer=(String)dealership.find(Manufacturer);
                            
                            List allCars = Dealership.getAllCars();
                            for(int j=0; j< allCars.size(); j++){
                                if(allCars.get(j).toString().contains(Manufacturer))
                                    specificMake.add(allCars.get(j));

                            }
                            
                            if(specificMake.isEmpty())
                                System.out.println("No cars from " + Manufacturer + ", returning to main menu");
                            else{
                            String Model = Input.getString("Model of Car: ");
    

                            for(int j=0; j< specificMake.size(); j++){
                                if(specificMake.get(j).toString().contains(Model))

                                    specificModel.add(specificMake.get(j));

                            }

                            System.out.println(specificModel.size() + " " + Manufacturer + " " + Model + " for sale" + "\n" + "Manufacturer" + "\t" + "Registration" + "\t" + "\t" + "Model" + "\t" + "\t" + "vehicle colour" + "\n" + specificModel.toString().replace("[","").replace("]","").replace(",",""));
                            }

                        }catch(Dealership.NotFoundException e){
                           System.out.println("Manufacturer not found - returning to menu");
                        }

                        break;

                    /*  alphabetical DA  
                    case 12:
                        System.out.println(Dealership.inOrderAlphabet);



                    break;
                      */      

                    default:
                        System.out.println("\n" + "invalid option" + "\n");
                }
            }
        } while (option == null || option != 0);
    }    
}