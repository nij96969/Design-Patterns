abstract class BeverageMaker{
    public void makeBeverage(){
        boilWater();
        brew();
        pourInCup();
        addCondiments();
   }
   
   void boilWater(){
        System.out.println("Boil Water");
   }

   abstract void brew();

   void pourInCup(){
    System.out.println("Pour in Cup");
   }

   abstract void addCondiments();
}

class  GreenTeaMaker extends BeverageMaker{
    @Override
    void brew() {
        System.out.println("Steeping Tea");
    }

    void addCondiments(){
        System.out.println("add lemon");
    }
    
}

class CoffeeMaker extends BeverageMaker{
    void brew(){
        System.out.println("Drip Coffee with filter");
    }

    void addCondiments(){
        System.out.println("Add Milk and Sugar");
    }
    
}

public class TemplateMethodClient{
    public static void main(String[] args) {
        BeverageMaker makeTea = new GreenTeaMaker();
        BeverageMaker makeCoffee = new CoffeeMaker();

        makeTea.makeBeverage();
        System.out.println();
        makeCoffee.makeBeverage();

    }
}