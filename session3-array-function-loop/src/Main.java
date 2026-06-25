import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static void inPhanTuMang(int[] arrNum) {
        for (int i = 0; i < arrNum.length; i++) {
            System.out.println(arrNum[i]);
        }
    }

    static int tongCacPtuMang(int[] arrNum) {
        int sum = 0;
        for (int i = 0; i < arrNum.length; i++) {
            sum += arrNum[i]; // sum = sum + arrNum1[i];
        }
        return sum;
    }

//    hàm tìm max, min trong mảng
//    input: int[] arrNum
//    output: [max, min]
    static int[] maxMinArray(int[] arrNum1) {
        int max = arrNum1[0];
        int min = arrNum1[0];
        int[] tupleMaxMin = new int[2];
        for (int i = 1; i < arrNum1.length; i++) {
            if (arrNum1[i] > max) {
                max = arrNum1[i];
            }
            if(arrNum1[i] < min) {
                min = arrNum1[i];
            }
        }
        tupleMaxMin[0] = max;
        tupleMaxMin[1] = min;
        return tupleMaxMin;
    }

    static int findSecondMax(int[] arrNum1) {
        int maximum = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int i = 0; i < arrNum1.length; i++) {
            if (arrNum1[i] > maximum) {
                secondMax = maximum;
                maximum = arrNum1[i];
            } else if (arrNum1[i] > secondMax && arrNum1[i] < maximum) {
                secondMax = arrNum1[i];
            }
        }
        return secondMax;
    }

    static String removeNguyenAm(String str) {
        String result = "";
//        String la tap hop cac ky tu (character), mang cac ky tu (char)
//        String <=> char[]
        for(int i = 0; i < str.length(); i++) {
//            chuyen cac ky tu ve viet thuong
            char c = str.charAt(i); // lay ky tu thu i trong mang ky tu
            char lowerC = Character.toLowerCase(c);

//            if(lowerC != 'u' && lowerC != 'e' && lowerC != 'o' && lowerC != 'a' && lowerC != 'i') {
//                result += c;
//            }
//            cach 2:
//            if ("ueoai".contains(String.valueOf(lowerC)) == false) {
//                result += c;
//            }

//            cach 3: indexOf cua String
//            khong tim thay => -1
//            tim thay => index
            if ("ueoai".indexOf(Character.toString(lowerC)) == -1) {
                result += c;
            }
        }

        return result;
    }

//    tính tổng các chữ số của 1 số
    static int sumChuSo(int number) {
//        1234 => 1 +2 +3 +4
//        b1: convert number => string
//        dùng for
//        sum = sum + number[i]
        String strNum = String.valueOf(number);
        int sum1 = 0;
        for (int i = 0; i < strNum.length(); i++) {
            String character = Character.toString(strNum.charAt(i));
            sum1 += Integer.parseInt(character);
        }
        return sum1;

//        int sum = 0;
//        while(number > 0) {
//            sum += number % 10;
//            number = number / 10;
//        }
//
//        return sum;
    }

//   đếm số chữ số của 1 số
//    VD: 123456 => 6
    static int demChuSo(long number) {
        int count = 0;


        return count;
    }

//     đảo ngược số
//    VD: 1234 => 4321

    static void inHinhChuNhat(int dai, int rong) {
        for(int i = 0; i < rong; i++) {
            for (int j = 0; j < dai; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    static void inHinhTamGiacVuong(int doDai) {
        for(int i = 1; i <= doDai; i++){
            for(int j = 1; j <= doDai; j++) {
                if (j <= i) {
                    System.out.print("* ");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
//        mảng
//        mảng số nguyên
//        cách 1: tạo mảng số nguyên và
//        gán giá trị sẵn
        inHinhTamGiacVuong(10);
        inHinhChuNhat(4, 3);
        int number = 1034;
        int tong = sumChuSo(number);
        System.out.println("Tong cac chu so: " + tong);

        int[] arrNum = {1, 2, 3, 4, 5, 6, 7};
//                      0  1  2  3  4  5  6
        inPhanTuMang(arrNum);

//        2. nhap tung phan tu cua mang
//        phan 1: khai bao so luong phan tu cua mang
//        phan 2: nhap tung phan tu cua mang
        int n;
        Scanner scan = new Scanner(System.in);
        System.out.println("Moi ban nhap so luong phan tu cua mang: ");
        n = scan.nextInt();
//        cap phat vung nho gom n o nho lien ke nhau
        int[] arrNum1 = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Phan tu thu " + (i + 1));
            arrNum1[i] = scan.nextInt();
        }
//
//        System.out.println("Cac gia tri trong arrNum1 la: ");
//        for (int i = 0; i < n; i++) {
//            System.out.println(arrNum1[i]);
//        }

//        bai 1: tinh tong cac so co trong mang
        int sum = tongCacPtuMang(arrNum1);
        System.out.println("Tong cac so trong mang la: " + sum);

//        bai 2: tim phan tu min, max co trong mang
        int max = maxMinArray(arrNum1)[0];
        int min = maxMinArray(arrNum1)[1];
        System.out.println("So lon nhat la: " + max);
        System.out.println("So nho nhat la: " + min);

        //    bai 4: tim so lon thu hai trong mang
//    VD: [1, 2, 3, 4, 5]
//    output: 4
        int secondMax = findSecondMax(arrNum1);
        System.out.println("So lon thu hai la: " + secondMax);

//        bai 5: xoa nguyen am (u,e,o,a,i)
//        input: Cybersoft
//        output: Cbrsft
        String str = "Cybersoft";
        String result = removeNguyenAm(str);
        System.out.println(result);
    }

//    bai 3: nhap so can tim. In ra vi tri cua so can tim, neu khong tim thay thi in ra -1
//    VD1: [1, 2, 3, 4, 5], target =3
//    output: 2
//    VD2: [1, 2, 3, 4, 5], target = 6
//    output: -1



}