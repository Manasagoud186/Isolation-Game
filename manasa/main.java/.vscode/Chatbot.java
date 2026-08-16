import java.net.http.*;
import java.net.URI;
import java.util.Scanner;

public class Chatbot {

    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🎓 AI Education Chatbot Started!");
        System.out.println("Type 'exit' to quit.\n");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Chatbot: Goodbye!");
                break;
            }

            String response = askAI(userInput);
            System.out.println("Chatbot: " + response + "\n");
        }
    }

    public static String askAI(String message) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String json = """
                {
                  "model": "gpt-4o-mini",
                  "messages": [
                    {"role": "system", "content": "You are an educational tutor chatbot."},
                    {"role": "user", "content": "%s"}
                  ]
                }
                """.formatted(message);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return extractMessage(response.body());
    }

    // Extracts the AI message from the JSON response
    public static String extractMessage(String json) {
        try {
            int index = json.indexOf("\"content\":");
            int start = json.indexOf("\"", index + 10) + 1;
            int end = json.indexOf("\"", start);
            return json.substring(start, end);
        } catch (Exception e) {
            return "Error reading AI response.";
        }
    }
}
