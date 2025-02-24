package model;

public class User {
    private String name;
    private String email;
    private Integer age;
    private Float heightM;

    public User(String name, String email, Integer age, Float heightM) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.heightM = heightM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getHeightM() {
        return heightM;
    }

    public void setHeightM(Float heightM) {
        this.heightM = heightM;
    }
}
