package com.github.curriculeon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private List<Double> examScores;

    public Student(String firstName, String lastName, Double[] examScores) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.examScores = new LinkedList<>(Arrays.asList(examScores));
    }

    public Student() {
        this(null, null, new Double[]{});
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double[] getExamScores() {
        return examScores.toArray(new Double[0]);
    }

    public void setExamScores(Double[] examScores) {
        this.examScores = new LinkedList<>(Arrays.asList(examScores));
    }

    public void addExamScore(double examScore) {
        examScores.add(examScore);
    }

    public void setExamScore(int examNum, double updateScore) {
        examScores.add(examNum, updateScore);
    }

    public Double getAverageExamScore() {
        return examScores
                .stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .getAsDouble();
    }

    @Override
    public String toString() {
        return String.format("%s %s -> Exam Scores: %s",
                this.firstName,
                this.lastName,
                this.examScores.toString());
    }

    @Override
    public int compareTo(Student otherStudent) {
        int compareByGrade = otherStudent.getAverageExamScore().compareTo(this.getAverageExamScore());
        if (compareByGrade == 0) {
            return this.lastName.compareTo(otherStudent.getLastName());
        }
        return compareByGrade;
    }
}
