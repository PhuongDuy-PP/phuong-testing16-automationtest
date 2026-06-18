import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
//        1. condition statement
//        bài 1: kiểm tra số dương hay số âm
//        input: number từ bàn phím
        Scanner scan = new Scanner(System.in);
        System.out.println("Mời bạn nhập số: ");
        int number = scan.nextInt();

//        output: số âm hoặc số dương
        if (number >= 0) {
            System.out.println("Số bạn nhập là số dương");
        } else {
            System.out.println("Số bạn nhập là số âm");
        }

//        Bai 2: kiểm tra so chan, so le
//        number % 2 == 1 -> kiem tra le
        if(number %2 == 1) {
            System.out.println("So ban nhap la so le");
        } else {
            System.out.println("So ban nhap la so chan");
        }

//        Bai 3: xep loai diem hoc sinh
//        9 -> 10 => xuat sac
//        8 ->  < 9 => gioi
//        6.5 => < 8 => kha
//        5 => < 6.5 => trung binh
//        duoi 5 => yeu
//        6, 7, 15

//        < 0 , > 10 => loi
//        khong phai so => loi
//        9 -> xuat sac
//        8 -> gioi
//        6.5 -> kha
//        5 -> trung binh
//        4.5 -> yeu

        System.out.println("Moi ban nhap diem:");
        float diem = scan.nextFloat();
        if ((diem < 0) || (diem > 10)) {
            System.out.println("Diem bi sai, vui long nhap lai");
        }

//        diem >=9 va diem <=10
//        9 <= diem && diem <=10
        if ((9 <= diem) && (diem <= 10)) {
            System.out.println("Xuat sac");
        } else if ((8 <= diem) && (diem < 9)) {
            System.out.println("Gioi");
        } else if ((6.5 <= diem) && (diem <= 8)) {
            System.out.println("Kha");
        } else if ((5 <= diem) && (diem < 6.5)) {
            System.out.println("Trung binh");
        } else {
            System.out.println("Yeu");
        }

//        bai 3: kiem tra dang nhap
//        dung username va password => dang nhap thanh cong
//        nguoc lai => dang nhap that bai
//        username: admin
//        password: 123456
//        status: active
//        case 1: empty username hoac password hoac empty ca hai
//        case 2: nhap sai username
//        case 3: nhap sai password
//        case 4: status deactivate
//        case 5: dung username, password, status active

        String username = "admin";
        String password = "123456";
        boolean isActive = true;
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Loi: tai khoan hoac mat khau khong duoc de trong");
        } else if (!isActive) {
//            !isActive: status khong phai la active
            System.out.println("Loi: tai khoan da bi khoa");
        } else if (username.equals("admin") == false || password.equals("123456") == false) {
            System.out.println("Loi: tai khoan hoac mat khau khong dung");
        } else {
            System.out.println("Dang nhap thanh cong");
        }

//        bai 4: tinh tien ve theo do tuoi va loai ve
//        input
//        tuoi, loai ve
//        dieu kien:
//        tre em < 12 tuoi: mien phi
//        nguoi cao tuoi (>=60 tuoi): 50.000 (cho tat ca loai ve)
//        nguoi lon con lai:
//          VIP: 200.000
//          thuong: 150.000

//        case 1: 11, VIP => free
//        case 2: 65, VIP => 50.000
//        case 3: 20, VIP => 200.000
//        case 4: 20, thuong => 150.000
        int tuoi = 20;
        String loaiVe = "thuong";
        double tienVe = 0;
        if (tuoi < 12) {
            tienVe = 0;
        } else if (tuoi >= 60) {
            tienVe = 50000;
        } else {
            if (loaiVe.equalsIgnoreCase("VIP")) {
                tienVe = 200000;
            } else {
                tienVe = 150000;
            }
        }

        if (tienVe == 0) {
            System.out.println("Ban duoc mien phi ve");
        } else {
            System.out.println("Tien ve cua ban la: " + tienVe);
        }

//        -------------------------------------
//        bai 5: tim so lon nhat trong 3 so


//        2. vong lap
//        bai 1: in cac so tu 1 den 50
        int n = 50;
        for(int i = 1; i <= n; i++) {
            System.out.println(i);
        }

//        bai 2: in cac so chan tu 1 den 50
        for(int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }

        for (int i = 2; i <= n; i+=2){
            System.out.println(i);
        }

//        Bài 3: tính tổng các số từ 1 -> n
        int tong = 0;
        for (int i = 1; i <= n; i++) {
            tong = tong + i;
        }
        System.out.println("Tong tu 1 -> n la: " + tong);

//        Bài 4: in bang cuu chuong
        int number1 = 7;
//        7 x 1 = 7
//        7 x 2 = 14
//        7 x 3 = 21
//        ....
//        7 x 10 = 70
        for (int i = 1; i <= 10; i++) {
            System.out.println(number1 + " x " + i + " = " + (number1 * i));
        }
    }
}