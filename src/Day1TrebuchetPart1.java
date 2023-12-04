import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Day1TrebuchetPart1 {
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
        for (char ch : line.toCharArray()) {
            if (Character.isDigit(ch)) {
                if (firstDigit.isEmpty()) {
                    firstDigit = String.valueOf(ch);
                }
                lastDigit = String.valueOf(ch);
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