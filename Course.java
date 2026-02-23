// Course.java
// Name: Matt Ceran
// Date: 02/22/2026
// Purpose: This class represents a course and its grade distribution

import java.util.ArrayList;

public class Course {

    private String courseName;
    private ArrayList<Integer> courseGrades;  // index 0=A, 1=B, 2=C, 3=D, 4=F

    // default constructor
    public Course() {
        courseName = "";
        courseGrades = new ArrayList<Integer>();
    }

    //parameterized constructor
    public Course(String name, ArrayList<Integer> grades){
        courseName = name;
        courseGrades = grades;
    }

    // getters and setters
    public String getCourseName(){
        return courseName;
    }
    public void setCourseName(String name){
        courseName = name;
    }

    public ArrayList<Integer> getCourseGrades() {
        return courseGrades;
    }
    public void setCourseGrades(ArrayList<Integer> grades) {
        courseGrades = grades;
    }


    // returns total number of grades across all letters
    public int getTotalGrades(){
        int total = 0;
        for(int i = 0; i < courseGrades.size(); i++){
            total += courseGrades.get(i);
        }
        return total;
    }

    // calculates the A percentage
    public double getAPercent() {
        int total = getTotalGrades();
        if(total == 0){
            return 0.0;
        }
        return ((double) courseGrades.get(0) / total) * 100.0;
    }

    // toString method for printing
    public String toString(){
        return courseName + " " + courseGrades.toString();
    }
}