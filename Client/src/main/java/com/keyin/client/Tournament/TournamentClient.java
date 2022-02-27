package com.keyin.client.Tournament;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class TournamentClient {
    public static void tournamentMenu() throws IOException, InterruptedException {
        System.out.println("***** TOURNAMENT MENU *****");
        System.out.println("Press 1 to Add");
        System.out.println("Press 2 to Search");
        System.out.println("Press 3 to Update");
        System.out.println("Press 4 to Delete");
        System.out.println("Press 0 to Exit");

        Scanner scanner = new Scanner(System.in);
        int input2 = scanner.nextInt();

        switch (input2) {
            case 0:
                System.out.println("Exiting....");
                System.exit(0);
            case 1:
                httpPostTournament();
                break;
            case 2:
                httpGetTournament();
                break;
            case 3:
                httpPutTournament();
                break;
            case 4:
                httpDeleteTournament();
                break;
        }
    }


    public static void httpPostTournament() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Fill in the following fields:");
        System.out.println("Heading: ");
        String heading = scanner.nextLine();
        System.out.println("Start Date: ");
        String startDate = scanner.nextLine();
        System.out.println("End Date: ");
        String endDate = scanner.nextLine();
        System.out.println("Location: ");
        String location = scanner.nextLine();
        System.out.println("Fee: ");
        double fee = Double.parseDouble(scanner.nextLine());
        System.out.println("Prize: ");
        double prize = Double.parseDouble(scanner.nextLine());
        System.out.println("Tournament added!");

        Map<Object, Object> people = new HashMap<>();
        people.put("start", "10/10/2010");
        people.put("end", "11/11/2010");
        people.put("location", "California");
        people.put("fee", 10.0);
        people.put("prize", 200.0);
        people.put("members", "Ash, Chris, Brad");
        people.put("standings", "1.Ash, 2.Brad, 3.Chris");

        ObjectMapper posted = new ObjectMapper();
        String requestBody = posted.writeValueAsString(people);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/v1/tournament/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 201) {
                System.out.println("Posted Tournament : " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void httpGetTournament() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id # for the tournament you are looking for: ");
        int input2 = scanner.nextInt();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/v1/tournament/" + input2))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("The tournament you requested : " + response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void httpPutTournament() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Id # of the tournament to update: ");
        int update = scanner.nextInt();
        Map<Object, Object> people = new HashMap<>();
        people.put("start", "01/22/2021");
        people.put("end", "02/22/2022");
        people.put("location", "Paradise");
        people.put("fee", 20.0);
        people.put("prize", 300.0);
        people.put("members", "Steve, James, Bob, April");
        people.put("standings", "1.James, 2.Steve, 3.April, 4.Bob");

        ObjectMapper posted = new ObjectMapper();
        String requestBody = posted
                .writeValueAsString(people);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/v1/tournament/" + update))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 202) {
                System.out.println("Updated Tournament : " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void httpDeleteTournament() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Id # to delete tournament: ");
        int delete = scanner.nextInt();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/v1/tournament/" + delete))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 204) {
                System.out.println("Deleted Tournament" + delete + " deleted");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}