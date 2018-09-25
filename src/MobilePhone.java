/*
author: Medha Kant
*/
public class MobilePhone {

    private int number; //identifier for the MobilePhone
    private Boolean status;
    public ExchangeList.Exchange exchange;

//the constructor for the class 
    public  MobilePhone(int number){
        this.number =number;
    }

//returns the identifier for the mobile phone
    public int number(){
        return this.number;
    }

//returns the status of the MobilePhone , returns a true if it's on and returns false otherwise
    public Boolean status(){
        return this.status;
    }

//method to turn on the mobile phone    
    public void switchOn(){
        this.status=true;
    }

//method to turn off the mobile phone
    public void switchOff(){
        this.status=false;
    }

//stores the location of the base stastion to which the mobile phone is connected
    public ExchangeList.Exchange location(){
        if (status==true)
            return this.exchange;
        else
            System.out.println("Unreachable");
            return null;

    }

    // public static void main(String[] args){
    //     MobilePhone m = new MobilePhone(8);
    //     m.switchOff();
    //     m.switchOn();
    //     System.out.println(m.number());
    // }
}
