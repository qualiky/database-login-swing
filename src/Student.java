public class Student {

    //student details
    private int uniqueStudentId;
    private String name;
    private String address;
    private int gender;
    private String stream;
    public final int GENDER_FEMALE = 1;
    public final int GENDER_MALE = 2;
    public final int GENDER_OTHER = 3;

    //subject details
    private double subDS;
    private double subJava;
    private double subEnglish;
    private double subMaths;
    private double subMacro;

    public Student(){

    }


    public Student(String name, String address, int gender, String stream){
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.stream = stream;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public double getSubDS() {
        return subDS;
    }

    public void setSubDS(double subDS) {
        this.subDS = subDS;
    }

    public double getSubJava() {
        return subJava;
    }

    public void setSubJava(double subJava) {
        this.subJava = subJava;
    }

    public double getSubEnglish() {
        return subEnglish;
    }

    public void setSubEnglish(double subEnglish) {
        this.subEnglish = subEnglish;
    }

    public double getSubMaths() {
        return subMaths;
    }

    public void setSubMaths(double subMaths) {
        this.subMaths = subMaths;
    }

    public double getSubMacro() {
        return subMacro;
    }

    public void setSubMacro(double subMacro) {
        this.subMacro = subMacro;
    }
}
