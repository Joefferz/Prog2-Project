package main.java.com.movierental.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MovieSystemControllerTest {

    private MovieSystemControllerData ctrl;

    @BeforeEach
    void setUp() {
        ctrl = new MovieSystemControllerData();
    }

    // --- ID generation tests ---
    @Test
    void testGenerateMovieID() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("MV1", "Test1"));
        movies.add(new Movie("MV3", "Test2"));
        movies.add(new Movie("MV7", "Test3"));

        ctrl.setMovies(movies);

        String newID = ctrl.generateMovieID();
        assertEquals("MV8", newID);
    }

    @Test
    void testGenerateMemberID() {
        ArrayList<Person> members = new ArrayList<>();
        members.add(new Student("Alice", "ST1", "Student", "SchoolA", "10"));
        members.add(new ExternalMember("Bob", "EX1", "External", "Engineer", "TechCorp"));
        members.add(new Student("Charlie", "ST3", "Student", "SchoolB", "11"));

        ctrl.setMembers(members);

        String studentID = ctrl.generateMemberID("ST");
        String externalID = ctrl.generateMemberID("EX");

        assertEquals("ST4", studentID);
        assertEquals("EX2", externalID);
    }

    // --- Find tests ---
    @Test
    void testFindMovieSuccess() throws Exception {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("MV10", "Avatar"));
        ctrl.setMovies(movies);

        Movie m = ctrl.findMovie("MV10");
        assertEquals("Avatar", m.getMovieName());
    }

    @Test
    void testFindMovieNotFound() {
        ctrl.setMovies(new ArrayList<>());
        assertThrows(Exception.class, () -> ctrl.findMovie("MV99"));
    }

    @Test
    void testFindMemberSuccess() throws Exception {
        ArrayList<Person> members = new ArrayList<>();
        members.add(new Student("Alice", "ST1", "Student", "SchoolA", "10"));
        ctrl.setMembers(members);

        Person p = ctrl.findMember("ST1");
        assertEquals("Alice", p.getName());
    }

    @Test
    void testFindMemberNotFound() {
        ctrl.setMembers(new ArrayList<>());
        assertThrows(Exception.class, () -> ctrl.findMember("ST99"));
    }

    // --- Rental tests ---
    @Test
    void testRentAndReturnMovie() throws Exception {
        Movie movie = new Movie("MV1", "Matrix");
        Student member = new Student("Alice", "ST1", "Student", "SchoolA", "10");

        ctrl.setMovies(new ArrayList<>(){{ add(movie); }});
        ctrl.setMembers(new ArrayList<>(){{ add(member); }});

        // Rent
        Rental rental = ctrl.rentMovie("MV1", "ST1");
        assertNotNull(rental);
        assertEquals("Unavailable", movie.isRentable());

        // Return
        LocalDate returnDate = LocalDate.now();
        double fee = ctrl.returnMovie("MV1", "ST1", returnDate);

        assertEquals("Available", movie.isRentable());
        assertTrue(fee >= 0); // Rental fee calculated
        assertEquals(returnDate, rental.getDateReturned());
    }

    // --- LocalDateAdapter tests ---
    @Test
    void testLocalDateSerialization() throws Exception {
        MovieSystemControllerData.LocalDateAdapter adapter = new MovieSystemControllerData.LocalDateAdapter();
        java.io.StringWriter sw = new java.io.StringWriter();
        adapter.write(new com.google.gson.stream.JsonWriter(sw), LocalDate.of(2024, 12, 25));
        assertEquals("\"2024-12-25\"", sw.toString());
    }

    @Test
    void testLocalDateDeserialization() throws Exception {
        MovieSystemControllerData.LocalDateAdapter adapter = new MovieSystemControllerData.LocalDateAdapter();
        java.io.StringReader sr = new java.io.StringReader("\"2024-12-25\"");
        LocalDate date = adapter.read(new com.google.gson.stream.JsonReader(sr));
        assertEquals(LocalDate.of(2024, 12, 25), date);
    }
}
