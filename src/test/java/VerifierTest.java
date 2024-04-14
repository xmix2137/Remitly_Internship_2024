import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.ksikora.Verifier;

public class VerifierTest {

    @Test
    public void testEmptyFile() {
        assertFalse(Verifier.verifyInputJSON("src/main/resources/empty.jason.json"));
    }

    @Test
    public void testInvalidJSONFormat() {
        assertFalse(Verifier.verifyInputJSON("src/main/resources/invalid_format.json"));
    }

    @Test
    public void testMissingPolicyName() {
        assertFalse(Verifier.verifyInputJSON("src/main/resources/missing_policy_name.json"));
    }

    @Test
    public void testInvalidPolicyNameCharacters() {
        assertFalse(Verifier.verifyInputJSON("src/main/resources/invalid_policy_name_characters.json"));
    }

    @Test
    public void testPolicyNameLengthExceedsMaximum() {
        assertFalse(Verifier.verifyInputJSON("src/main/resources/policy_name_too_long.json"));
    }

    @Test
    public void testMissingPolicyDocument() {
        assertFalse(Verifier.verifyInputJSON("src/main/resources/missing_policy_document.json"));
    }

    @Test
    public void testInvalidPolicyDocumentType() {
        assertFalse(Verifier.verifyInputJSON("src/main/resources/invalid_policy_document_type.json"));
    }

    @Test
    public void testMissingVersionInPolicyDocument() {
        assertFalse(Verifier.verifyInputJSON("src/main/resources/missing_version_in_policy_document.json"));
    }

    @Test
    public void testMissingStatementInPolicyDocument() {
        assertFalse(Verifier.verifyInputJSON("src/main/resources/missing_statement_in_policy_document.json"));
    }

    @Test
    public void testResourceContainsSingleAsterisk() {
        assertFalse(Verifier.verifyInputJSON("src/main/resources/resource_contains_single_asterisk.json"));
    }

    @Test
    public void testValidJSON() {
        assertTrue(Verifier.verifyInputJSON("src/main/resources/valid.json"));
    }

    @Test
    public void testNoAsteriskInAnyResource() {
        assertTrue(Verifier.verifyInputJSON("src/main/resources/many_resources_no_asterisk.json"));
    }

    @Test
    public void testSingleAsteriskInOneResource() {
        assertFalse(Verifier.verifyInputJSON("src/main/resources/many_resources_one_asterisk.json"));
    }

    @Test
    public void testAsteriskInAllResources() {
        assertFalse(Verifier.verifyInputJSON("src/main/resources/many_resources_many_asterisk.json"));
    }
}

