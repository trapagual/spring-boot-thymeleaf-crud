package es.trapasoft.student.service;

import es.trapasoft.student.entity.Student;
import java.util.List;

public interface IStudentService {
    
    List<Student> getAllStudents();
    
    List<Student> getStudentByName(String name);
    
    Student saveStudent(Student student);
    
    Student getStudentById(Long id);
    
    Student updateStudent(Student student);
    
    void deleteStudentById(Long id);

}
