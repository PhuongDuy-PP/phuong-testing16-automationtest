public class Dog extends Animal{
    String type;

    public Dog() {
//        super: kế thừa hàm khởi tạo của class cha
        super();
    }

    public Dog(String name, String type) {
        super(name);
        this.type = type;
    }

    public void bark() {
        System.out.println(name + " is barking");
        System.out.println("Type: " + type);
    }

    @Override
    public void eat() {
        System.out.println(name + " is eating meat");
    }
}
