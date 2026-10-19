import java.util.Random;

public class Message {
    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String message;
    private String messageHash;

    // Constructor
    public Message(int numMessagesSent, String recipient, String message) {
        this.numMessagesSent = numMessagesSent;
        this.recipient = recipient;
        this.message = message;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Generates a random 10-digit unique message ID
    private String generateMessageID() {
        Random random = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10));
        }
        return id.toString();
    }

    // 1. Validates that Message ID is not more than 10 characters
    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    // 2. Validates Recipient Cell Number (starts with international code e.g. +27 and max 10 chars/format check)
    public String checkRecipientCell() {
        if (recipient != null && recipient.startsWith("+") && recipient.length() <= 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Checks message length <= 250 characters
    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int over = message.length() - 250;
            return "Message exceeds 250 characters by " + over + " characters; please reduce the size.";
        }
    }

    // 3. Creates the Message Hash: First 2 digits of ID + : + numMessagesSent + : + First Word + Last Word (ALL CAPS)
    public String createMessageHash() {
        if (message == null || message.trim().isEmpty()) {
            return "";
        }

        String firstTwoDigits = messageID.substring(0, 2);
        
        // Extract words (removing common punctuation like commas, periods, etc.)
        String cleanMessage = message.replaceAll("[^a-zA-Z0-9\\s]", "").trim();
        String[] words = cleanMessage.split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String combinedHash = firstTwoDigits + ":" + numMessagesSent + ":" + firstWord + lastWord;
        return combinedHash.toUpperCase();
    }

    // 4. Handles sending options (Send, Disregard/Delete, Store)
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                return "Message successfully sent";
            case 2:
                return "Press 0 to delete the message";
            case 3:
                return "Message successfully stored";
            default:
                return "Invalid choice selected.";
        }
    }

    // 5. Formats the full message details for display
    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + message;
    }

    // Store Message JSON stub method (Research task)
    public void storeMessage() {
        // Research note: For full JSON persistence, use a library like Jackson or Gson
        // String jsonString = String.format("{\"id\":\"%s\",\"recipient\":\"%s\",\"message\":\"%s\"}", messageID, recipient, message);
        System.out.println("JSON storage feature ready for implementation.");
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
}
