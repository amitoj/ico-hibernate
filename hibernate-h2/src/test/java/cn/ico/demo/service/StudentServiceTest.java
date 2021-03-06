package cn.ico.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import cn.ico.demo.entity.Passport;
import cn.ico.demo.entity.Student;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-context.xml" })
@Service
public class StudentServiceTest {

    @Autowired
    private StudentService service;

    @Test
    @Transactional
    public void testGetStudent() {
        Student student = service.getStudent(101);
        System.out.println(student);
        assertNotNull(student);
        assertEquals(101, student.getId());
    }

    @Test
    @Transactional
    public void testGetStudent_GettingAPassport() {
        Student student = service.getStudent(101);
        assertNotNull(student.getPassport());
        assertEquals(201, student.getPassport().getId());
    }

    @Test
    @Transactional
    public void testUpdateStudent() {
        Student student = service.getStudent(101);
        student.setName("Doe v2");
        Student insertedStudent = service.updateStudent(student);
        Student retrievedStudent = service.getStudent(insertedStudent.getId());
        System.out.println(student);
        assertNotNull(retrievedStudent);
    }

    @Test
    public void testInsertStudent() {
        Passport passport = new Passport(202, "L12344432", "India");
        Student student = createStudent("dummy@dummy.com", "Doe", passport);
        Student insertedStudent = service.insertStudent(student);
        Student retrievedStudent = service.getStudent(insertedStudent.getId());
        assertNotNull(retrievedStudent);
    }

    @Test
    public void testInsertStudent_withoutPassport() {
        Student student = createStudent("dummy@dummy.com", "Doe", null);
        Student insertedStudent = service.insertStudent(student);
        Student retrievedStudent = service.getStudent(insertedStudent.getId());
        assertNotNull(retrievedStudent);
    }

    private Student createStudent(String email, String name, Passport passport) {
        Student student = new Student();
        student.setEmail(email);
        student.setName(name);
        student.setPassportId(passport);
        return student;
    }

    @After
    public void printAllDataAfterTest() {
        System.out.println(service.getAllStudents());
    }
}