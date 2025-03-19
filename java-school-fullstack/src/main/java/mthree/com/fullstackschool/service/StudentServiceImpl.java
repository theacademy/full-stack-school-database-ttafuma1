package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE
    @Autowired
    private StudentDao studentDao;
    private CourseDao courseDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
    }

    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        return studentDao.getAllStudents();

        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE

        try {
            return studentDao.findStudentById(id);
        } catch (DataAccessException ex) {
            Student student = new Student();
            student.setStudentFirstName("Student Not Found");
            student.setStudentLastName("Student Not Found");
            return student;
        }

        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE

        if(student == null){
            return null;
        }
        else if (student.getStudentFirstName() == null || student.getStudentFirstName().trim().isEmpty()
                || student.getStudentLastName() == null || student.getStudentLastName().trim().isEmpty()) {
            student.setStudentFirstName("First Name blank, student NOT added");
            student.setStudentLastName("Last Name blank, student NOT added");
            return student;
        }
        return studentDao.createNewStudent(student);
        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE

        if(student == null){
            return null;
        } else if (id != student.getStudentId()) {
            student.setStudentFirstName("IDs do not match, student not updated");
            student.setStudentLastName("IDs do not match, student not updated");
        } else {
            studentDao.updateStudent(student);
        }
        return student;

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE

        studentDao.deleteStudent(id);

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        Student student = getStudentById(studentId);
        Course course = courseDao.findCourseById(courseId);
        if(student != null && course != null) {
            if(student.getStudentFirstName().equals("Student Not Found"))
                System.out.println("Student not found");
            else if(course.getCourseName().equals("Course Not Found")){
                System.out.println("Course not found");
            }
            else {
                System.out.println("Student: " + studentId + " deleted from course: "  + courseId);
                studentDao.deleteStudentFromCourse(studentId, courseId);
            }
        }

        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        try {
            Student student = getStudentById(studentId);
            Course course =  courseDao.findCourseById(courseId);
            if (student != null && course != null) {
                if (student.getStudentFirstName().equals("Student Not Found")) {
                    System.out.println("Student not found");
                } else if (course.getCourseName().equals("Course Not Found")) {
                    System.out.println("Course not found");
                } else {
                    System.out.println("Student: " + studentId + " added to course: " + courseId);
                    studentDao.addStudentToCourse(studentId, courseId);
                }
            }
        } catch (Exception e) {
            System.out.println("Student: " + studentId + " already enrolled in course: " + courseId);
        }

        //YOUR CODE ENDS HERE
    }
}
