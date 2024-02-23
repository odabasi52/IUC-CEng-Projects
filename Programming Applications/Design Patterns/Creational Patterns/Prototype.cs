using PrototypeSpace;

namespace PrototypeSpace{
    interface IPrototype<T>{
        T Clone();
    }
    class Home{
        public int homeID;
        public string city;
        public Home(int homeID, string city){
            this.homeID = homeID;
            this.city = city;
        }
    }
    class Person: IPrototype<Person>{
        public string Name;
        public int Age;
        public Home Home;
        public Person(string name, int age, int homeID, string city){
            Name = name;
            Age = age;
            Home = new Home(homeID, city);
        }
        public Person Clone(){
            Person p = new Person(Name, Age, Home.homeID, Home.city);
            return p;
        }

        /*Another way without interface and manual copying*/
        public Person ShallowCopy()
        {
            return (Person) this.MemberwiseClone();
        }

        public Person DeepCopy()
        {
            Person other = (Person) MemberwiseClone();
            other.Home = new Home(Home.homeID, Home.city);
            return other;
        }

        public void Print()
        {
            Console.WriteLine("Name: " + Name + " Age: " + Age + " HomeID: " + Home.homeID + " City: " + Home.city);
        }
    }

}

class Test{
    static void Main(string[] args)
    {
        Person person = new Person("John", 25, 1, "New York");
        Person p1 = person.ShallowCopy();
        Person p2 = person.DeepCopy();
        Person p3 = person.Clone();

        p1.Print();
        p2.Print();
        p3.Print();
    }
}
