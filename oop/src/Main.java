//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
//        tao doi tuong NguoiDung
        NguoiDung user1 = new NguoiDung();
        user1.maNguoiDung = "001";
        user1.hoTen = "Nguyen Van A";
        user1.ngaySinh = "1990/01/01";
        user1.showInfor();

        NguoiDung user2 = new NguoiDung();
        user2.maNguoiDung = "002";
        user2.hoTen = "Nguyen Van B";
        user2.showInfor();

//        LƯU Ý: class cha không nên dùng để tạo đối tượng
//        Dog() => hàm khởi tạo đối tượng
//        2 loại hàm khởi tạo
//        - hàm khởi tạo không có tham số. VD: Dog()
//        lấy giá trị mặc định
//        int => 0
//        float => 0.0
//        String => ""
//        - hàm khởi tạo có tham số
//        mac dinh: nếu ko khởi tạo hàm khởi tạo
//        java sẽ auto tạo hàm khởi tao ko tham so
        Dog dog1 = new Dog();
        dog1.name = "Chihuahua";
        dog1.eat();
        dog1.bark();

        Dog dog2 = new Dog("Rex", "begie");
        dog2.bark();

//        VD2: quan ly nhan vien
//        Developer, Tester, ProjectManager
//        thong tin chung: employeeID, name, basic salary, level
//        dac trung rieng cua cac title
//        Dac trung rieng:
//        Developer: salary:
//        fresher: 6tr
//        junior: 11tr + phu cap
//        middle: 11tr + phu cap + hieu suat cong viec

//        Tester
//        fresher: 6tr
//        junior: 10tr + phu cap
//        middle: 10tr + phu cap + KPI

//        PM
//        15tr + phu cap + thuong du an

        Developer dev1 = new Developer("nv01", "Nguyen Van A", 11000000, "Gui xe", "Junior", "A");
        dev1.showInfor();
        System.out.println("Salary: " + dev1.calculateSalary());

        Tester tester1 = new Tester("nv01", "Yen Nhi", 10000000, "Gui xe", "Junior", "5");
        tester1.showInfor();
        System.out.println("Salary: " + tester1.calculateSalary());

        ProjectManager prj1 = new ProjectManager("nv03", "Tran Van C", 15000000, "Quan ly team", 3, "Yes");
        prj1.showInfor();

//        tinh chat 2: tinh dong goi
//        public, private, protected
    }
}