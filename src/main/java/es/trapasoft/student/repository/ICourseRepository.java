package es.trapasoft.student.repository;

import es.trapasoft.student.entity.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICourseRepository extends JpaRepository<Course, Long> {
    
        @Query("FROM Course c WHERE c.name like :title")
        List<Course> findByTitleContaining(@Param("title") String title);
        
        @Query("FROM Course c WHERE c.fee <= :fee")
        List<Course> findByFeeLessThan(@Param("fee") double fee);

        @Query("FROM Course c ORDER BY name ASC")
        public List<Course> findAllSortByName();
}
