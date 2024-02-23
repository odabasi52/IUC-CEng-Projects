using SingletonSpace;

namespace SingletonSpace{
    class Singleton{
        private static Singleton? instance;
        private Singleton(){}

        public static Singleton Instance{
            get{
                if(instance == null){
                    instance = new Singleton();
                }
                return instance;
            }
        }
        public void instancePrint(){
            Console.WriteLine("Singleton Instance");
        }
    }

}

class Test{
    static void Main(string[] args)
    {
        Singleton singleton = Singleton.Instance;
        singleton.instancePrint();
    }
}