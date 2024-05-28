package Menu;

import Lambda.FlightSortOption;
import Objects.*;
import Objects.Employee.Pilot;
import Serivice.CSVWriter;
import Status.*;
import MyExceptions.*;

import java.io.*;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    private List <Aircraft> aircraftList;
    private List <Passenger> passengerList;
    private List <Flight> flights;
    private List <Ticket> tickets;
    private List <Pilot> pilotList;
    private List <Runway> runwayList;
    private static Menu instance;

    private Menu() throws SQLException {
        flights = new ArrayList<>();
        passengerList = new ArrayList<>();
        tickets = new ArrayList<>();
        pilotList = new ArrayList<>();
        runwayList = new ArrayList<>();
        aircraftList = new ArrayList<>();
        ParseDataPilot();
        ParseDataAirCraft();
        parseRunways();
    }

    private void parseRunways() throws SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/INITData/runways.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int length = Integer.parseInt(parts[0].trim());
                    int width = Integer.parseInt(parts[1].trim());
                    String surfaceType = parts[2].trim();
                    runwayList.add(new Runway(length, width, surfaceType));
                } else {
                    System.err.println("Invalid format in line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Runway runway : runwayList) {
            System.out.println(runway);
            runway.create();
        }
    }

    private void ParseDataAirCraft() throws SQLException {
        String filePath = "src/INITData/aircraft.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) { // Ensure correct number of attributes
                    String name = parts[0].trim();
                    System.out.println(name);
                    String description = parts[1].trim();
                    System.out.println(description);
                    String type = parts[2].trim();
                    int totalCapacity = Integer.parseInt(parts[3].trim());
                    int seats = Integer.parseInt(parts[4].trim());
                    Aircraft aircraft = new Aircraft(name, description, type, totalCapacity, seats);
                    aircraftList.add(aircraft);
                } else {
                    System.err.println("Invalid format for aircraft entry: " + line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Aircraft aircraft : aircraftList) {
            aircraft.changePilot(pilotList.getFirst());
            aircraft.create();
        }
    }

    private void ParseDataPilot() throws SQLException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/INITData/pilot.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 4) {
                    String name = parts[0].trim();
                    double salary = Double.parseDouble(parts[1].trim());
                    String department = parts[2].trim();
                    String licenseNumber = parts[3].trim();
                    Pilot pilot = new Pilot(name, salary, department, licenseNumber);
                    pilotList.add(pilot);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        for (Pilot pilot : pilotList) {
            System.out.println(pilot);
            pilot.create();
        }
    }

    public static Menu getInstance() throws SQLException {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    private synchronized void number1() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adăugare Zbor");

        boolean ok = false;
        String nameCompany = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Compania aeriană: ");
                nameCompany = scanner.nextLine();
                if (nameCompany.length() < 1) throw new InputError("mai incearcă");
            } catch(Exception e) {
                ok = false;
            }
        }

        ok = false;
        String country = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Tara companiei aeriane " + nameCompany + ": ");
                country = scanner.nextLine();
                if (nameCompany.length() < 1) throw new InputError("mai incearcă");
            } catch(Exception e) {
                ok = false;
            }
        }

        Airline company = new Airline(nameCompany, country);

        ok = false;
        String nameAirport = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Aeroportul de plecare: ");
                nameAirport = scanner.nextLine();
                if (nameAirport.length() < 1) throw new InputError("mai incearcă");
            } catch(Exception e) {
                ok = false;
            }
        }

        ok = false;
        String city1 = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Orasul aeroportului de plecare: ");
                city1 = scanner.nextLine();
                if (city1.length() < 1) throw new InputError("mai incearcă");
            } catch(Exception e) {
                ok = false;
            }
        }

        Airport a1 = new Airport(nameAirport, city1, nameCompany);

        ok = false;
        LocalDate date1 = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Data de plecare (YYYY-MM-DD): ");
                date1 = LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                ok = false;
            }
        }

        ok = false;
        String nameAirport2 = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Aeroportul de sosire: ");
                nameAirport2 = scanner.nextLine();
                if (nameAirport2.length() < 1) throw new InputError("mai incearcă");
            } catch(Exception e) {
                ok = false;
            }
        }

        // Orasul aeroportului de sosire
        ok = false;
        String city2 = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Orasul aeroportului de sosire: ");
                city2 = scanner.nextLine();
                if (city2.length() < 1) throw new InputError("mai incearcă");
            } catch(Exception e) {
                ok = false;
            }
        }

        // Tara aeroportului de sosire
        ok = false;
        String country2 = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Tara aeroportului de sosire: ");
                country2 = scanner.nextLine();
                if (country2.length() < 1) throw new InputError("mai incearcă");
            } catch(Exception e) {
                ok = false;
            }
        }

        // Data de sosire
        ok = false;
        LocalDate dataSosire = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Data de sosire (YYYY-MM-DD): ");
                dataSosire = LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                ok = false;
                System.out.println("Formatul datei este invalid. Te rugăm să introduci data în formatul corect (YYYY-MM-DD).");
            }
        }

        // Durata zborului
        ok = false;
        Duration flightDuration = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Durata zborului (HH:MM): ");
                String durationString = scanner.nextLine();
                String[] durationParts = durationString.split(":");
                long hours = Long.parseLong(durationParts[0]);
                long minutes = Long.parseLong(durationParts[1]);
                flightDuration = Duration.ofHours(hours).plusMinutes(minutes);
            } catch (Exception e) {
                ok = false;
                System.out.println("Formatul duratei este invalid. Te rugăm să introduci durata în formatul corect (HH:MM).");
            }
        }

        Airport a2 = new Airport(nameAirport2, city2, country2);

        // Starea zborului
        ok = false;
        FlightStatus stare = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Starea zborului (SCHEDULED, DELAYED, CANCELLED): ");
                String statusStr = scanner.nextLine().toUpperCase();
                stare = FlightStatus.valueOf(statusStr);
            } catch (IllegalArgumentException e) {
                ok = false;
                System.out.println("Starea zborului introdusă este invalidă. Te rugăm să introduci o stare validă.");
            }
        }

        // Creare obiect Flight
        Flight flight = new Flight(company, a1, date1, a2, dataSosire, flightDuration, stare);
        flights.add(flight);
        flight.create();
        CSVWriter.getInstance().exportToCSV("1. Adăugare Zbor");
        System.out.println(flight);
    }

    private synchronized void number2() throws SQLException {
        System.out.println("Vizualizare Detalii Zbor");
        Flight flight = new Flight();

        CSVWriter.getInstance().exportToCSV("2. Vizualizare Detalii Zbor");
        try {
            System.out.println(flight.inRead());
        } catch(Exception e) {
            System.out.println("Id-ul nu este valid");
        }
    }

    private synchronized void number3() throws SQLException {
        CSVWriter.getInstance().exportToCSV("3. Actualizare Zbor");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Actualizare Zbor");

        Flight flight = null;
        System.out.print("Intrdu id-ul zborului cautat: ");
        int id = scanner.nextInt();
        int idx = 0;
        for (Flight f : flights) {
            if (id == f.getFlightId()) {
                flight = f;
                break;
            }
            idx++;
        }

        if (flight == null) {
            System.out.println("Id-ul nu este valid");
            return;
        }

        boolean ok = false;
        String nameCompany = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Compania aeriană: ");
                nameCompany = scanner.nextLine();
                if (nameCompany.length() < 1) throw new InputError("mai incearcă");
            } catch(Exception e) {
                ok = false;
            }
        }

        ok = false;
        String country = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Tara companiei aeriane " + nameCompany + ": ");
                country = scanner.nextLine();
                if (nameCompany.length() < 1) throw new InputError("mai incearcă");
            } catch(Exception e) {
                ok = false;
            }
        }

        Airline company = new Airline(nameCompany, country);

        ok = false;
        String nameAirport = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Aeroportul de plecare: ");
                nameAirport = scanner.nextLine();
                if (nameAirport.length() < 1) throw new InputError("mai incearcă");
            } catch(Exception e) {
                ok = false;
            }
        }

        ok = false;
        String city1 = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Orasul aeroportului de plecare: ");
                city1 = scanner.nextLine();
                if (city1.length() < 1) throw new InputError("mai incearcă");
            } catch(Exception e) {
                ok = false;
            }
        }

        Airport a1 = new Airport(nameAirport, city1, nameCompany);

        ok = false;
        LocalDate date1 = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Data de plecare (YYYY-MM-DD): ");
                date1 = LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                ok = false;
            }
        }

        ok = false;
        String nameAirport2 = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Aeroportul de sosire: ");
                nameAirport2 = scanner.nextLine();
                if (nameAirport2.length() < 1) throw new InputError("mai incearcă");
            } catch(Exception e) {
                ok = false;
            }
        }

        // Orasul aeroportului de sosire
        ok = false;
        String city2 = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Orasul aeroportului de sosire: ");
                city2 = scanner.nextLine();
                if (city2.length() < 1) throw new InputError("mai incearcă");
            } catch(Exception e) {
                ok = false;
            }
        }

        // Tara aeroportului de sosire
        ok = false;
        String country2 = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Tara aeroportului de sosire: ");
                country2 = scanner.nextLine();
                if (country2.length() < 1) throw new InputError("mai incearcă");
            } catch(Exception e) {
                ok = false;
            }
        }

        // Data de sosire
        ok = false;
        LocalDate dataSosire = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Data de sosire (YYYY-MM-DD): ");
                dataSosire = LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                ok = false;
                System.out.println("Formatul datei este invalid. Te rugăm să introduci data în formatul corect (YYYY-MM-DD).");
            }
        }

        // Durata zborului
        ok = false;
        Duration flightDuration = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Durata zborului (HH:MM): ");
                String durationString = scanner.nextLine();
                String[] durationParts = durationString.split(":");
                long hours = Long.parseLong(durationParts[0]);
                long minutes = Long.parseLong(durationParts[1]);
                flightDuration = Duration.ofHours(hours).plusMinutes(minutes);
            } catch (Exception e) {
                ok = false;
                System.out.println("Formatul duratei este invalid. Te rugăm să introduci durata în formatul corect (HH:MM).");
            }
        }

        Airport a2 = new Airport(nameAirport2, city2, country2);

        // Starea zborului
        ok = false;
        FlightStatus stare = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Starea zborului (SCHEDULED, DELAYED, CANCELLED): ");
                String statusStr = scanner.nextLine().toUpperCase();
                stare = FlightStatus.valueOf(statusStr);
            } catch (IllegalArgumentException e) {
                ok = false;
                System.out.println("Starea zborului introdusă este invalidă. Te rugăm să introduci o stare validă.");
            }
        }

        // modificare obiect Flight
        flight = new Flight(company, a1, date1, a2, dataSosire, flightDuration, stare);
        flight.setId(id);
        flight.update();
        flights.set(idx, flight);
    }

    private synchronized void number4() {
        CSVWriter.getInstance().exportToCSV("4. Ștergere Zbor");
        System.out.println("Stergere Zbor");
        Flight flight = new Flight();
        try {
            flight.inDelete();
            flights.remove(flight);
        } catch(Exception e) {
            System.out.println("Id-ul nu este valid");
        }
    }

    private synchronized void number5() throws SQLException {
        CSVWriter.getInstance().exportToCSV("5. Adăugare Pasager");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adăugare Pasager\n");

        Boolean ok = false;
        String name = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Nume: ");
                name = scanner.nextLine();
                if (name.isEmpty()) throw new InputError("mai incearca");
            } catch(Exception e) {
                ok = false;
            }
        }

        ok = false;
        String passportNumber = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Id pasaport: (2 litere mari si 7 cifre): ");
                passportNumber = scanner.nextLine();
                if (passportNumber.isEmpty()) throw new InputError("mai incearca");
                Pattern pattern = Pattern.compile("[A-Z]{2}[0-9]{7}");
                Matcher matcher = pattern.matcher(passportNumber);
                if (!matcher.matches()) throw new InvalidPassportNumberException(passportNumber);
            } catch(Exception e) {
                ok = false;
            }
        }

        ok = false;
        String nationality = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Nationalitate: ");
                nationality = scanner.nextLine();
                if (nationality.isEmpty()) throw new InputError("mai incearca");
            } catch (Exception e) {
                ok = false;
            }
        }

        ok = false;
        Integer weight = null;
        while (!ok) {
            try {
                ok = true;
                System.out.print("Greutate: ");
                weight = scanner.nextInt();
            } catch (Exception e) {
                ok = false;
            }
        }

        Passenger p = new Passenger(name, passportNumber, nationality, weight);
        System.out.println(p);
        p.create();
        passengerList.add(p);
    }

    private synchronized void number6() {
        CSVWriter.getInstance().exportToCSV("6. Vizualizare Listă Pasageri pentru un Zbor");
        Scanner scanner = new Scanner(System.in);
        System.out.println("izualizare Listă Pasageri pentru un Zbor");

        System.out.print("Introdu id-ul zborului cautat: ");
        int id = scanner.nextInt();
        int idx = 0;
        Flight flight = null;
        for (Flight f : flights) {
            if (id == f.getFlightId()) {
                flight = f;
                break;
            }
            idx++;
        }

        System.out.println("Avem Pasageri:\n");
        for (Ticket t : tickets) {
            if (t.getFlight() == flight) {
                System.out.println(t.getPassenger());
            }
        }
    }

    private synchronized void number7() {
        CSVWriter.getInstance().exportToCSV("7. Modificare Detalii Pasager");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Atribuie Zbor Pasager");

        System.out.print("Introdu id-ul zborului cautat: ");
        int id = scanner.nextInt();
        int idx = 0;
        Flight flight = null;
        for (Flight f : flights) {
            if (id == f.getFlightId()) {
                flight = f;
                break;
            }
            ++idx;
        }

        if (flight == null) {
            System.out.println("Id-ul nu este valid");
            return;
        }

        System.out.print("Introdu id-ul unui pasager: ");
        id = scanner.nextInt();
        int idx2 = 0;
        Passenger pa = null;
        for (Passenger f : passengerList) {
            if (id == f.getId()) {
                pa = f;
                break;
            }
            ++idx2;
        }

        if (pa == null) {
            System.out.println("Id-ul nu este valid");
            return;
        }

        System.out.print("Clasa (Economy, Business, First, ...): ");
        String seatc = scanner.nextLine();

        System.out.print("Pretul  ");
        float price = scanner.nextFloat();

        Ticket t = new Ticket(pa, flight, seatc, price);
        System.out.println(t);
        tickets.add(t);
    }

    private synchronized void number8() {
        CSVWriter.getInstance().exportToCSV("8. Căutarea Zborurilor după Destinație");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Căutarea Zborurilor după Destinație");

        System.out.println("Introdu o tara destinatie: ");
        String country = scanner.nextLine();

        for (Flight f : flights)
            if (f.getArrivalAirport().getCountry().equalsIgnoreCase(country) ||
                    f.getDepartureAirport().getCountry().equalsIgnoreCase(country)) {
                System.out.println(f);
            }
    }

    private synchronized void number9() {
        CSVWriter.getInstance().exportToCSV("9. Verificarea disponibilității Zborului");
        System.out.println("Verificarea disponibilității Zborului");
        flights.sort(FlightSortOption.STATUS);
        for (Flight f : flights) {
            System.out.println(f);
            if (f.getStatus() != FlightStatus.SCHEDULED)
                break;
        }
    }

    private synchronized void number10(){
        CSVWriter.getInstance().exportToCSV("10. Generare raport Zborurui");
        System.out.println("Generare raport Zborurui");

        // Generate and print flight reports based on different sorting criteria
        for (FlightSortOption sortOption : FlightSortOption.values()) {
            List <Flight> aflights = flights;
            aflights.sort(sortOption);
            System.out.println(aflights);
        }
    }

    public void AirlineMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Menu menu = Menu.getInstance();

        Boolean running = true;

        while (running) {
            System.out.println("Welcome to Airline Management\n");
            System.out.println("Select an option:");
            System.out.println("1. Adăugare Zbor");
            System.out.println("2. Vizualizare Detalii Zbor");
            System.out.println("3. Actualizare Zbor");
            System.out.println("4. Ștergere Zbor");
            System.out.println("5. Adăugare Pasager");
            System.out.println("6. Vizualizare Listă Pasageri pentru un Zbor");
            System.out.println("7. Atribuie Zbor Pasager");
            System.out.println("8. Căutarea Zborurilor după Destinație");
            System.out.println("9. Verificarea disponibilității Zborului");
            System.out.println("10. Generare raport Zborurui");
            System.out.println("0. Exit");

            System.out.print("Input: [0-10]: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    menu.number1();
                    break;
                case 2:
                    menu.number2();
                    break;
                case 3:
                    menu.number3();
                    break;
                case 4:
                    menu.number4();
                    break;
                case 5:
                    menu.number5();
                    break;
                case 6:
                    menu.number6();
                    break;
                case 7:
                    menu.number7();
                    break;
                case 8:
                    menu.number8();
                    break;
                case 9:
                    menu.number9();
                    break;
                case 10:
                    menu.number10();
                    break;
                default:
                    running = false;
                    break;
            }
        }
    }
}
