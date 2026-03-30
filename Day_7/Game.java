class Name
{
    String name;
    Name(String name)
    {
        this.name=name;
    }
}
class Warrior extends Name
{
    Warrior(String name)
    {
        super(name);
    }
    void attack()
    {
        System.out.println(name+"Warrior attacks with a sword!");
    }
}
class Archer extends Name
{
    Archer(String name)
    {
        super(name);
    }
    void attack()
    {
        System.out.println(name+"Archer attacks with a Arrow!");
    }
}
class Mage extends Name
{
    Mage(String name)
    {
        super(name);
    }
    void attack()
    {
        System.out.println(name+"Mage attacks with magic!");
    }
}
public class Game {
    public static void main(String[] args) {
        Name name;
        name = new Warrior("Aragorn ");
        ((Warrior)name).attack();
        name = new Archer("Legolas ");
        ((Archer)name).attack();
        name = new Mage("Gandalf ");
        ((Mage)name).attack();
    }
}