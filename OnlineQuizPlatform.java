import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineQuizPlatform {
    private static Scanner scanner = new Scanner(System.in);
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static Question[] questions;

    public static void main(String[] args) {
        // Initialize questions
        initializeQuestions();

        // Start quiz
        startQuiz();
    }

    private static void initializeQuestions() {
        // Define your questions here
        questions = new Question[]{
                new Question("What is the capital of France?", new String[]{"1. Paris", "2. London", "3. Rome", "4. Berlin"}, "1"),
                new Question("What is the largest mammal?", new String[]{"1. Elephant", "2. Blue Whale", "3. Giraffe", "4. Polar Bear"}, "2"),
                new Question("Is Python Object-Oriented Programming Language?", new String[]{"1.True","2.False"}, "1"),
                new Question("Who is the author 'One Indian Girl'?", new String[]{"1.Chetan Bhagat", "2. Gita Mehta", "3. Jaswant Singh", "4. Vikram Seth"}, "1"),
                // Add more questions here
        };
    }

    private static void startQuiz() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                showResult();
                timer.cancel();
                System.exit(0);
            }
        };
        timer.schedule(task, 30 * 1000); // 30 seconds

        for (Question question : questions) {
            displayQuestion(question);
            String userAnswer = scanner.nextLine().toUpperCase();
            if (userAnswer.equals(question.getCorrectAnswer())) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Incorrect! The correct answer is: " + question.getCorrectAnswer());
                System.out.println();
            }
            currentQuestionIndex++;
        }

        showResult();
    }

    private static void displayQuestion(Question question) {
        System.out.println(question.getQuestion());
        for (String option : question.getOptions()) {
            System.out.println(option);
        }
        System.out.print("Your answer: ");
    }

    private static void showResult() {
        System.out.println("Quiz ended!");
        System.out.println("Your score: " + score + "/" + questions.length);
        System.out.println("Correct Answers: " + score);
        System.out.println("Incorrect Answers: " + (questions.length - score));
    }
}

class Question {
    private String question;
    private String[] options;
    private String correctAnswer;

    public Question(String question, String[] options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer.toUpperCase();
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}