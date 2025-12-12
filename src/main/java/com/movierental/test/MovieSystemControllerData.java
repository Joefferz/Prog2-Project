package main.java.com.movierental.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MovieSystemControllerData {

    // --- Data Lists ---
    private ArrayList<Person> members = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Rental> rentals = new ArrayList<>();

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    // --- Getters & Setters for testing ---
    public ArrayList<Movie> getMovies() { return movies; }
    public void setMovies(ArrayList<Movie> movies) { this.movies = movies; }

    public ArrayList<Person> getMembers() { return members; }
    public void setMembers(ArrayList<Person> members) { this.members = members; }

    public ArrayList<Rental> getRentals() { return rentals; }
    public void setRentals(ArrayList<Rental> rentals) { this.rentals = rentals; }

    // --- ID Generation ---
    public String generateMovieID() {
        int max = 0;
        for (Movie m : movies) {
            String numPart = m.getMovieID().substring(2); // "MV"
            max = Math.max(max, Integer.parseInt(numPart));
        }
        return "MV" + (max + 1);
    }

    public String generateMemberID(String prefix) {
        int max = 0;
        for (Person p : members) {
            if (p.getCustomerID().startsWith(prefix)) {
                String num = p.getCustomerID().substring(2);
                max = Math.max(max, Integer.parseInt(num));
            }
        }
        return prefix + (max + 1);
    }

    // --- Find methods ---
    public Movie findMovie(String id) throws Exception {
        for (Movie m : movies) if (m.getMovieID().equalsIgnoreCase(id)) return m;
        throw new Exception("Movie not found.");
    }

    public Person findMember(String id) throws Exception {
        for (Person p : members) if (p.getCustomerID().equalsIgnoreCase(id)) return p;
        throw new Exception("Member not found.");
    }

    // --- Rental logic ---
    public Rental rentMovie(String movieID, String memberID) throws Exception {
        Movie m = findMovie(movieID);
        Person p = findMember(memberID);

        if (!m.isRentable().equalsIgnoreCase("Available"))
            throw new Exception("Movie is already rented.");

        Rental r = new Rental(memberID, movieID, LocalDate.now());
        rentals.add(r);
        m.updateAvailability(); // becomes Unavailable

        return r;
    }

    public double returnMovie(String movieID, String memberID, LocalDate returnDate) throws Exception {
        Movie m = findMovie(movieID);

        if (m.isRentable().equalsIgnoreCase("Available"))
            throw new Exception("Movie is not rented.");

        Rental active = rentals.stream()
                .filter(r -> r.getMovieRentedID().equalsIgnoreCase(movieID))
                .filter(r -> r.getCustomerRenterID().equalsIgnoreCase(memberID))
                .filter(r -> r.getDateReturned() == null)
                .findFirst()
                .orElseThrow(() -> new Exception("No active rental found."));

        active.setDateReturned(returnDate);
        m.updateAvailability();

        Person customer = findMember(memberID);
        return active.calculate(customer.getMembership());
    }

    // --- JSON Save/Load ---
    public <T> void saveList(String file, ArrayList<T> list) {
        try (FileWriter w = new FileWriter(new File("Data", file))) {
            gson.toJson(list, w);
        } catch (Exception ignored) {}
    }

    public <T> ArrayList<T> loadList(String file, java.lang.reflect.Type type) {
        try (FileReader r = new FileReader(new File("Data", file))) {
            return gson.fromJson(r, type);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // --- LocalDate Adapter ---
    public static class LocalDateAdapter extends com.google.gson.TypeAdapter<LocalDate> {
        @Override
        public void write(JsonWriter out, LocalDate date) throws IOException {
            if (date == null) out.nullValue();
            else out.value(date.toString());
        }
        @Override
        public LocalDate read(JsonReader in) throws IOException {
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                in.nextNull(); return null;
            }
            return LocalDate.parse(in.nextString());
        }
    }
}

