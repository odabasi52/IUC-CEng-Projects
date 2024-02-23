using FactoryPattern;

namespace FactoryPattern{
    interface Transport{
        abstract void doSomething();
    }

    class Truck: Transport{
        public void doSomething(){
            Console.WriteLine("I am a truck");
        }
    }

    class Ship: Transport{
        public void doSomething(){
            Console.WriteLine("I am a ship");
        }
    }

    abstract class Factory{
        public abstract Transport createTransport();
    }

    class ShipFactory: Factory{
        public override Transport createTransport()
        {
            return new Ship();
        }
    }

    class TruckFactory: Factory{
        public override Transport createTransport()
        {
            return new Truck();
        }
    }
}

class Test{
    static void Main(string[] args)
    {
            Factory truckFactory = new TruckFactory();
            Transport truck = truckFactory.createTransport();
            truck.doSomething();

            Factory shipFactory = new ShipFactory();
            Transport ship = shipFactory.createTransport();
            ship.doSomething();
    }
}
