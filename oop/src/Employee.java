//tính chất 3: tính trừu tượng
// class cha CHỈ ĐƯỢC DÙNG ĐỂ CLASS CON KẾ THỪA

public abstract class Employee {
//    nhung thuoc tinh class BAT BUOC de private
    private String ID;
    private String name;
    private int basicSalary;
    private String phuCap;

//    getter, setter

    public String getID() {
        return ID.toUpperCase();
    }

    public void setID(String ID) {
//        kiem tra kieu du lieu
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) throws Exception {
        if (basicSalary < 0) {
            throw new Exception("Basic salary must be greater than 0");
        }
        this.basicSalary = basicSalary;
    }

    public String getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(String phuCap) {
        this.phuCap = phuCap;
    }

    public Employee() {
        basicSalary = 6000000;
    }

    public Employee(String ID, String name, int basicSalary, String phuCap) {
        this.ID = ID;
        this.name = name;
        this.basicSalary = basicSalary;
        this.phuCap = phuCap;
    }

    public void showInfor() {
        System.out.println("=================");
        System.out.println("ID: " + ID);
        System.out.println("Name: " + name);
        System.out.println("Basic salary: "+ basicSalary);
        System.out.println("Phu cap: " + phuCap);
    }

//    hàm trừu tượng
//    BẮT BUỘC CLASS CON PHẢI DEFINE LẠI HÀM NÀY
//    HÀM NÀY KHÔNG CÓ THÂN HÀM
    public abstract int calculateSalary();
}
