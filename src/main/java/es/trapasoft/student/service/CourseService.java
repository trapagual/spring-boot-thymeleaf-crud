package es.trapasoft.student.service;

import es.trapasoft.student.entity.Course;
import es.trapasoft.student.repository.ICourseRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.domain.Sort;

public class CourseService implements ICourseService {

    private ICourseRepository courseRepository;

    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAllSortByName();
    }

    @Override
    public List<Course> getCourseByName(String name) {
        return courseRepository.findByTitleContaining(name);
    }
    
    @Override
    public List<Course> getCourseByFee(double fee) {
        return courseRepository.findByFeeLessThan(fee);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.getById(id);
    }


    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }
    
    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }
    
}
