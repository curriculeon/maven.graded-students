package com.github.curriculeon.classroom;

import com.github.curriculeon.Classroom;
import com.github.curriculeon.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GetGradeBookTest {

    @Test
    public void test1() {
        // Create a Classroom with 5 Students
        Student student0 = new Student("Alice", "Smith", new Double[]{90.0, 80.0});
        Student student1 = new Student("Charlie", "Brown", new Double[]{80.0, 70.0});
        Student student2 = new Student("Bob", "Johnson", new Double[]{70.0, 60.0});
        Student student3 = new Student("Dave", "Jones", new Double[]{60.0, 50.0});
        Student student4 = new Student("Eve", "Davis", new Double[]{00.0, 00.0});
        Student[] students = new Student[]{student0, student1, student2, student3, student4};
        Classroom classroom = new Classroom(students);

        // Define the expected grade book mapping
        Map<Student, Character> expected = new HashMap<>();
        expected.put(student0, 'A');
        expected.put(student1, 'B');
        expected.put(student2, 'C');
        expected.put(student3, 'D');
        expected.put(student4, 'F');

        // Test the getGradeBook() method
        test(expected, classroom);
    }

    @Test
    public void test2() {
        // Create a Classroom with 3 Students
        Student[] students = new Student[]{
                new Student("Alice", "Smith", new Double[]{80.0, 90.0}),
                new Student("Bob", "Johnson", new Double[]{70.0, 60.0}),
                new Student("Charlie", "Brown", new Double[]{50.0, 40.0})
        };
        Classroom classroom = new Classroom(students);

        // Define the expected grade book mapping
        Map<Student, Character> expected = new HashMap<>();
        expected.put(students[0], 'A');
        expected.put(students[1], 'C');
        expected.put(students[2], 'F');

        // Test the getGradeBook() method
        test(expected, classroom);
    }

    private void test(final Map<Student, Character> expected, final Classroom inputClassroom) {
        final Map<Student, Character> actual = inputClassroom.getGradeBook();
        Assert.assertEquals(expected.size(), actual.size());
        for (final Map.Entry<Student, Character> entry : expected.entrySet()) {
            final Student expectedKey = entry.getKey();
            final Character expectedValue = entry.getValue();
            final Character actualValue = actual.get(entry.getKey());
            String errorMessage = "`%s` was expected to have grade of `%s`, but instead of grade of `%s`.";
            errorMessage = String.format(errorMessage, expectedKey.toString(), expectedValue, actualValue);
            Assert.assertTrue(actual.containsKey(expectedKey));
            Assert.assertEquals(errorMessage, expectedValue, actualValue);
        }
    }
}
