package org.example;


import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import org.example.location.Location;
import org.example.location.LocationDaoImpl;
import org.example.location.LocationService;
import org.example.location.LocationServiceImpl;
import org.example.weather.WeatherData;
import org.example.weather.WeatherService;
import org.example.weather.WeatherServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final LocationService locationService = new LocationServiceImpl(new LocationDaoImpl());
    private static final WeatherService weatherService = new WeatherServiceImpl();

    public static void main(String[] args) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Add a location");
            System.out.println("2. View all locations");
            System.out.println("3. Get weather data for a location");
            System.out.println("4. Exit");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    addLocation(scanner);
                    break;
                case "2":
                    viewAllLocations();
                    break;
                case "3":
                    getWeatherData(scanner);
                    break;
                case "4":
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addLocation(Scanner scanner) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException {
        System.out.println("Enter the location details:");
        Location location = new Location();

        System.out.print("ID: ");
        location.setId(scanner.nextLine());

        System.out.print("Latitude: ");
        location.setLatitude(Double.parseDouble(scanner.nextLine()));

        System.out.print("Longitude: ");
        location.setLongitude(Double.parseDouble(scanner.nextLine()));

        System.out.print("City: ");
        location.setCity(scanner.nextLine());

        System.out.print("Region (optional): ");
        location.setRegion(scanner.nextLine());

        System.out.print("Country: ");
        location.setCountry(scanner.nextLine());

        locationService.addLocation(location);
        System.out.println("Location added successfully.");
    }

    private static void viewAllLocations() {
        List<Location> locations = locationService.getAllLocations();

        System.out.println("Locations:");
        for (Location location : locations) {
            System.out.println(location);
        }
    }

    private static void getWeatherData(Scanner scanner) {
        System.out.print("Enter the ID of the location: ");
        String id = scanner.nextLine();

        Location location = locationService.findById(id);
        if (location == null) {
            System.out.println("Location not found.");
            return;
        }

        WeatherData weatherData = weatherService.getWeatherData(location);
        System.out.println("Weather data: ");
        System.out.println("\t Temperature: " + weatherData.getTemperature() + " C");
        System.out.println("\t Pressure: " + weatherData.getPressure() + " millibars");
        System.out.println("\t Humidity: " + weatherData.getHumidity() + " %");
        System.out.println("\t Wind direction: " + weatherData.getWindDirection() + " degrees");
    }
}
