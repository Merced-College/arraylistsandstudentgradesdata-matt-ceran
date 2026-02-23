// Main.java
// Name: Matt Ceran
// Date: 02/22/2026
// Purpose: Reads course grade data from CSV file and analyzes it

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Course> courses = new ArrayList<Course>();

        // read the csv file
        File file = new File("courseAndGradesData.csv");
        Scanner fileScanner = new Scanner(file);

        // skip first two lines (header stuff)
        fileScanner.nextLine();
        fileScanner.nextLine();

        while(fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(",");

            // skip the total row at the bottom
            if(parts[0].equals("Total")){
                continue;
            }

            String name = parts[0];
            int a = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[2]);
            int c = Integer.parseInt(parts[3]);
            int d = Integer.parseInt(parts[4]);
            int f = Integer.parseInt(parts[5]);

            // check if course already exists (handle duplicates)
            boolean found = false;
            for(int i = 0; i < courses.size(); i++){
                if(courses.get(i).getCourseName().equalsIgnoreCase(name)) {
                    // add grades to existing course
                    ArrayList<Integer> existing = courses.get(i).getCourseGrades();
                    existing.set(0, existing.get(0) + a);
                    existing.set(1, existing.get(1) + b);
                    existing.set(2, existing.get(2) + c);
                    existing.set(3, existing.get(3) + d);
                    existing.set(4, existing.get(4) + f);
                    found = true;
                    break;
                }
            }

            if(!found){
                // create new course object and add to list
                ArrayList<Integer> grades = new ArrayList<Integer>();
                grades.add(a);
                grades.add(b);
                grades.add(c);
                grades.add(d);
                grades.add(f);
                Course newCourse = new Course(name, grades);
                courses.add(newCourse);
            }
        }
        fileScanner.close();


        // print the table header
        System.out.printf("%-15s %6s %6s %6s %6s %6s %7s %8s%n",
             "Course", "A", "B", "C", "D", "F", "Total", "A%");
        System.out.println("---------------------------------------------------------------");

        // print each course
        for(int i = 0; i < courses.size(); i++){
            Course c = courses.get(i);
            ArrayList<Integer> g = c.getCourseGrades();
            System.out.printf("%-15s %6d %6d %6d %6d %6d %7d %7.2f%%%n",
                c.getCourseName(),
                g.get(0), g.get(1), g.get(2), g.get(3), g.get(4),
                c.getTotalGrades(),
                c.getAPercent());
        }

        // find course with highest A percantage
        Course best = courses.get(0);
        for (int i = 1; i < courses.size(); i++) {
            if(courses.get(i).getAPercent() > best.getAPercent()){
                best = courses.get(i);
            }
        }
        System.out.println();
        System.out.println("Course with highest A%: " + best.getCourseName()
            + " with " + String.format("%.2f", best.getAPercent()) + "%");


        // let user search for a course
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter a course name to search for (or 'quit'): ");

        while(input.hasNextLine()){
            String search = input.nextLine();
            if(search.equalsIgnoreCase("quit")){
                break;
            }

            // linear search thru arraylist
            boolean courseFound = false;
            for(int i = 0; i < courses.size(); i++) {
                if(courses.get(i).getCourseName().equalsIgnoreCase(search)){
                    Course c = courses.get(i);
                    ArrayList<Integer> g = c.getCourseGrades();
                    System.out.printf("%-15s A:%d  B:%d  C:%d  D:%d  F:%d  Total:%d  A%%:%.2f%%%n",
                        c.getCourseName(),
                        g.get(0), g.get(1), g.get(2), g.get(3), g.get(4),
                        c.getTotalGrades(), c.getAPercent());
                    courseFound = true;
                    break;
                }
            }
            if(!courseFound){
                System.out.println("Course not found.");
            }

            System.out.println();
            System.out.print("Enter a course name to search for (or 'quit'): ");
        }

        input.close();
        System.out.println("Goodbye!");
    }
}