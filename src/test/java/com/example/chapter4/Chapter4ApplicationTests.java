
package com.example.chapter4;

import com.example.chapter4.controllers.StudentController;
import com.example.chapter4.entities.Student;
import com.example.chapter4.services.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Chapter4ApplicationTests {

	@Mock
	private StudentService studentService;

	@InjectMocks
	private StudentController studentController;

	@Test
	void testSaveStudent() {
		Student student = new Student();
		student.setId(1);
		student.setNom("Mido");

		// sauvegarde
		when(studentService.save(any(Student.class))).thenReturn(student);

		// sauvegarder l'étudiant
		ResponseEntity<Student> response = studentController.save(student);

		// Vérifier statut
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Mido", response.getBody().getNom());
	}

	@Test
	void testDeleteStudent() {
		// suppression
		when(studentService.delete(1)).thenReturn(true);

		ResponseEntity<Void> response = studentController.delete(1);

		// Vérifier statut
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void testFindAllStudents() {
		// Créer student
		Student student1 = new Student();
		Student student2 = new Student();

		// récupération
		when(studentService.findAll()).thenReturn(Arrays.asList(student1, student2));

		ResponseEntity<List<Student>> response = studentController.findAll();

		assertEquals(2, response.getBody().size());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testCountStudents() {
		when(studentService.countStudents()).thenReturn(10L);

		ResponseEntity<Long> response = studentController.countStudent();

		assertEquals(10L, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testFindByYear() {
		when(studentService.findNbrStudentByYear()).thenReturn(Arrays.asList());

		ResponseEntity<Collection<?>> response = studentController.findByYear();

		assertEquals(0, response.getBody().size());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
