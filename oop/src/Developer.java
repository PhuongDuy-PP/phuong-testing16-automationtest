public class Developer extends Employee{
    String title;
    String hieuSuatCV;

    public Developer() {
        super();
    }

    public Developer(String ID,
                     String name,
                     int basicSalary,
                     String phuCap,
                     String title,
                     String hieuSuatCV){
        super(ID, name, basicSalary, phuCap);
        this.title = title;
        this.hieuSuatCV = hieuSuatCV;
    }

    @Override
    public void showInfor() {
        super.showInfor();
        System.out.println("Title: " + title);
        System.out.println("Hieu suat CV: " + hieuSuatCV);
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
            if (hieuSuatCV.equals("A")) {
                salary = salary + 2000000;
            }
            return salary;
        }
    }
}
