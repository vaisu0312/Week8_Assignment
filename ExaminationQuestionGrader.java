import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class ExamQuestion {
    String correctAnswer, studentAnswer;
    double points;
    ExamQuestion(String correctAnswer, String studentAnswer, double points) {
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.points = points;
    }
    abstract double score();
    abstract String type();
}

class ObjectiveQuestion extends ExamQuestion {
    String questionType;
    ObjectiveQuestion(String type, String correct, String student, double points) {
        super(correct, student, points);
        questionType = type;
    }
    double score() {
        return correctAnswer.equalsIgnoreCase(studentAnswer) ? points : 0;
    }
    String type() { return questionType; }
}

class EssayQuestion extends ExamQuestion {
    EssayQuestion(String correct, String student, double points) {
        super(correct, student, points);
    }
    double score() {
        String[] keywords = correctAnswer.split(",");
        int matches = 0;
        String answer = studentAnswer.toLowerCase();
        for (String keyword : keywords) {
            if (answer.contains(keyword.trim().toLowerCase()))
                matches++;
        }
        if (matches >= 2) return points * 0.75;
        if (matches == 1) return points * 0.50;
        return 0;
    }
    String type() { return "ESSAY"; }
}

public class ExaminationQuestionGrader {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] first = line.split("\\\\s+", 2);
            String type = first[0].toUpperCase();
            Matcher matcher = pattern.matcher(line);
            String[] quoted = new String[3];
            int count = 0;
            while (matcher.find() && count < 3)
                quoted[count++] = matcher.group(1);

            String[] tail = line.split("\"");
            double points = Double.parseDouble(tail[tail.length - 1].trim());

            ExamQuestion question;
            if (type.equals("ESSAY"))
                question = new EssayQuestion(quoted[1], quoted[2], points);
            else
                question = new ObjectiveQuestion(type, quoted[1], quoted[2], points);

            double score = question.score();
            System.out.printf("%s: %.2f%n", question.type(), score);
            total += score;
        }

        System.out.printf("Total Score: %.2f%n", total);
        sc.close();
    }
}
