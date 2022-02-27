package com.keyin.client.Member;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MemberClient {
    public static void memberMenu() throws IOException, InterruptedException {
            System.out.println("***** MEMBER MENU *****");
            System.out.println("Press 1 to Add");
            System.out.println("Press 2 to Search");
            System.out.println("Press 3 to Update");
            System.out.println("Press 4 to Delete");
            System.out.println("Press 0 to Exit");

            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();

            switch (input) {
                case 0:
                    System.out.println("Exiting....");
                    System.exit(0);
                case 1:
                    httpPost();
                    break;
                case 2:
                    httpGet();
                    break;
                case 3:
                    httpPut();
                    break;
                case 4:
                    httpDelete();
                    break;
            }
        }



    public static void httpPost() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("* Please complete the following to Add Member:  ");
        System.out.println("First name: ");
        scanner.nextLine();
        System.out.println("Last name: ");
        scanner.nextLine();
        System.out.println("Address: ");
        scanner.nextLine();
        System.out.println("Email: ");
        scanner.nextLine();
        System.out.println("Phone Number: ");
        scanner.nextLine();
        System.out.println("Duration of Membership: ");
        scanner.nextLine();
        System.out.println("Membership Type: ");
        scanner.nextLine();
        System.out.println("Current Tournaments: ");
        scanner.nextLine();
        System.out.println("Past Tournaments: ");
        scanner.nextLine();
        System.out.println("Upcoming Tournaments: ");
        scanner.nextLine();
        System.out.println("Member Added!");

        Map<Object, Object> person = new HashMap<>();
        person.put("personId", 8);
        person.put("startDate", "24/10/2010");
        person.put("duration", "24/10/2011");
        person.put("membershipType", "2");
        person.put("currentId", "8");
        person.put("pastId", "5");
        person.put("upcomingId", "4");

        ObjectMapper posted = new ObjectMapper();
        String requestBody = posted.writeValueAsString(person);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/v1/member/"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 201) {
                System.out.println(response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void httpGet() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID number of the member you are searching for: ");
        int search = scanner.nextInt();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080//v1/member/" + search))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println(response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void httpPut() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID number of the member you are looking for to update: ");
        Map<Object, Object> person = new HashMap<>();
        int update = scanner.nextInt();
        person.put("personId", 1);
        person.put("startDate", "10/11/2009");
        person.put("duration", "10/11/2012");
        person.put("membershipTypeId", "1");
        person.put("currentId", "4");
        person.put("pastId", "2");
        person.put("upcomingId", "3");

        ObjectMapper posted = new ObjectMapper();
        String requestBody = posted
                .writeValueAsString(person);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/v1/member/" + update))
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 202) {
                System.out.println("Updated Membership : " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void httpDelete() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID number of the member you want to delete: ");
        int delete = scanner.nextInt();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/v1/member/" + delete))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Deleted Membership" + delete + "Deleted");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}