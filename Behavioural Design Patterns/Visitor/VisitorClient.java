import java.util.ArrayList;
import java.util.List;

interface ShapeVisitor{
    void visit(Circle circle);
    void visit(Square square);
}

interface Shape{
    void accept(ShapeVisitor visitor);
}

class Circle implements Shape{
    public int radius;

    Circle(int radius){
        this.radius = radius;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}
class Square implements Shape{
    public int side;

    Square(int side){
        this.side = side;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}

class AreaCalculator implements ShapeVisitor {
    double totalArea = 0;

    @Override
    public void visit(Circle circle) {
        totalArea += 3.14 * circle.radius * circle.radius;
    }

    @Override
    public void visit(Square square) {
        totalArea += square.side * square.side;
    }

    public double getTotalArea(){
        return this.totalArea;
    }
}
public class VisitorClient{
    public static void main(String[] args) {
        List<Shape> list = new ArrayList<>();

        list.add(new Circle(10));
        list.add(new Square(10));
        list.add(new Circle(12));
        
        AreaCalculator areaCalculator = new AreaCalculator();
        
        for(Shape shape : list){
            shape.accept(areaCalculator);
        }

        System.out.println("Total Area : " + areaCalculator.getTotalArea());
    }
}