package iscte.ista;

public class Student {
    private String name;
    private int number;

    public Student(){
    }

    public Student(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName(){
        return name;
    }
    public int getNumber(){
        return number;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', number=" + number + "}";
    }
}
