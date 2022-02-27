package com.keyin.client;

import com.keyin.client.Member.MemberClient;
import com.keyin.client.Tournament.TournamentClient;

import java.io.IOException;

import java.util.concurrent.ExecutionException;
import java.util.Scanner;

import static com.keyin.client.Member.MemberClient.*;
import static com.keyin.client.Tournament.TournamentClient.*;

public class GolfClubApplication {

    public static void main(String[] args) throws InterruptedException, IOException {
        while (true) {
            System.out.println("********************************************");
            System.out.println("*         WELCOME TO THE GOLF CLUB         *");
            System.out.println("********************************************");
            System.out.println("");

            System.out.println("Please choose from the following 3 options: ");
            System.out.println("* M - Members");
            System.out.println("* T - Tournaments");
            System.out.println("* E - Exit");

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            if(userInput.equalsIgnoreCase("M")) {
                MemberClient.memberMenu();
                System.out.println("\nReturning to main menu...\n\n");
                continue;
            } else if(userInput.equalsIgnoreCase("T")) {
                TournamentClient.tournamentMenu();
                System.out.println("\nReturning to main menu...\n\n");
                continue;
            } else if(userInput.equalsIgnoreCase("E")) {
                break;
            } else {
                System.out.println("Error: Wrong Input. Please try again.");
                continue;
            }
        }
    }
}
