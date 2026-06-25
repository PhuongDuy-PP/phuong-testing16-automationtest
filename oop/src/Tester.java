public class Tester extends Employee{
    String title;
    String soLuongBug;

    public Tester() {
        super();
    }

    public Tester(String ID,
                  String name,
                  int basicSalary,
                  String phucap,
                  String title,
                  String soLuongBug) {
        super(ID, name, basicSalary, phucap);
        this.title = title;
        this.soLuongBug = soLuongBug;
    }

    @Override
    public void showInfor() {
        super.showInfor();
        System.out.println("Title: " + title);
        System.out.println("So luong bug: " + soLuongBug);
    }

    @Override
    public int calculateSalary() {
        int salary = 0;
        if (title.equals("fresher")) {
            salary = salary + getBasicSalary() + 6000000;
            return salary;
        } else if (title.equals("junior")) {
            salary = salary + getBasicSalary() + 11000000;
            return salary;
        } else {
            salary = salary + getBasicSalary() + 11000000;
            if (Integer.parseInt(soLuongBug) > 3) {
                salary = salary + Integer.parseInt(soLuongBug) * 1000000;
            }
            return salary;
        }
    }
}
