import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
//      LẬP TRÌNH CƠ BẢN:
//        1. khai báo biến
//        2. toán tu: +, -, *, /, %
//        3. phep toan so sanh: >, >=, <, <=, ==, !=
//        4. phep toan noi cac dieu kien: &&, ||
//        5. condition, loop
//        6. function
//        7. lap trinh huong doi tuong (OOP)
//        + ke thua
//        + dong goi
//        + truu tuong
//        tool AI: chatGPT, Gemini, Claude,...
//        nhung web hoc co ban java
//        1. Xuat gia tri ra ngoai terminal
//        luu code: Ctrl + S (Win), Cmd + S(Mac)
        System.out.println("Hello testing 16");

//        2. khai bao bien (variable)
//        so nguyen: int, long
//        gan so 10 vao bien number1
        int number1 = 10;
        String str1 = "Testing 16";
        System.out.println(number1);
        System.out.println(str1);

//        truong hop trong println co ca chu + so
//        convert du lieu so => chu. VD: 10 => "10"
        System.out.println("Hello " + number1);

//        3. các phép toán
//        + - * /(chia lấy nguyên) %(chia lấy dư)
        int num1 = 10;
        int num2 = 20;

//        kết quả tong cua num1 va num2 se gan vao bien sum
        int sum = num1 + num2;

        System.out.println("Ket qua cua num1 va num2 la " + sum);
//        Quy tac dat ten bien, ten ham
//        dat ten bien theo quy tac camel case
//        tongHaiSo, dangNhapThanhCong, taoTaiKhoan,...
//        dat ten bien dung ngu canh
//        cac tieng cua ten bien khong duoc co dau space
//        khong duoc co ky tu dac biet (%~!$^&*())
//        ky tu dau tien phai la chu
//        VD dung: sum1, sum2. VD sai: 1sum, 2sum
//        khong duoc dat ten bien trung voi cac keyword trong java
//        keyword: int, float, def, class,...

//        -
        int num3 = 20;
        int num4 = 30;
        int minus = num3 - num4;
        System.out.println("Hieu num3 va num4 la: " + minus);

//        num3++, ++num3 => num3 = num3 + 1
//        num3--, --num3 => num3 = num3 - 1
//        THUONG SE GAP TRONG VONG LAP
//        phep nhan *
        int num5 = 39;
        int num6 = 79;
        int mutiply = num5 * num6;
        System.out.println("Tich num5 va num6 la: " + mutiply);

//        phep chia
//        chia lay nguyen /
        int num7 = 10;
        int num8 = 4;
        int chiaLayNguyen = num7 / num8;
        System.out.println("Chia lay nguyen cua 10 va 4 la: " + chiaLayNguyen);

        int chiaLayDu = num7 % num8;
        System.out.println("Chia lay du cua 10 va 4 la: " + chiaLayDu);

//        các phép toán so sánh: > >= < <= == (kiem tra bang) != (kiem tra khác nhau) ! (phủ định)
//        ket qua tra ve boolean - hoac la true hoac la false
        boolean kiemTra1 = num7 > num8;
        boolean kiemTra2 = num7 < num8;
        System.out.println("Ket qua cua num7 va num8 la " + kiemTra1);
        System.out.println(kiemTra2);
//        casc phep toan nay se di chung voi cu phap dieu kien (condition)
//        va vong lap

//        phep toan noi cac dieu kien: &&, ||
        boolean kiemTra3 = (num1 > num2) && (num2 > num3) && (num3 > num4);
//        true && true => true
//        tat ca truong hop con lai => false

        boolean kiemTra4 = (num1 > num2) || (num2 > num3) || (num3 > num4);
//        CHI CAN 1 trong cac dieu kien laf true => true
//        VD: true || false || false => true

//        tu duy dev
//        mo hinh 3 khoi: input + process + output
//        B1: hay viet test case ve tinh nang quen mat khau
//        input: nhap email hoac SDT

//        output:
//        test case 1: change pass thanh cong + login thanh cong voi pass moi
//        test case 2: change pass thanh cong + login voi pass cu. Expected: fail

//        Bai 1: tinh tong 3 so nguyen nhap tu ban phim
//        input:
//        Scanner: doi tuong ho tro nhap gia tri tu ban phim
//        B1: tao doi tuong scanner
        Scanner scan = new Scanner(System.in);
        System.out.println("Moi ban nhap so1: ");
        int so1 = Integer.parseInt(scan.nextLine());

        System.out.println("Moi ban nhap so2: ");
        int so2 = Integer.parseInt(scan.nextLine());

        System.out.println("Moi ban nhap so3: ");
        int so3 = Integer.parseInt(scan.nextLine());

//        output:
        int tong = 0;

//        process
        tong = so1 + so2 + so3;
        System.out.println("Tong so1, so2 va so3 la " + tong);

//        B2: nhập tên của bạn. Hãy xuất ra nội dung là
//        Xin chào <tên>
//        input: biến String luu ten nhap tu ban phim
        System.out.println("Moi ban nhap ten: ");
        String name = scan.nextLine();

        System.out.println("Xin chao " + name);


//        tong ket lai:
//        khai bao bien
//        nhap, xuat
//        phep toan + - * / %
//        phep so sanh > >= < <= == !=
//        phep noi nhieu dieu kien && ||
//        mo hinh 3 khoi: input + process + output

    }
}