import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    private final List<Message> messages = new ArrayList<>();
    private int totalMessagesSent = 0;

    public String SentMessage(Message message, int choice) {
        if (choice == 1) {
            messages.add(message);
            totalMessagesSent++;
            return "Message successfully sent.";
        }
        if (choice == 2) {
            return "Press 0 to delete the message.";
        }
        if (choice == 3) {
            messages.add(message);
            if (storeMessage(message)) {
                return "Message successfully stored.";
            }
            messages.remove(message);
            return "Message could not be stored.";
        }
        return "Invalid option.";
    }

    public String printMessages() {
        StringBuilder result = new StringBuilder();
        for (Message message : messages) {
            result.append("Message ID: ").append(message.getMessageID()).append("\n");
            result.append("Message Hash: ").append(message.getMessageHash()).append("\n");
            result.append("Recipient: ").append(message.getRecipient()).append("\n");
            result.append("Message: ").append(message.getMessage()).append("\n\n");
        }
        return result.toString();
    }

    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    public boolean storeMessage(Message message) {
        try {
            Path path = Path.of("messages.json");
            String existing = Files.exists(path) ? Files.readString(path) : "[]";
            if (!existing.trim().startsWith("[")) existing = "[]";

            String jsonObj = "{\"messageID\":\"" + escapeJson(message.getMessageID()) + 
                    "\",\"messageHash\":\"" + escapeJson(message.getMessageHash()) + 
                    "\",\"recipient\":\"" + escapeJson(message.getRecipient()) + 
                    "\",\"message\":\"" + escapeJson(message.getMessage()) + "\"}";

            String content = existing.trim();
            content = content.substring(0, content.length() - 1).trim();
            if (content.length() > 1) content += ",";
            content += jsonObj + "]";
            
            Files.writeString(path, content);
            return true;
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
            return false;
        }
    }

    private String escapeJson(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }
}
