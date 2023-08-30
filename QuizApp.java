import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Question {
    private String text;
    private String[] options;
    private int correctOption;

    public Question(String text, String[] options, int correctOption) {
        this.text = text;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }
}

public class QuizApp {
    private static Map<String, User> usersDatabase = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;
    private static Question[] questions;

    public static void main(String[] args) {
        initializeUsers();
        login();
        loadQuestions();
        startQuiz();
        logout();
    }

    private static void initializeUsers() {
        usersDatabase.put("user1", new User("user1", "pass123"));
        usersDatabase.put("user2", new User("user2", "pass456"));
        // Add more users as needed
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = usersDatabase.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            currentUser = user;
        } else {
            System.out.println("Login failed. Invalid credentials.");
            System.exit(0);
        }
    }

    private static void loadQuestions() {
        questions = new Question[]{
            new Question("What is the capital of France?",
                         new String[]{"London", "Berlin", "Paris", "Madrid"},
                         2),
            // Add more questions
        };
    }

    private static void startQuiz() {
        int score = 0;
        for (Question question : questions) {
            System.out.println(question.getText());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            System.out.print("Select an option: ");
            int selectedOption = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (question.isCorrect(selectedOption)) {
                score++;
            }
        }

        System.out.println("Quiz completed. Your score: " + score);
    }

    private static void logout() {
        System.out.println("Logout successful.");
    }
}
