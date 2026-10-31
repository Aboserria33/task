import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class User {
    private final String username;
    private final String password;
    private final String role;

    User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    String getUsername() { return username; }
    String getPassword() { return password; }
    String getRole() { return role; }
}

class WelcomePage extends Frame {
    WelcomePage(User user) {
        super("User Home");
        setLayout(new GridLayout(2, 1, 8, 8));
        add(new Label("Hello, " + user.getUsername(), Label.CENTER));
        add(new Label("Your role is: " + user.getRole(), Label.CENTER));
        setSize(320, 150);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { dispose(); }
        });
    }
}

public class LoginFormTask extends Frame {
    private final List<User> users = new ArrayList<>();
    private int wrongPasswordCount = 0;
    private final TextField usernameField = new TextField(18);
    private final TextField passwordField = new TextField(18);
    private final Choice roleChoice = new Choice();
    private final Button loginButton = new Button("Login");

    public LoginFormTask() {
        super("Login Form");
        loadUsers();

        roleChoice.add("Student");
        roleChoice.add("TA");
        roleChoice.add("Professor");
        passwordField.setEchoChar('*');

        Panel fields = new Panel(new GridLayout(3, 2, 8, 8));
        fields.add(new Label("Username:", Label.RIGHT));
        fields.add(usernameField);
        fields.add(new Label("Password:", Label.RIGHT));
        fields.add(passwordField);
        fields.add(new Label("Role:", Label.RIGHT));
        fields.add(roleChoice);

        Label heading = new Label("Welcome back", Label.CENTER);
        heading.setFont(new Font("SansSerif", Font.BOLD, 20));

        Button cancelButton = new Button("Cancel");
        Panel actions = new Panel(new FlowLayout());
        actions.add(cancelButton);
        actions.add(loginButton);

        Panel mainPanel = new Panel(new BorderLayout(10, 14));
        mainPanel.add(heading, BorderLayout.NORTH);
        mainPanel.add(fields, BorderLayout.CENTER);
        mainPanel.add(actions, BorderLayout.SOUTH);

        setLayout(new GridBagLayout());
        add(mainPanel);

        loginButton.addActionListener(e -> attemptLogin());
        cancelButton.addActionListener(e -> dispose());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { dispose(); }
        });

        setSize(420, 340);
        setLocationRelativeTo(null);
    }

    private void attemptLogin() {
        String name = usernameField.getText().trim();
        String pass = passwordField.getText();

        if (name.isEmpty() || pass.isEmpty()) {
            showMessage("Username and password cannot be empty.");
            return;
        }

        User user = findUser(name);
        if (user == null) {
            showMessage("User not found.");
            return;
        }

        if (!user.getPassword().equals(pass)) {
            wrongPasswordCount++;
            int remaining = 3 - wrongPasswordCount;
            if (remaining <= 0) {
                loginButton.setEnabled(false);
                showMessage("Too many wrong passwords. Login is now disabled.");
            } else {
                showMessage("Wrong password. You have " + remaining + " attempt(s) left.");
            }
            return;
        }

        if (!user.getRole().equals(roleChoice.getSelectedItem())) {
            showMessage("Please select the correct role for this user.");
            return;
        }

        new WelcomePage(user).setVisible(true);
        dispose();
    }

    private void showMessage(String message) {
        Dialog messageBox = new Dialog(this, "Message", true);
        messageBox.setLayout(new BorderLayout(10, 10));
        messageBox.add(new Label(message, Label.CENTER), BorderLayout.CENTER);

        Button okButton = new Button("OK");
        okButton.addActionListener(e -> messageBox.dispose());
        Panel buttonPanel = new Panel();
        buttonPanel.add(okButton);
        messageBox.add(buttonPanel, BorderLayout.SOUTH);

        messageBox.setSize(340, 130);
        messageBox.setLocationRelativeTo(this);
        messageBox.setVisible(true);
    }

    private void loadUsers() {
        users.add(new User("student1", "pass123", "Student"));
        users.add(new User("ta1", "ta123", "TA"));
        users.add(new User("prof1", "prof123", "Professor"));
    }

    private User findUser(String name) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equalsIgnoreCase(name)) {
                return currentUser;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new LoginFormTask().setVisible(true));
    }
}
