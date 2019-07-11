package App.Model;

public class CarDetails {
    private int id;
    private String mark;
    private String brand;
    private int vin;
    private int age;

    public CarDetails(int Id, String Mark, String Brand, int Vin, int Age) {
        this.id = Id;
        this.mark = Mark;
        this.brand = Brand;
        this.vin = Vin;
        this.age = Age;
    }

    public int GetId() {
        return id;
    }

    public String GetMark() {
        return mark;
    }

    public String GetBrand() {
        return brand;
    }

    public int GetVin() {
        return vin;
    }

    public int GetAge() {
        return age;
    }


}
