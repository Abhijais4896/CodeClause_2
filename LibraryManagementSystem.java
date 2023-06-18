import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystem {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField yearField;
    private JButton addButton;
    private JButton displayButton;

    private List<Book> books;

    public LibraryManagementSystem() {
        books = new ArrayList<>();

        // Create and configure the frame
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        // Create the text area for displaying book information
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Create the input fields for adding a book
        titleField = new JTextField();
        authorField = new JTextField();
        yearField = new JTextField();

        // Create the "Add" button
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                int year = Integer.parseInt(yearField.getText());

                addBook(title, author, year);
                clearFields();
            }
        });

        // Create the "Display" button
        displayButton = new JButton("Display");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBooks();
            }
        });

        // Create the panel for input fields and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Year:"));
        inputPanel.add(yearField);
        inputPanel.add(addButton);
        inputPanel.add(displayButton);

        // Add the components to the frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);
    }

    private void addBook(String title, String author, int year) {
        Book book = new Book(title, author, year);
        books.add(book);
    }

    private void displayBooks() {
        textArea.setText(""); // Clear the existing text

        for (Book book : books) {
            textArea.append("Title: " + book.getTitle() + "\n");
            textArea.append("Author: " + book.getAuthor() + "\n");
            textArea.append("Year: " + book.getYear() + "\n");
            textArea.append("--------------------\n");
        }
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        yearField.setText("");
    }

    public static void main(String[] args) {
        // Run the GUI in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new LibraryManagementSystem();
        });
    }
}
