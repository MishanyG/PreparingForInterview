package HomeWork_L1;

import java.util.ArrayList;

interface CreateShape {
    void create();
}

interface DeleteShape {
    void delete();
}

abstract class Shape implements CreateShape, DeleteShape {
    public void run() {
        create();
    }
}

class Circle extends Shape {
    public void create() {
        System.out.println("Create circle");
    }

    public void delete() {
        System.out.println("Delete circle");
    }
}

class Square extends Shape {
    public void create() {
        System.out.println("Create square");
    }

    public void delete() {
        System.out.println("Delete square");
    }
}

class Triangle extends Shape {
    public void create() {
        System.out.println("Create triangle");
    }

    public void delete() {
        System.out.println("Delete triangle");
    }
}

class MainPolymorphism {
    public static void main(String[] args) {
        ArrayList <Shape> shapes = new ArrayList <>();
        shapes.add(new Circle());
        shapes.add(new Square());
        shapes.add(new Triangle());

        System.out.println("Create Shapes:\n");

        for(Shape shape : shapes) {
            shape.run();
        }

        System.out.println("\nDelete Shapes:\n");

        for(Shape shape : shapes) {
            shape.delete();
        }
    }
}