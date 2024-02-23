using AbstractFactory;

namespace AbstractFactory{
    interface IComputer{
        public abstract void run();
    }

    class ModernComputer : IComputer
    {
        public void run()
        {
            Console.WriteLine("Modern Computer is running");
        }
    }

    class OldComputer : IComputer
    {
        public void run()
        {
            Console.WriteLine("Old Computer is running");
        }
    }

    interface IPhone{
        public abstract void call();
    }

    class ModernPhone : IPhone
    {
        public void call()
        {
            Console.WriteLine("Modern Phone is calling");
        }
    }

    class OldPhone : IPhone
    {
        public void call()
        {
            Console.WriteLine("Old Phone is calling");
        }
    }

    interface IFactory{
        public abstract IComputer createComputer();
        public abstract IPhone createPhone();
    }

    class ModernFactory : IFactory
    {
        public IComputer createComputer()
        {
            return new ModernComputer();
        }

        public IPhone createPhone()
        {
            return new ModernPhone();
        }
    }

    class OldFactory: IFactory
    {
        public IComputer createComputer()
        {
            return new OldComputer();
        }

        public IPhone createPhone()
        {
            return new OldPhone();
        }
    }


}

class Test{
    static void Main(string[] args)
    {
        IFactory modernFactory = new ModernFactory();
        IComputer modernComputer = modernFactory.createComputer();
        modernComputer.run();
        IPhone modernPhone = modernFactory.createPhone();
        modernPhone.call();

        IFactory oldFactory = new OldFactory();
        IComputer oldComputer = oldFactory.createComputer();
        oldComputer.run();
        IPhone oldPhone = oldFactory.createPhone();
        oldPhone.call();
    }
}