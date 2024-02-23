using BuilderSpace;

namespace BuilderSpace{
    interface IHomeBuilder{
        abstract public void BuildWalls();
        abstract public void BuildDoors();
        abstract public void BuildWindows();
    }

    class StoneHomeBuilder : IHomeBuilder
    {
        public void BuildDoors()
        {
            Console.WriteLine("Building Stone Doors");
        }

        public void BuildWalls()
        {
            Console.WriteLine("Building Stone Walls");
        }

        public void BuildWindows()
        {
            Console.WriteLine("Building Stone Windows");
        }
    }

    class WoodenHomeBuilder: IHomeBuilder{
        public void BuildDoors()
        {
            Console.WriteLine("Building Wooden Doors");
        }

        public void BuildWalls()
        {
            Console.WriteLine("Building Wooden Walls");
        }

        public void BuildWindows()
        {
            Console.WriteLine("Building Wooden Windows");
        }
    }

    class Director{
        private IHomeBuilder _builder;
        public IHomeBuilder Builder{
            set => _builder = value;
            get => _builder;
        }

        public void ConstructFullHome(){
            _builder.BuildWalls();
            _builder.BuildDoors();
            _builder.BuildWindows();
        }
    }

}

class Test{
    static void Main(string[] args)
    {
        Director director = new Director();

        director.Builder = new StoneHomeBuilder();
        director.ConstructFullHome();
        
        director.Builder = new WoodenHomeBuilder();
        director.ConstructFullHome();
    }
}
