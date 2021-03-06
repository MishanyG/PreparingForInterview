package HomeWork_L1;

// Не знаю, нужно ли было смотреть на то, что выводится в консоль, какая была задумка, но я посчитал что это не ошибка
// то что в легковой машине мы её открыли и поехали,
// а грузовик поехал и остановился
// нужно ли было бы расставить методы по логике (открыть, запустить, поехать, остановить)
// Надеюсь что правильно понял задание)

interface Moveable {
    void move();
}

interface Stopable {
    void stop();
}

// Лучше методы open() и start вынести отдельно
interface Open {
    void open();
}

class Initialisation {
    protected void start() {
        System.out.println("Car starting");
    }
}

//abstract class Car {
//    public Engine engine;     - не найден тип Engine
// ...

enum Engine {
    benzine,
    diesel
}

// Нет конструктора, незачем сущность делать абстрактной
class Car {
    public Engine engine;
    private String color;
    private String name;
    Initialisation init;

    public Car(Engine engine, String color, String name, Initialisation init) {
        this.engine = engine;
        this.color = color;
        this.name = name;
        this.init = init;
    }

    public Car() {
    }

//    protected void start() {
//        System.out.println("Car starting");
//    }

//    abstract void open();

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class LightWeightCar extends Car implements Moveable, Open {

    @Override
    public void open() {
        System.out.println("Car is open");
    }

    @Override
    public void move() {
        System.out.println("Car is moving");
    }
}

// class Lorry extends Car, Moveable, Stopable - java не поддерживает множественное наследование
class Lorry extends Car implements Moveable, Stopable {

    public void move(){
        System.out.println("Car is moving");
    }

    public void stop(){
        System.out.println("Car is stop");
    }
}


