# Verifier

A simple Java application for verifying JSON policy files.
It checks the structure and content of JSON files to ensure they meet certain format criteria AWS::IAM::Role Policy and return logical false if an input JSON Resource field contains a single asterisk and true in any other case.

## Usage

To use the Policy Verifier, simply run the application and provide the path to the JSON file you want to check.
The application will analyze the file and output any errors or warnings encountered during the verification process.

### Running the Application

You can run the application using any Java IDE or from the command line. If you're using Maven, you can build and run the application with the following commands:

```
mvn clean install
java -jar target/policy-verifier.jar <path_to_json_file>
```

Replace `<path_to_json_file>` with the path to the JSON file you want to verify.

## Features

- Validates the structure and content of JSON policy files.
- Checks whether the provided JSON file complies with the AWS::IAM::Role Policy format
- Provides detailed error messages for easy troubleshooting.
- Return logical false if an input JSON Resource field contains a single asterisk and true in any other case.


## Author

Krzysztof Sikora