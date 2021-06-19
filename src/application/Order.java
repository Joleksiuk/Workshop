package application;

public class Order {
    int id;
    String name;
    String lastname;


    Order(int id){

        this.id=id;
        this.name = "name";
        this.lastname = "lastname";

    }
    Order(){
        this.id=-1;
    }

    String getClientName(){
        return this.name+" "+this.lastname;
    }
}
