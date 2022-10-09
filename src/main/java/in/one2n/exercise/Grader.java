package in.one2n.exercise;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grader {

    private static final String COMMA_DELIMITER = ",";

    public List<Student> parseCSV(String filepath) {
        // TODO: add your implementation here
        List<Student>students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {

            //parsing the column headers

            String[] headers = br.readLine().trim().split(",");
            String line;

            //parsing the records and
            //storing them into a List

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

        students.forEach((student)->{
            student.getFinalScore();
            student.getGrade();
        });

        return students;
    }
    public Student findOverallTopper(List<Student> gradedStudents) {
        // TODO: add your implementation here
        if(gradedStudents.isEmpty()){
            return null;
        }

        //finding the max finalscore and its corresponding student

        Student topper = gradedStudents.get(0);
        for(Integer index = 1; index<gradedStudents.size(); index++){
            if(gradedStudents.get(index).getFinalScore()>topper.getFinalScore()){
                topper = gradedStudents.get(index);
            }
        }
        return topper;
    }

    public Map<String, Student> findTopperPerUniversity(List<Student> gradedStudents) {
        // TODO: add your implementation here
        if(gradedStudents.isEmpty()){
            return new HashMap<>();
        }
        Map<String, Student> toppers = new HashMap<>();
        for(Student student: gradedStudents){

            // if the university key is not present
            //creating a new entry in the hashmap

            if(toppers.get(student.getUniversity())==null) {
                toppers.put(student.getUniversity(),student);
            }else{

                //else replacing key's value if it is greater
                //than the previous one

                if(toppers.get(student.getUniversity()).getFinalScore()<student.getFinalScore()){
                    toppers.replace(student.getUniversity(),student);
                }
            }
        }
        return toppers;
    }
}
