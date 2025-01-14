abstract class Electronics{
    protected Workshop workshop1;
    protected Workshop workshop2;

    protected Electronics(Workshop workshop1 , Workshop workshop2){
        this.workshop1 = workshop1;
        this.workshop2 = workshop2;
    }

    abstract public void manufacture();
}

class Remote extends Electronics{
    public Remote(Workshop workshop1 , Workshop workshop2){
        super(workshop1 , workshop2);
    }


    @Override
    public void manufacture(){
        System.out.print("Remote |");
        workshop1.create();
        workshop2.create();
    }  
}

class Mobile extends Electronics{
    public Mobile(Workshop workshop1 , Workshop workshop2){
        super(workshop1 , workshop2);
    }

    @Override
    public void manufacture(){
        System.out.print("Mobile |");
        workshop1.create();
        workshop2.create();
    }  
}

//Implementor for bridge pattern
interface Workshop{
    abstract public void create();
}

//Concrete Implementation 1 
class Produce implements Workshop{
    @Override
    public void create() {
        System.out.println("Produce |");
    }
}

//Concrete Implementation 2
class Assemble implements Workshop{
    @Override
    public void create() {
        System.out.print("Assemble |");
    }
}
public class ElectronicsClient {

    public static void main(String[] args) {
        Electronics rm = new Remote(new Assemble(), new Produce());
        rm.manufacture();
        Electronics mob = new Mobile(new Assemble(), new Produce());
        mob.manufacture();
    }
}