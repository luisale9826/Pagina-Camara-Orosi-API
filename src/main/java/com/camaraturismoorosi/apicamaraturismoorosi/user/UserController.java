// package com.camaraturismoorosi.apicamaraturismoorosi.user;

// import java.util.Arrays;
// import java.util.List;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("api/v1/students")
// public class UserController {

//     private static final List<User> STUDENTS = Arrays.asList(
//         new User(1, "Luis Bonilla"),
//         new User(2, "Daniel López"), 
//         new User(3, "John Smith")
//     );

//     @GetMapping(path = "/{studentId}")
//     public User getUser(@PathVariable("studentId") Integer studentId) {
//         return STUDENTS
        
//                 .stream()
//                 .filter(student -> studentId.equals(student.getStudentId()))
//                 .findFirst()
//                 .orElseThrow(() -> new IllegalStateException("Student" + studentId + " does not exist"));
//     }

// }
