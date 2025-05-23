import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.net.URL;

class Animal {
    String name;
    String imagePath;

    public Animal(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public char getFirstLetter() {
        return Character.toUpperCase(name.charAt(0));
    }
}

public class PreschoolTrainer extends JFrame {
    private JLabel imageLabel;
    private JTextField inputField;
    private JLabel resultLabel;
    private List<Animal> animals;
    private Animal currentAnimal;

    public PreschoolTrainer() {
        setTitle("Preschool Letter Trainer");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        animals = List.of(
                new Animal("Horse", "horse.jpg"),
                new Animal("Cat", "cat.jpg"),
                new Animal("Elephant", "elephant.jpg")
        );

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        inputField = new JTextField();
        resultLabel = new JLabel("", SwingConstants.CENTER);

        JButton checkButton = new JButton("Check");
        checkButton.addActionListener(e -> checkAnswer());

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> showNextAnimal());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(checkButton);
        bottomPanel.add(nextButton);

        add(imageLabel, BorderLayout.CENTER);
        add(inputField, BorderLayout.NORTH);
        add(resultLabel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.PAGE_END);

        showNextAnimal();
    }

    private void showNextAnimal() {
        int index = (int) (Math.random() * animals.size());
        currentAnimal = animals.get(index);
        URL imageURL = PreschoolTrainer.class.getResource("/resources/" + currentAnimal.imagePath);
        if (imageURL != null) {
            ImageIcon icon = new ImageIcon(imageURL);
            Image scaledImage = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            System.err.println("Image not found: " + currentAnimal.imagePath);
            imageLabel.setIcon(null);
        }
        inputField.setText("");
        resultLabel.setText("");
    }

    private void checkAnswer() {
        String userInput = inputField.getText().trim().toUpperCase();
        if (!userInput.isEmpty() && userInput.charAt(0) == currentAnimal.getFirstLetter()) {
            resultLabel.setText("✅ Correct! " + currentAnimal.name + " starts with " + currentAnimal.getFirstLetter());
        } else {
            resultLabel.setText("❌ Incorrect. Try again!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PreschoolTrainer().setVisible(true));
    }
}

