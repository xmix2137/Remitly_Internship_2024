package pl.ksikora;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Verifier {

    public static boolean verifyInputJSON(String filePath) {
        try {
            String jsonData = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(jsonData);

            if (!jsonObject.has("PolicyName")) {
                System.err.println("Missing required field 'PolicyName' in JSON file.");
                return false;
            }

            if (!(jsonObject.get("PolicyName") instanceof String)) {
                System.err.println("Field 'PolicyName' must be of string type.");
                return false;
            }

            String policyName = jsonObject.getString("PolicyName");
            String policyNamePattern = "[\\w+=,.@-]+";
            if (!Pattern.matches(policyNamePattern, policyName)) {
                System.err.println("Field 'PolicyName' does not match pattern.");
                return false;
            }

            int policyNameLength = policyName.length();
            if (policyNameLength < 1 || policyNameLength > 128) {
                System.err.println("Field 'PolicyName' must have length between 1 and 128 characters.");
                return false;
            }

            if (!jsonObject.has("PolicyDocument")) {
                System.err.println("Missing required field 'PolicyDocument' in JSON file.");
                return false;
            }

            if (!(jsonObject.get("PolicyDocument") instanceof JSONObject)) {
                System.err.println("Field 'PolicyDocument' must be of JSONObject type.");
                return false;
            }

            JSONObject policyDocument = jsonObject.getJSONObject("PolicyDocument");

            if (!policyDocument.has("Version")) {
                System.err.println("Missing required field 'Version' in 'PolicyDocument' in JSON file.");
                return false;
            }

            if (!policyDocument.has("Statement")) {
                System.err.println("Missing required field 'Statement' in 'PolicyDocument' in JSON file.");
                return false;
            }

            JSONArray statements = policyDocument.getJSONArray("Statement");

            for (int i = 0; i < statements.length(); i++) {
                JSONObject statement = statements.getJSONObject(i);
                Object resource = statement.get("Resource");

                if (resource.equals("*")) {
                    return false;
                }
            }

            return true;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error verifying JSON data: " + e.getMessage());
            return false;
        }
    }
}
