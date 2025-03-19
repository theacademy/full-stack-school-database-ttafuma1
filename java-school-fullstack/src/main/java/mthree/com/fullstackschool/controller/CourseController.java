package mthree.com.fullstackschool.controller;

import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        return courseService.getAllCourses();

        //YOUR CODE ENDS HERE
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable int id) {
        //YOUR CODE STARTS HERE
        Course response = courseService.getCourseById(id);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
        }

        return response;

        //YOUR CODE ENDS HERE
    }

    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course) {
        //YOUR CODE STARTS HERE

        return courseService.addNewCourse(course);

        //YOUR CODE ENDS HERE
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course course) {
        //YOUR CODE STARTS HERE

        if (id != course.getCourseId()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "ID NOT FOUND.");
        }

        Course response = courseService.updateCourseData(id, course);
        if (response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "COURSE NOT FOUND." + id);
        }

        return response;

        //YOUR CODE ENDS HERE
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id) {
        //YOUR CODE STARTS HERE

        courseService.deleteCourseById(id);

        //YOUR CODE ENDS HERE
    }
}
