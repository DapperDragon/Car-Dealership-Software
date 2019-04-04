/*//@author B00330262 - Gary Hughes*/
public class Car implements Comparable<Car>{
    
    //decalre variables using info from GameListTest class
    private String RegistrationNumber;
    private String ModelName;
    private String colour;
    
    //takes game details from GameListTest
    //adds info to local variables declared above
    public void addDetails(String RegistrationNumber, String ModelName, String colour){
        this.RegistrationNumber = RegistrationNumber;
        this.ModelName = ModelName;
        this.colour = colour;
        
     }

    //uses "compareTo" method with details of games
    public int compareTo(Car car){
        
        //Sort by year (descending), if year is the same 
        //compare names for further sorting (ascending)
        Integer result = this.ModelName.compareTo(car.ModelName);
        if (result == 0){
            result = this.RegistrationNumber.compareToIgnoreCase(car.RegistrationNumber);
            //make result negative to change to descending order
            result = result * -1;
        }
        return result;
    }
    
    //details of all games to string for printing, returned to GameListTest
    public String toString(){
        String gameDetails = new String();
        gameDetails=String.format("%-20s%-20s%-20s", this.RegistrationNumber, this.ModelName, this.colour);
        return gameDetails;
    }
   
    
}
