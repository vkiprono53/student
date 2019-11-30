package com.kiprono.course.controllers;

import com.kiprono.course.models.Student;
import com.kiprono.course.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /*
     * **************FindAll Student Records****************
     * */
    @GetMapping("/students")
    public ResponseEntity getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    /*
     * **************Delete Records by id********************
     * */
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        Optional<Student> student1 = studentService.findById(id);
        if (!student1.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        studentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /*
     * ***********************Save the Records****************
     * */
    @PostMapping("/students")
    public ResponseEntity save(@Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentService.save(student));
    }

    /*
     * **************Find By Id*******************
     * */

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getOne(@PathVariable Long id) {

        Optional<Student> student = studentService.findById(id);

        if (!student.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student.get());
    }

    /*
     * @updateStudent()---************Update the students records
     *
     * */


    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable Long id) {

        Optional<Student> student1 = studentService.findById(id);

        if (!student1.isPresent())
            return ResponseEntity.notFound().build();

        student.setId(id);

        studentService.save(student);

        return ResponseEntity.ok(studentService.save(student));
    }


}
