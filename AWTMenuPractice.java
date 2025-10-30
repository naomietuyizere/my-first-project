import java.awt.*;
import java.awt.event.*;

public class AWTMenuPractice extends Frame implements ActionListener {

    private static final String FULL_NAME = "Tuyizere Naomie";
    private static final String REG_NUMBER = "24RP00900";
    private static final String APP_TITLE = "AWT MENU Practice: " + REG_NUMBER;

    private static final Color DARK_BLUE = new Color(10, 40, 70);
    private static final Color LIGHT_BLUE = new Color(200, 220, 240);
    private static final Color CREAM_BEIGE = new Color(245, 245, 230);
    private static final Color DARK_SLATE_BLUE = new Color(40, 70, 90);
    private static final Color ORANGE_BROWN = new Color(180, 80, 10);

    private CardLayout cardLayout;
    private Panel contentPanel;

    private static final String LOGIN_PAGE = "LoginCard";
    private static final String STUDENT_PAGE = "StudentCard";

    public AWTMenuPractice() {
        setTitle(APP_TITLE);
        setSize(750, 500);
        setLayout(new BorderLayout());

        setMenuBar(createMenuBar());

        cardLayout = new CardLayout();
        contentPanel = new Panel(cardLayout);

        contentPanel.add(createLoginPage(), LOGIN_PAGE);
        contentPanel.add(createStudentPage(), STUDENT_PAGE);

        cardLayout.show(contentPanel, LOGIN_PAGE);
        add(contentPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu pagesMenu = new Menu("Pages");

        MenuItem loginItem = new MenuItem("Login");
        loginItem.setActionCommand("LOGIN_PAGE");
        loginItem.addActionListener(this);
        pagesMenu.add(loginItem);

        MenuItem studentItem = new MenuItem("Student");
        studentItem.setActionCommand("STUDENT_PAGE");
        studentItem.addActionListener(this);
        pagesMenu.add(studentItem);

        menuBar.add(pagesMenu);
        menuBar.add(new Menu("Edit"));
        menuBar.add(new Menu("Help"));

        return menuBar;
    }

    private Panel createLoginPage() {
        Panel loginContainer = new Panel(new BorderLayout());

        Panel titlePanel = new Panel(new BorderLayout());
        titlePanel.setBackground(DARK_BLUE);
        titlePanel.setPreferredSize(new Dimension(0, 60));

        Label titleLabel = new Label("SIMPLE JAVA AWT LAYOUT", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        loginContainer.add(titlePanel, BorderLayout.NORTH);

        Panel contentPanel = new Panel(new BorderLayout());

        Panel leftNavArea = new Panel(new BorderLayout());
        leftNavArea.setBackground(LIGHT_BLUE);
        leftNavArea.setPreferredSize(new Dimension(150, 0));

        Panel buttonMenu = new Panel(new GridLayout(2, 1, 0, 5));

        Button loginButtonSidebar = new Button("login");
        loginButtonSidebar.setFont(new Font("Arial", Font.BOLD, 14));
        loginButtonSidebar.setBackground(ORANGE_BROWN);
        loginButtonSidebar.setForeground(Color.WHITE);
        loginButtonSidebar.addActionListener(e -> cardLayout.show(this.contentPanel, LOGIN_PAGE));

        Button studentButton = new Button("student");
        studentButton.setFont(new Font("Arial", Font.BOLD, 14));
        studentButton.setBackground(ORANGE_BROWN.darker());
        studentButton.setForeground(Color.WHITE);
        studentButton.addActionListener(e -> cardLayout.show(this.contentPanel, STUDENT_PAGE));

        buttonMenu.add(loginButtonSidebar);
        buttonMenu.add(studentButton);

        leftNavArea.add(buttonMenu, BorderLayout.NORTH);
        contentPanel.add(leftNavArea, BorderLayout.WEST);

        Panel rightPanel = new Panel(new BorderLayout(20, 20));
        rightPanel.setBackground(CREAM_BEIGE);

        Label loginTitle = new Label("LOGIN PAGE", Label.CENTER);
        loginTitle.setFont(new Font("Arial", Font.BOLD, 18));
        rightPanel.add(loginTitle, BorderLayout.NORTH);

        Panel formPanel = new Panel(new GridBagLayout());
        formPanel.setBackground(CREAM_BEIGE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new Label("Username", Label.RIGHT), gbc);
        gbc.gridx = 1;
        formPanel.add(new TextField(15), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new Label("Password", Label.RIGHT), gbc);
        gbc.gridx = 1;
        TextField passwordField = new TextField(15);
        passwordField.setEchoChar('*');
        formPanel.add(passwordField, gbc);

        Button loginButton = new Button("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(DARK_SLATE_BLUE);
        loginButton.setForeground(Color.WHITE);

        loginButton.addActionListener(e -> {
            Dialog messageDialog = new Dialog(this, "Status", true);
            messageDialog.setLayout(new FlowLayout());
            messageDialog.setSize(250, 100);
            messageDialog.add(new Label("Login button clicked. Validation skipped."));
            Button ok = new Button("OK");
            ok.addActionListener(event -> messageDialog.dispose());
            messageDialog.add(ok);
            messageDialog.setLocationRelativeTo(this);
            messageDialog.setVisible(true);
        });

        Panel buttonWrapper = new Panel(new FlowLayout(FlowLayout.RIGHT));
        buttonWrapper.setBackground(CREAM_BEIGE);
        buttonWrapper.add(loginButton);

        rightPanel.add(formPanel, BorderLayout.CENTER);
        rightPanel.add(buttonWrapper, BorderLayout.SOUTH);

        contentPanel.add(rightPanel, BorderLayout.CENTER);
        loginContainer.add(contentPanel, BorderLayout.CENTER);

        return loginContainer;
    }

    private Panel createStudentPage() {
        Panel studentPanel = new Panel(new GridBagLayout());
        studentPanel.setBackground(LIGHT_BLUE.brighter());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);

        Label header = new Label("Student Information", Label.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridy = 0;
        studentPanel.add(header, gbc);

        Label nameLabel = new Label("Full Name: " + FULL_NAME, Label.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy = 1;
        studentPanel.add(nameLabel, gbc);

        Label regLabel = new Label("Registration Number: " + REG_NUMBER, Label.CENTER);
        regLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy = 2;
        studentPanel.add(regLabel, gbc);

        return studentPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("LOGIN_PAGE")) {
            cardLayout.show(contentPanel, LOGIN_PAGE);
        } else if (command.equals("STUDENT_PAGE")) {
            cardLayout.show(contentPanel, STUDENT_PAGE);
        }
    }

    public static void main(String[] args) {
        new AWTMenuPractice();
    }
}
