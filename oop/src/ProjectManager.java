public class ProjectManager extends  Employee{
    int soLuongProject;
    String thuongDuAn;

    public ProjectManager() {
        super();
    }

    public ProjectManager(String ID,
                          String name,
                          int basicSalary,
                          String phuCap,
                          int soLuongProject,
                          String thuongDuAn) {
        super(ID, name, basicSalary, phuCap);
        this.soLuongProject = soLuongProject;
        this.thuongDuAn = thuongDuAn;
    }

    @Override
    public void showInfor() {
        super.showInfor();
        System.out.println("So luong project: " + soLuongProject);
        System.out.println("Thuong du an: " + thuongDuAn);
    }

    @Override
    public int calculateSalary() {
        return 20000000;
    }
}
