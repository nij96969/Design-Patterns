//abstract factory
interface ChocolateFactory {
    Chocolate createBar();
    ChocolateNutrients writeNutrients();
}

//abstract product interface for chocolate and ChocolateNutirents
interface  Chocolate{
    void make();
}

interface ChocolateNutrients{
    void specifyNutrients();
}

//Concrete factories for Companies Hershey and KitKat

class HersheyFactory implements ChocolateFactory {
    public Chocolate createBar(){
        return new HersheyBar();
    }

    public ChocolateNutrients writeNutrients(){
        return new HersheyNutrients();
    }
}

class KitKatFactory implements ChocolateFactory{
    public Chocolate createBar(){
        return new KitKatBar(); 
    }

    public ChocolateNutrients writeNutrients(){
        return new KitKatNutrients();
    }
}

//Concrete Product for Hershey
class HersheyBar implements Chocolate{
    public void make(){
        System.out.println("Making Hershey Bar");
    }
}

class KitKatBar implements Chocolate{
    public void make(){
        System.out.println("Makeing KitKat Bar");
    }
}

class HersheyNutrients implements ChocolateNutrients{
    public void specifyNutrients(){
        System.out.println("Calories : 300 cal , Protein : 0 gm , Dietary Fibre : 2 gm");
    }
}

class KitKatNutrients implements ChocolateNutrients{
    public void specifyNutrients() {
        System.out.println("Calories : 500 cal , Protein : 3 gm , Dietary Fibre : 20 gm");    
    }
}
public class ChocolateFactoryClient {

    public static void main(String[] args) {
        ChocolateFactory hershey_factory = new HersheyFactory();
        Chocolate hershey_bar = hershey_factory.createBar();
        ChocolateNutrients hershey_nutrients = hershey_factory.writeNutrients();

        hershey_bar.make();
        hershey_nutrients.specifyNutrients();

        ChocolateFactory kik_kat_factory = new KitKatFactory();
        Chocolate kit_kat_bar = kik_kat_factory.createBar();
        ChocolateNutrients kit_kat_nutrients = kik_kat_factory.writeNutrients();

        kit_kat_bar.make();
        kit_kat_nutrients.specifyNutrients();
    }
}