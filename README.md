# spring-boot-thymeleaf-crud
Aplicación web con Spring Boot haciendo CRUD a una b.d. MySQL, frontend con Thymeleaf. 

Además de lo obvio, que ya sale en multitud de tutoriales, aquí demuestro como:
1)  usar en un controlador otro modelo adicional al propio (cargar una lista de cursos en el controlador de estudiantes)
2)  pasar esa lista a las vistas
3)  trabajar con esa lista en dropdowns en las vistas y que funcione para todas las funciones CRUD.
4)  la relación es muchos-a-muchos, que es la más compleja posible

Este es un pequeño gestor de estudiantes que lo único que hace es el CRUD básico de estudiantes (alta, baja, modificación), y adicionalmente muestra cómo seleccionar los cursos a los que se apunta el estudiante en forma de selector dropdown, en vez de tener que ir metiendo códigos a mano.

## Relaciones

Las relaciones funcionan teniendo en cuenta estos puntos:

1) en el Controlador, no puedes inyectar el Service de Cursos (no lo admite, no sé por qué), pero sí puedes inyectar el CoursesRepository.
    Con él, rellenamos una variable de clase, coursesList, que contendrá todos los cursos disponibles.

    ```java
       private List<Course> coursesList = new ArrayList<>();

        public StudentController(StudentService studentService, ICourseRepository courseRepository) {
            this.studentService = studentService;
            this.courseRepository = courseRepository;
            
            this.coursesList = this.courseRepository.findAllSortByName();
        }
    ```
2) en el Controlador, pasamos esa variable como model.addAttribute a las vistas.
   
   ```java
    @GetMapping("/students/new")
    public String createStudentForm(Model model){
        
        // este objeto Student almacenara los valores 
        Student student = new Student();
       
        model.addAttribute("student", student);
        model.addAttribute("coursesList", coursesList);

        return "create_student";
    }   
   ```
3) en las vistas de escritura (create, update), metemos un select utilizando thymeleaf que es el que nos permitirá seleccionar entre los
    cursos disponibles, los que queremos asignar al estudiante.

```html
    <div class="form-group">
        <label>Courses</label>
        <select th:field="${student.courses}" name="courses" class="form-control" multiple="true">
            <option th:each="course :${coursesList}" th:value="${course.id}" th:text="${course.name}" />
        </select>   
    </div> 
```

4) en las vistas de lectura (por ejemplo la tabla que tenemos en students, que muestra los cursos que tiene asignados el estudiante,
    usamos también el mismo atributo student.courses:

```html
    <tr th:each="student: ${students}">
        <td th:text="${student.firstName}"></td>
        <td th:text="${student.lastName}"></td>
        <td th:text="${student.email}"></td>
        <td> 
            <th:block th:each="course,iter: ${student.courses}">
                <label th:text="${course.name}"/>
                <th:block th:if="${!iter.last}">, </th:block>
            </th:block>
        </td>
    .......
    </tr>
```

Con esto se puede hacer el CRUD incluyendo un selector que permite escoger varios cursos (multiple=true), y que además conserva los cursos que ya tuviera seleccionado el estudiante.


(c) trapasoft.es 2021