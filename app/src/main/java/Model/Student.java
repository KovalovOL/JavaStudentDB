package Model;

public class Student {
    private int id;
    private String faculty;
    private String name;
    private String surname;
    private int score;

    public Student() {

    }

    public Student(String faculty, String name, String surname, int score) {
        this.faculty = faculty;
        this.name = name;
        this.surname = surname;
        this.score = score;
    }

    public Student(int id, String faculty, String name, String surname, int score) {
        this.id = id;
        this.faculty = faculty;
        this.name = name;
        this.surname = surname;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getScore() {
        return score;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
