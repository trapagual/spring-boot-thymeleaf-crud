package es.trapasoft.student.repository;

import es.trapasoft.student.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IStudentRepository extends JpaRepository<Student, Long> {

    @Query("FROM Student s WHERE s.firstName LIKE :name OR s.lastName LIKE :name")
    public List<Student> findByNameContaining(@Param("name") String name);
    
}
