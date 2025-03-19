package mthree.com.fullstackschool.controller;

import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import mthree.com.fullstackschool.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    @Autowired
    StudentServiceImpl studentServiceImpl;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        return studentServiceImpl.getAllStudents();

        //YOUR CODE ENDS HERE
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        //YOUR CODE STARTS HERE

        return studentServiceImpl.addNewStudent(student);

        //YOUR CODE ENDS HERE
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        //YOUR CODE STARTS HERE

        Student response = studentServiceImpl.getStudentById(id);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
        }

        return response;

        //YOUR CODE ENDS HERE
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        //YOUR CODE STARTS HERE

        if (id != student.getStudentId()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "ID NOT FOUND.");
        }

        Student response = studentServiceImpl.updateStudentData(id, student);
        if (response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "STUDENT NOT FOUND." + id);
        }

        return response;


        //YOUR CODE ENDS HERE
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        //YOUR CODE STARTS HERE

        studentServiceImpl.deleteStudentById(id);

        //YOUR CODE ENDS HERE
    }

    @DeleteMapping("/{studentId}/{courseId}")
    public void deleteStudentFromCourse(@PathVariable int studentId, @PathVariable int courseId) {
        //YOUR CODE STARTS HERE

        studentServiceImpl.deleteStudentFromCourse(studentId, courseId);

        //YOUR CODE ENDS HERE
    }

    @PostMapping("/{studentId}/{courseId}")
    public void addStudentToCourse(@PathVariable int studentId, @PathVariable int courseId) {
        //YOUR CODE STARTS HERE

        studentServiceImpl.addStudentToCourse(studentId, courseId);

        //YOUR CODE ENDS HERE
    }
}
