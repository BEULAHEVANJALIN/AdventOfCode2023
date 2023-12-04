import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Day1Trebuchet {
    private static final Map<String, Integer> wordToDigit = new HashMap<>();

    static {
        wordToDigit.put("one", 1);
        wordToDigit.put("two", 2);
        wordToDigit.put("three", 3);
        wordToDigit.put("four", 4);
        wordToDigit.put("five", 5);
        wordToDigit.put("six", 6);
        wordToDigit.put("seven", 7);
        wordToDigit.put("eight", 8);
        wordToDigit.put("nine", 9);
    }

    public static void main(String[] args) {
        try {
            URL url = new URL("https://adventofcode.com/2023/day/1/input");
            HttpURLConnection connection = getHttpURLConnection(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            int result = 0;
            while ((line = reader.readLine()) != null) {
                result += extractAndParseInt(line);
            }
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static int extractAndParseInt(String line) {
        String firstDigit = "";
        String lastDigit = "";
        for (int i = 0; i < line.length(); i++) {
            for (String word : wordToDigit.keySet()) {
                if (Character.isDigit(line.charAt(i))) {
                    if (firstDigit.isEmpty()) {
                        firstDigit = String.valueOf(line.charAt(i));
                    }
                    lastDigit = String.valueOf(line.charAt(i));
                } else if (line.startsWith(word, i)) {
                    if (firstDigit.isEmpty()) {
                        firstDigit = String.valueOf(wordToDigit.get(word));
                    }
                    lastDigit = String.valueOf(wordToDigit.get(word));
                }
            }
        }

        try {
            return firstDigit.isEmpty() ? 0 : Integer.parseInt(firstDigit + lastDigit);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setRequestProperty("Cookie", "YOUR_SESSION_COOKIE");
        return connection;
    }
}
