public class NguoiDung {
//    define cac bien chua thong tin cua nguoi dung
//    thuoc tinh cua doi tuong - attribute, property
    String maNguoiDung;
    String hoTen;
    String diaChi;
    String gioiTinh;
    String ngaySinh;

//    phuong thuc, hanh dong (method) - function
    public void showInfor() {
        System.out.println("Ma Nguoi dung: " + maNguoiDung);
        System.out.println("Ho ten: " + hoTen);
        System.out.println("Dia chi: " + diaChi);
        System.out.println("Gioi tinh: " + gioiTinh);
        System.out.println("Ngay sinh: " + ngaySinh);

    }
}
