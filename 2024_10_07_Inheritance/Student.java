public class Student extends Person {

    private int admissionsYear;
    private int gradYear;
    private String major;
    private String netID;

    public Student(String firstName, String lastName, String birthDate, int admissionsYear, int gradYear, String major, String netID) {

        super(firstName, lastName, birthDate);
        
        this.admissionsYear = admissionsYear;
        this.gradYear = gradYear;
        this.major = major;
        this.netID = netID;
    }

    public void setAdmissionsYear(int admissionsYear) {
        this.admissionsYear = admissionsYear;
    }

    public void setGradYear(int gradYear) {
        this.gradYear = gradYear;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public int getAdmissionsYear() {
        return admissionsYear;
    }

    public int getGradYear() {
        return gradYear;
    }

    public String getMajor() {
        return major;
    }

    public String getNetID() {
        return netID;
    }

}