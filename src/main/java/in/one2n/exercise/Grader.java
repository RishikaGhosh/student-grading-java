package in.one2n.exercise;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Grader {

    private static final String COMMA_DELIMITER = ",";

    public List<Student> parseCSV(String filepath) {
        // TODO: add your implementation here
        List<Student>students = new ArrayList<>();

        // try - with resources block
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {

            String[] headers = br.readLine().trim().split(",");
            String line;

            while ((line = br.readLine()) != null) {
                String[] record = line.split(COMMA_DELIMITER);
                Student student = new Student(record[0],
                        record[1],
                        record[2],
                        Double.valueOf(record[3]),
                        Double.valueOf(record[4]),
                        Double.valueOf(record[5]),
                        Double.valueOf(record[6]));
                students.add(student);
            }
            return students;


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Student> calculateGrade(List<Student> students) {
        // TODO: add your implementation here
        if(students.isEmpty()){
            return Collections.emptyList();
        }

        //populating the finalscore and grade fields of class Student
        //UPDATED with stream

        students.stream().forEach((student)->{
            student.getFinalScore();
            student.getGrade();
        });
        return students;
    }
    public Student findOverallTopper(List<Student> gradedStudents) {
        // TODO: add your implementation here
        //UPDATED with streams
        return gradedStudents.isEmpty()?null:gradedStudents.stream().max(Comparator.comparing(Student::getFinalScore)).get();
    }

    public Map<String, Student> findTopperPerUniversity(List<Student> gradedStudents) {
        // TODO: add your implementation here
        if(gradedStudents.isEmpty()){
            return new HashMap<>();
        }

        Map<String, Student> toppers = new HashMap<>();

        //UPDATED using Streams
        Map<String,List<Student>> students = gradedStudents.stream().collect(Collectors.groupingBy(Student::getUniversity));
        students.forEach((uni,topper)->{
            toppers.put(uni,findOverallTopper(topper));
        });
        return toppers;
    }
}
