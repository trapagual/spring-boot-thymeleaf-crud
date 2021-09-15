package es.trapasoft.student.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name="students")
public class Student {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="first_name", nullable=false)
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="email")
    private String email;    
    
    
    // relacion muchos a muchos con cursos
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinTable(name="students_courses",
        joinColumns = {
            @JoinColumn(name="student_id", referencedColumnName="id",
            nullable=false, updatable=false)
        },
        inverseJoinColumns = {
            @JoinColumn(name="course_id", referencedColumnName="id",
            nullable=false, updatable=false)
        })
    private Set<Course> courses = new HashSet<>();
    
    
    public Student() {
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    
    @Override
    public String toString() {
        return "Student{" + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + '}';
    }
    
    
}
