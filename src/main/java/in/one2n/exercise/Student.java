package in.one2n.exercise;

import java.util.Objects;

public class Student {

    private String firstname;
    private String lastname;
    private String university;
    private Double test1Score;
    private Double test2Score;
    private Double test3Score;
    private Double test4Score;

    // computed fields
    private Double finalScore;
    private Grade grade;

    public Student(String firstname, String lastname, String university) {
        // TODO: add your implementation here
        this.firstname=firstname;
        this.lastname=lastname;
        this.university=university;
    }

    public Student(String firstname, String lastname, String university, Double test1Score, Double test2Score, Double test3Score, Double test4Score) {
        // TODO: add your implementation here
        this.firstname=firstname;
        this.lastname=lastname;
        this.university=university;
        this.test1Score=test1Score;
        this.test2Score=test2Score;
        this.test3Score=test3Score;
        this.test4Score=test4Score;
    }

    public Double getFinalScore() {
        // TODO: add your implementation here
        this.finalScore = (test1Score+test2Score+test3Score+test4Score)/4.0;
        return this.finalScore;
    }

    public Grade getGrade() {
        // TODO: add your implementation here

        if(this.finalScore<35) this.grade=Grade.F;
        else if(this.finalScore>=35 && this.finalScore<50) this.grade=Grade.C;
        else if(this.finalScore>=50 && this.finalScore<70) this.grade=Grade.B;
        else this.grade=Grade.A;
        return this.grade;
    }

    public String getUniversity(){
        return this.university;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Objects.equals(firstname, student.firstname) && Objects.equals(lastname, student.lastname) && Objects.equals(university, student.university);
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, university);
    }
}

