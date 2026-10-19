import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuickChatApp {
    private static int totalMessagesSentCount = 0;
    private static List<Message> sentMessagesList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Simulate Login Verification
        boolean loggedIn = true; // Set to true after valid login check
        if (!loggedIn) {
            System.out.println("Login failed. Exiting application.");
            return;
        }

        // 2. Welcome Message
        System.out.println("Welcome to QuickChat.\n");

        boolean running = true;

        while (running) {
            // 3. Numeric Menu options
            System.out.println("Select an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    processSendMessages(scanner);
                    break;
                case 2:
                    System.out.println("\nComing Soon.\n");
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting QuickChat. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
        scanner.close();
    }

    private static void processSendMessages(Scanner scanner) {
        System.out.print("\nHow many messages would you like to enter? ");
        int numToEnter = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numToEnter; i++) {
            System.out.println("\n--- Message " + (i + 1) + " ---");

            // Cell number input & validation
            String recipient = "";
            while (true) {
                System.out.print("Enter Recipient Cell Number (with country code e.g. +27718693002): ");
                recipient = scanner.nextLine();
                Message tempCheck = new Message(totalMessagesSentCount, recipient, "test");
                String validationMsg = tempCheck.checkRecipientCell();
                System.out.println(validationMsg);
                if (validationMsg.equals("Cell phone number successfully captured.")) {
                    break;
                }
            }

            // Message text input & length validation
            String messageText = "";
            while (true) {
                System.out.print("Enter Message (Max 250 characters): ");
                messageText = scanner.nextLine();
                Message tempCheck = new Message(totalMessagesSentCount, recipient, messageText);
                String lenMsg = tempCheck.checkMessageLength();
                if (lenMsg.equals("Message ready to send.")) {
                    System.out.println("Message ready to send.");
                    break;
                } else {
                    System.out.println(lenMsg);
                }
            }

            // Create valid message object
            Message msg = new Message(totalMessagesSentCount, recipient, messageText);

            // Display Action Menu
            System.out.println("\nChoose an option for this message:");
            System.out.println("1. Send Message");
            System.out.println("2. Disregard Message");
            System.out.println("3. Store Message to send later");
            System.out.print("Choice: ");
            int actionChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String result = msg.SentMessage(actionChoice);
            System.out.println("\n" + result);

            if (actionChoice == 1) {
                totalMessagesSentCount++;
                sentMessagesList.add(msg);
                
                // Display message details
                System.out.println("\n--- Message Sent Details ---");
                System.out.println(msg.printMessages());
            }
        }

        System.out.println("\n==================================");
        System.out.println("Total Messages Sent in Session: " + returnTotalMessagess());
        System.out.println("==================================\n");
    }

    public static int returnTotalMessagess() {
        return totalMessagesSentCount;
    }
}