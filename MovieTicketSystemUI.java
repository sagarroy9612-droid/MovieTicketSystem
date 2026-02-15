
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieTicketSystemUI {
  private static final String url="jdbc:mysql://localhost:3306/MTS";
        private static final String username="root";
    private static final String password="password";


    public static void main(String[] args) {
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException e) {
       JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.INFORMATION_MESSAGE);
      }
        JFrame frame = new JFrame("Movie Ticket System");
        frame.setSize(875, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use CardLayout for switching panels
        CardLayout cardLayout = new CardLayout();
        frame.setLayout(cardLayout);

        // ---------------- LOGIN PANEL ----------------
        JPanel loginPanel = new JPanel(null);
        JLabel loginLabel = new JLabel("Login",new ImageIcon("user.png"),JLabel.CENTER);
        loginLabel.setBounds(0, 20, 900, 50);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 28));
        loginPanel.add(loginLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userLabel.setBounds(250, 150, 100, 30);
        JTextField userField = new JTextField();
        userField.setBounds(350, 150, 200, 30);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passLabel.setBounds(250, 200, 100, 30);
        JPasswordField passField = new JPasswordField();
        passField.setBounds(350, 200, 200, 30);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(400, 250, 100, 30);
          loginBtn.setBackground(new Color(0, 153, 76));
        loginBtn.setForeground(Color.WHITE);
loginBtn.setFocusPainted(false);
loginBtn.setPreferredSize(new Dimension(80, 25));

        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);
        loginPanel.add(loginBtn);

        // ---------------- ADMIN PANEL ----------------
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new BorderLayout());
        JPanel topmenu=new JPanel();
        topmenu.setLayout(new GridLayout(0,5,10,30));
        topmenu.setPreferredSize(new Dimension(0,40));
        topmenu.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        topmenu.setBackground(new Color(33, 150, 243));
        JButton dashboardBtn = new JButton("Dashboard / Home",new ImageIcon("dashboard.png"));
        JButton moviesBtn = new JButton("Movies",new ImageIcon("movie.png"));
        JButton deletebtn=new JButton("Delete Movies",new ImageIcon("deletem.png"));
        JButton bookingsBtn = new JButton("Bookings / Tickets",new ImageIcon("ticket.png"));
        JButton logoutBtn = new JButton("Logout",new ImageIcon("logout.png"));
       JButton[] buttons={dashboardBtn,moviesBtn,deletebtn,bookingsBtn,logoutBtn};
   for (JButton button : buttons) {
     button.setFont(new Font("Aria", Font.BOLD, 16));
            button.setBackground(Color.LIGHT_GRAY);
            button.setForeground(new Color(45, 45, 45));
            button.setFocusPainted(false);
            topmenu.add(button);
   }
     JPanel mainpanel=new JPanel();
        mainpanel.setBackground(Color.WHITE);
        CardLayout cardLayout2=new CardLayout();
        mainpanel.setLayout(cardLayout2);
//------------Dashboard Button-----------
    JPanel dashboardpPanel = new JPanel(new GridLayout(1, 3, 20, 0));
dashboardpPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    JPanel moviesJPanel=new JPanel();
    moviesJPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    moviesJPanel.setPreferredSize(new Dimension(250,350));
    moviesJPanel.setLayout(new BorderLayout());
    moviesJPanel.setBackground(Color.CYAN);
    JLabel moviesJLabel=new JLabel("Total Movies", JLabel.CENTER);
    moviesJLabel.setFont(new Font("Arial", Font.BOLD, 16));
   JLabel moviesValue = new JLabel("Loading...", JLabel.CENTER);

     moviesValue.setFont(new Font("Arial", Font.BOLD, 28));
     moviesJPanel.add(moviesJLabel,BorderLayout.NORTH);
     moviesJPanel.add(moviesValue,BorderLayout.CENTER);
     JPanel bookingsJPanel=new JPanel();
      bookingsJPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    bookingsJPanel.setPreferredSize(new Dimension(250,350));
    bookingsJPanel.setLayout(new BorderLayout());
    bookingsJPanel.setBackground(Color.ORANGE);
    JLabel bookingsJLabel=new JLabel("Total Bookings", JLabel.CENTER);
    bookingsJLabel.setFont(new Font("Arial", Font.BOLD, 16));
     JLabel bookingsvalue=new JLabel("Loading...", JLabel.CENTER);
     bookingsvalue.setFont(new Font("Arial", Font.BOLD, 28));
     bookingsJPanel.add(bookingsJLabel,BorderLayout.NORTH);
     bookingsJPanel.add(bookingsvalue,BorderLayout.CENTER);
     JPanel userJPanel=new JPanel();
      userJPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
     userJPanel.setPreferredSize(new Dimension(250,350));
    userJPanel.setLayout(new BorderLayout());
    userJPanel.setBackground(Color.GREEN);
    JLabel usersJLabel=new JLabel("Total users", JLabel.CENTER);
    usersJLabel.setFont(new Font("Arial", Font.BOLD, 16));
   
JLabel usersValue = new JLabel("Loading...", JLabel.CENTER);

     usersValue.setFont(new Font("Arial", Font.BOLD, 28));
     userJPanel.add(usersJLabel,BorderLayout.NORTH);
     userJPanel.add(usersValue,BorderLayout.CENTER);
     dashboardpPanel.add(moviesJPanel);
     dashboardpPanel.add(bookingsJPanel);
     dashboardpPanel.add(userJPanel);
    // ----------Movies Button---------
         JPanel moviespanel=new JPanel();
      moviespanel.setLayout(new BorderLayout());
      JPanel moviestopJPanel=new JPanel();
      moviestopJPanel.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 5));
      moviestopJPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
      moviestopJPanel.setPreferredSize(new Dimension(0,50));
      JTextField titleField=new JTextField(10);
       JTextField genreField=new JTextField(10);
         JTextField timeField=new JTextField(5);
           JTextField priceField=new JTextField(3);
        JTextField seatsField=new JTextField(10);
        JButton addButton=new JButton("Add");
          addButton.setBackground(new Color(0, 153, 76));
        addButton.setForeground(Color.WHITE);
addButton.setFocusPainted(false);
addButton.setPreferredSize(new Dimension(70, 25));
      
        moviestopJPanel.add(new JLabel("Title"));
        moviestopJPanel.add(titleField);
         moviestopJPanel.add(new JLabel("Genre"));
        moviestopJPanel.add(genreField);
        moviestopJPanel.add(new JLabel("Time"));
        moviestopJPanel.add(timeField);
        moviestopJPanel.add(new JLabel("Price"));
        moviestopJPanel.add(priceField);
         moviestopJPanel.add(new JLabel("seats"));
        moviestopJPanel.add(seatsField);
        moviestopJPanel.add(addButton);
       
     String[] columnnames={"Id","Title","Genre","Time","Price","Seats",};
     DefaultTableModel defaultTableModel=new DefaultTableModel(columnnames,0);
     JTable table=new JTable(defaultTableModel);
     JScrollPane scrollPane=new JScrollPane(table);
         moviespanel.add(scrollPane,BorderLayout.CENTER);

          moviespanel.add(moviestopJPanel,BorderLayout.NORTH);
        // ---------- Delete Button Panel ----------
JPanel deletPanel = new JPanel();
deletPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
deletPanel.setLayout(new BorderLayout());

String[] delcolumnname = { "Id", "Title", "Genre", "Time", "Price", "Seats" };
DefaultTableModel deleteTableModel = new DefaultTableModel(delcolumnname, 0);
JTable delTable = new JTable(deleteTableModel);
JScrollPane delScrollPane = new JScrollPane(delTable);
deletPanel.add(delScrollPane, BorderLayout.CENTER); 

JPanel delbasPanel = new JPanel();
delbasPanel.setPreferredSize(new Dimension(0, 60));
delbasPanel.setLayout(new FlowLayout());


JTextField delidField = new JTextField(10);
JTextField deltitleField = new JTextField(10);
JTextField delgenreField = new JTextField(10);
JTextField deltimeField = new JTextField(5);
JTextField delpriceField = new JTextField(3);
JTextField delseatsField = new JTextField(10);


JButton delButton = new JButton("Delete");
delButton.setBackground(new Color(0, 153, 76));
delButton.setForeground(Color.WHITE);
delButton.setFocusPainted(false);
delButton.setPreferredSize(new Dimension(80, 30));

     
delbasPanel.add(new JLabel("Id"));
delbasPanel.add(delidField);
delbasPanel.add(new JLabel("Title"));
delbasPanel.add(deltitleField);
   delbasPanel.add(new JLabel("Genre"));
delbasPanel.add(delgenreField);
 delbasPanel.add(new JLabel("Time"));
delbasPanel.add(deltimeField);
  delbasPanel.add(new JLabel("Price"));
delbasPanel.add(delpriceField);
    delbasPanel.add(new JLabel("seats"));
delbasPanel.add(delseatsField);
delbasPanel.add(delButton);

deletPanel.add(delbasPanel, BorderLayout.SOUTH);

    // ----------Bookings Button-------
         JPanel bookingspanel=new JPanel();
         bookingspanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
          bookingspanel.setLayout(new BorderLayout());
       String bookingcoloumname[]={"Movie","User","Seats book"};
       DefaultTableModel bookingdefaultTableModel=new DefaultTableModel(bookingcoloumname,0);
       JTable bookingTable=new JTable(bookingdefaultTableModel);
       JScrollPane userJScrollPane=new JScrollPane(bookingTable);
       bookingspanel.add(userJScrollPane,BorderLayout.CENTER);
   // ----------Logout Button-------
         JPanel logoutpanel=new JPanel();
      logoutpanel.add(new JLabel("Hello"));
    //---------------------------------------------------------------------------------------------
        mainpanel.add(dashboardpPanel,"dashboard");
        mainpanel.add(moviespanel,"movies");
        mainpanel.add(deletPanel,"delete");
        mainpanel.add(bookingspanel,"booking");
        mainpanel.add(logoutpanel,"logout");
    //--------------------------------------------------------------------------------------------
        adminPanel.add(mainpanel,BorderLayout.CENTER);
        adminPanel.add(topmenu,BorderLayout.NORTH);
        
        // ---------------- USER PANEL ----------------
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());
        JPanel usertopmenu=new JPanel();
        usertopmenu.setLayout(new GridLayout(0,5,10,30));
        usertopmenu.setPreferredSize(new Dimension(0,40));
        usertopmenu.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        usertopmenu.setBackground(new Color(33, 150, 243));
         JButton movieBtn = new JButton("View Movies",new ImageIcon("movie.png"));
        JButton searchBtn = new JButton("Search Movies",new ImageIcon("search.png"));
        JButton bookBtn = new JButton("Book Tickets",new ImageIcon("ticket.png"));
        JButton myticketsBtn = new JButton("My Bookings",new ImageIcon("cancel.png"));
        JButton logBtn = new JButton("Logout",new ImageIcon("logout.png"));
      JButton[] userbuttons={movieBtn,searchBtn,bookBtn,myticketsBtn,logBtn};
   for (JButton userbutton : userbuttons) {
     userbutton.setFont(new Font("Aria", Font.BOLD, 16));
            userbutton.setBackground(Color.LIGHT_GRAY);
            userbutton.setForeground(new Color(45, 45, 45));
            userbutton.setFocusPainted(false);
            usertopmenu.add(userbutton);
   }
   JPanel userMainpanel=new JPanel();
   CardLayout userCardLayout=new CardLayout();
   userMainpanel.setLayout(userCardLayout);
 // --------------------Movie Button--------------------------
   JPanel moviJPanel=new JPanel();
   moviJPanel.setLayout(new BorderLayout());
String[] userMovieCols = {"ID","Title", "Genre", "Time", "Price", "Available Seats"};
DefaultTableModel userMovieModel = new DefaultTableModel(userMovieCols, 0);
JTable userMovieTable = new JTable(userMovieModel);
JScrollPane userScrollPane=new JScrollPane(userMovieTable);
moviJPanel.add(userScrollPane,BorderLayout.NORTH);

//------------------Search Button-----------------------------
   JPanel searJPanel = new JPanel(new BorderLayout());
JPanel searchTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
JTextField searchField = new JTextField(15);
JButton searchMovieBtn = new JButton("Search");
  searchMovieBtn.setBackground(new Color(0, 153, 76));
        searchMovieBtn.setForeground(Color.WHITE);
searchMovieBtn.setFocusPainted(false);
searchMovieBtn.setPreferredSize(new Dimension(80, 25));
searchTop.add(new JLabel("Search by Title:"));
searchTop.add(searchField);
searchTop.add(searchMovieBtn);
searJPanel.add(searchTop, BorderLayout.NORTH);
DefaultTableModel searchTableModel = new DefaultTableModel(userMovieCols, 0);
JTable searchTable = new JTable(searchTableModel);
searJPanel.add(new JScrollPane(searchTable), BorderLayout.CENTER);

//--------------------Book Button-------------------------

   JPanel bookJPanel = new JPanel();
   bookJPanel.setLayout(new BorderLayout());
JPanel bookTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
JTextField bookTitleField = new JTextField(10);
JTextField seatCountField = new JTextField(5);
JButton confirmBookBtn = new JButton("Confirm Booking");
  confirmBookBtn.setBackground(new Color(0, 153, 76));
        confirmBookBtn.setForeground(Color.WHITE);
confirmBookBtn.setFocusPainted(false);
confirmBookBtn.setPreferredSize(new Dimension(150, 25));
bookTop.add(new JLabel("Movie Title:"));
bookTop.add(bookTitleField);
bookTop.add(new JLabel("Seats:"));
bookTop.add(seatCountField);
bookTop.add(confirmBookBtn);
bookJPanel.add(bookTop, BorderLayout.NORTH);
String[] bookingCols = {"Movie", "Seats"};
DefaultTableModel bookingModel = new DefaultTableModel(bookingCols, 0);
JTable bookingTableUser = new JTable(bookingModel);
JScrollPane bookingJScrollPane=new JScrollPane(bookingTableUser);
bookJPanel.add(bookingJScrollPane, BorderLayout.CENTER);
//--------------------Movie Ticket Button-----------------------
  JPanel myticketJPanel = new JPanel();
  myticketJPanel.setLayout(new BorderLayout());
  JPanel basJPanel=new JPanel();
  basJPanel.setLayout(new FlowLayout());
String[] myBookingsCols = {"Movie", "Seats"};
JTextField cancelmovie=new JTextField(10);
JTextField cancelseats=new JTextField(10);
JButton cancelBookingBtn = new JButton("Cancel Booking");
  cancelBookingBtn.setBackground(new Color(0, 153, 76));
        cancelBookingBtn.setForeground(Color.WHITE);
cancelBookingBtn.setFocusPainted(false);
cancelBookingBtn.setPreferredSize(new Dimension(150, 25));
DefaultTableModel myBookingModel = new DefaultTableModel(myBookingsCols, 0);
JTable myBookingTable = new JTable(myBookingModel);
myticketJPanel.add(new JScrollPane(myBookingTable), BorderLayout.CENTER);
basJPanel.add(cancelmovie);
basJPanel.add(cancelseats);
basJPanel.add(cancelBookingBtn);
myticketJPanel.add(basJPanel, BorderLayout.SOUTH);

// ----------Logout Button------------------------
   JPanel logJPanel=new JPanel();
   logJPanel.add(new JLabel("log panel"));

//-------------------------------------------------------------------------------
   userMainpanel.add(moviJPanel,"Movie");
    userMainpanel.add(searJPanel,"Search");
     userMainpanel.add(bookJPanel,"Book");
      userMainpanel.add(myticketJPanel,"MyTicket");
       userMainpanel.add(logJPanel,"Log");
       
//-------------------------------------------------------------------------
   userPanel.add(usertopmenu,BorderLayout.NORTH);
   userPanel.add(userMainpanel,BorderLayout.CENTER);
        // ---------------- ADD PANELS TO FRAME ----------------
        frame.add(loginPanel, "login");
        frame.add(adminPanel, "admin");
        frame.add(userPanel, "user");

        // ---------------- LOGIN BUTTON ACTION ----------------
       loginBtn.addActionListener(e -> {
    String u = userField.getText();
    String p = new String(passField.getPassword());

    try (Connection conn = DriverManager.getConnection(url, username, password)) {
        String query = "SELECT role FROM users WHERE username=? AND password=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, u);
        ps.setString(2, p);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String role = rs.getString("role");

            if (role.equals("admin")) {
                cardLayout.show(frame.getContentPane(), "admin");
            } else if (role.equals("user")) {
              try {
  Connection conn1=DriverManager.getConnection(url, username, password);
  String query1="Select *from movies";
  PreparedStatement ps1=conn1.prepareStatement(query1);
  ResultSet rs1=ps1.executeQuery();
  userMovieModel.setRowCount(0); 
  while (rs1.next()) {
    Object[] rowdata = {
                        rs1.getInt("id"),
                        rs1.getString("title"),
                        rs1.getString("genre"),
                        rs1.getFloat("time"),
                        rs1.getFloat("price"),
                        rs1.getInt("seats")
                };
  userMovieModel.addRow(rowdata);
  }
} catch (Exception m) {
         JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

}
                cardLayout.show(frame.getContentPane(), "user");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid role in database");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid credentials");
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
});

 //--------------------Admin Button Functionality-------------------------------------------
 dashboardBtn.addActionListener(e -> {
    try {
      Connection conn=DriverManager.getConnection(url, username, password);
      String query="Select count(*)from movies";
      PreparedStatement ps=conn.prepareStatement(query);
      ResultSet rs=ps.executeQuery();
      if (rs.next()) {
        moviesValue.setText(String.valueOf(rs.getInt(1)));
      }
      } 
      
     catch (SQLException m) {
     JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    try {
      Connection conn=DriverManager.getConnection(url, username, password);
      String query="select sum(seats_book)from bookings";
      PreparedStatement ps=conn.prepareStatement(query);
      ResultSet rs=ps.executeQuery();
      if (rs.next()) {
        bookingsvalue.setText(String.valueOf(rs.getInt(1)));
      }
      } 
      
     catch (SQLException m) {
     JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    try {
      Connection conn=DriverManager.getConnection(url, username, password);
      String query="Select count(*)from users";
      PreparedStatement ps=conn.prepareStatement(query);
      ResultSet rs=ps.executeQuery();
      if (rs.next()) {
        usersValue.setText(String.valueOf(rs.getInt(1)));
      }
      } 
      
     catch (SQLException m) {
     JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    cardLayout2.show(mainpanel, "dashboard");
});

        moviesBtn.addActionListener(e -> {
try {
Connection conn=DriverManager.getConnection(url, username, password);
String query="SELECT * FROM movies";
PreparedStatement ps=conn.prepareStatement(query);
ResultSet rs=ps.executeQuery();
  defaultTableModel.setRowCount(0); 
            while (rs.next()) {
                Object[] rowdata = {
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getFloat("time"),
                        rs.getFloat("price"),
                        rs.getInt("seats")
                };
                defaultTableModel.addRow(rowdata);
            }
} catch (Exception m) {
             JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

}
          cardLayout2.show(mainpanel, "movies");
      });
        bookingsBtn.addActionListener(e ->{ 
            try {
      Connection conn=DriverManager.getConnection(url, username, password);
    String query="select *from bookings";
    PreparedStatement ps=conn.prepareStatement(query);
    ResultSet rs=ps.executeQuery();
    bookingdefaultTableModel.setRowCount(0);
while (rs.next()) {
    Object[] row={
        rs.getString("movie"),
        rs.getString("user"),
          rs.getInt("seats_book")
    };
bookingdefaultTableModel.addRow(row);
}
 } catch (Exception m) {
            JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

 }
            
   
            cardLayout2.show(mainpanel, "booking");
    });
    logoutBtn.addActionListener(e -> {
 int option=JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit", null, JOptionPane.YES_NO_OPTION);
        if (option==0) {
        cardLayout.show(frame.getContentPane(), "login");
        }
});
addButton.addActionListener(e -> {
    String Title = titleField.getText();
    String Genre = genreField.getText();
     String Timetext = timeField.getText();
    String Pricetext = priceField.getText();
    String Seatstext = seatsField.getText();
    
if(Title.isEmpty()||Genre.isEmpty()||Timetext.isEmpty()||Pricetext.isEmpty()||Seatstext.isEmpty()){
  JOptionPane.showMessageDialog(frame,"All fields are required!", "Error",JOptionPane.ERROR_MESSAGE);
  return;
}
Float Time;
Float Price;
int Seats;
try {
   Time = Float.parseFloat(Timetext);
     Price = Float.parseFloat(Pricetext);
     Seats = Integer.parseInt(Seatstext);
} catch (NumberFormatException m) {
  JOptionPane.showMessageDialog(frame, "Please enter valid numbers!", "Error",  JOptionPane.ERROR_MESSAGE);
  return;
}
    try (Connection conn = DriverManager.getConnection(url, username, password)) {
      
        String query = "INSERT INTO movies(title, genre, time, price, seats) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, Title);
            ps.setString(2, Genre);
            ps.setFloat(3, Time);
            ps.setFloat(4, Price);
            ps.setInt(5, Seats);

            int rowsaffected = ps.executeUpdate();
            if (rowsaffected > 0) {
                JOptionPane.showMessageDialog(frame, "The Movie Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Some Error! Try Again", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        
        String query1 = "SELECT * FROM movies";
        try (PreparedStatement ps1 = conn.prepareStatement(query1);
             ResultSet rs = ps1.executeQuery()) {

            defaultTableModel.setRowCount(0); 
            while (rs.next()) {
                Object[] rowdata = {
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getFloat("time"),
                        rs.getFloat("price"),
                        rs.getInt("seats")
                };
                defaultTableModel.addRow(rowdata);
            }
        }

        
    } catch (SQLException m) {
        JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
});
deletebtn.addActionListener(e -> {
  try {
Connection conn=DriverManager.getConnection(url, username, password);
String query="SELECT * FROM movies";
PreparedStatement ps=conn.prepareStatement(query);
ResultSet rs=ps.executeQuery();
  deleteTableModel.setRowCount(0); 
            while (rs.next()) {
                Object[] rowdata = {
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getFloat("time"),
                        rs.getFloat("price"),
                        rs.getInt("seats")
                };
                deleteTableModel.addRow(rowdata);
            }
} catch (Exception m) {
             JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

}
    cardLayout2.show(mainpanel, "delete");
});

 delTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
             
      int row=delTable.getSelectedRow();
      if (row!=-1) {
         delidField.setText(deleteTableModel.getValueAt(row, 0).toString());
        deltitleField.setText(deleteTableModel.getValueAt(row, 1).toString());
          delgenreField.setText(deleteTableModel.getValueAt(row, 2).toString());
            deltimeField.setText(deleteTableModel.getValueAt(row, 3).toString());
              delpriceField.setText(deleteTableModel.getValueAt(row, 4).toString());
                delseatsField.setText(deleteTableModel.getValueAt(row, 5).toString());
      }
            }
 });

delButton.addActionListener(e -> {
  
String idText = delidField.getText().trim();
if (idText.isEmpty() || !idText.matches("\\d+")) {
    JOptionPane.showMessageDialog(frame, "Please enter a valid numeric movie ID!", "Validation Error", JOptionPane.WARNING_MESSAGE);
    return;
}
  int id =Integer.parseInt( delidField.getText().trim());

   
    try (Connection conn = DriverManager.getConnection(url, username, password)) {
        String query = "DELETE FROM movies WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);

        int rows = ps.executeUpdate();
        if (rows > 0) {
            JOptionPane.showMessageDialog(frame, "Movie deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            
            String refreshQuery = "SELECT * FROM movies";
            PreparedStatement ps2 = conn.prepareStatement(refreshQuery);
            ResultSet rs = ps2.executeQuery();

            deleteTableModel.setRowCount(0);
            while (rs.next()) {
                Object[] rowdata = {
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getFloat("time"),
                    rs.getFloat("price"),
                    rs.getInt("seats")
                };
                deleteTableModel.addRow(rowdata);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Movie not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
});


 //--------------------USer Button Functionality-------------------------------------------

 movieBtn.addActionListener(e ->{

 userCardLayout.show(userMainpanel,"Movie");
});
        searchBtn.addActionListener(e -> {
        userCardLayout.show(userMainpanel, "Search");
        });
searchMovieBtn.addActionListener(e -> {
    String keyword = searchField.getText().trim();
if (keyword.isEmpty()) {
 JOptionPane.showMessageDialog(frame,"Please enter a keyword to search!",null,JOptionPane.ERROR_MESSAGE);
 return;
}
    try (Connection conn = DriverManager.getConnection(url, username, password)) {
        String query = "SELECT * FROM movies WHERE title LIKE ? OR genre LIKE ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");

        ResultSet rs = ps.executeQuery();

        // clear previous results
        searchTableModel.setRowCount(0);

        while (rs.next()) {
            Object[] rowdata = {
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("genre"),
                rs.getFloat("time"),
                rs.getFloat("price"),
                rs.getInt("seats")
            };
            searchTableModel.addRow(rowdata);
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

});

        bookBtn.addActionListener(e -> {
            userCardLayout.show(userMainpanel, "Book");
        });
        confirmBookBtn.addActionListener(e->{
 String booktitle=bookTitleField.getText();
 String seatstext=seatCountField.getText();
 int seats;
  int availableSeats = 0;
 if (booktitle.trim().isEmpty()|| seatstext.trim().isEmpty()) {
    JOptionPane.showMessageDialog(frame, "All fields are required", null, JOptionPane.ERROR_MESSAGE);
   return;
 }
 try {
    seats=Integer.parseInt(seatCountField.getText());
 } catch (NumberFormatException m) {
   JOptionPane.showMessageDialog(frame, "Please enter valid numbers!", "Error",  JOptionPane.ERROR_MESSAGE);
 return;
 }
try {

Connection conn2=DriverManager.getConnection(url, username, password);
String checkMovieQuery = "SELECT seats FROM movies WHERE title=?";
        PreparedStatement psCheck =conn2.prepareStatement(checkMovieQuery);
        psCheck.setString(1, booktitle);
        ResultSet rsCheck = psCheck.executeQuery();

        if (!rsCheck.next()) {
            JOptionPane.showMessageDialog(frame, "Movie not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int availableSeats2 = rsCheck.getInt("seats");
        if (seats > availableSeats2) {
            JOptionPane.showMessageDialog(frame,"Not enough seats", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
}
  catch (Exception m) {
         JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

 }
 try {
    Connection conn=DriverManager.getConnection(url, username, password);
    String query="select seats from movies where title=?";
    PreparedStatement ps=conn.prepareStatement(query);
   
    ps.setString(1, booktitle);
    ResultSet rs=ps.executeQuery();
    if (rs.next()) {
        availableSeats=rs.getInt(1);
    } 
 } catch (SQLException m) {
   JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
 }
 
 try {
    Connection conn=DriverManager.getConnection(url, username, password);
    String query="update movies set seats=? where title=?";
    PreparedStatement ps=conn.prepareStatement(query);
    ps.setInt(1, availableSeats-seats);
    ps.setString(2, booktitle);
    ps.executeUpdate();
 } catch (SQLException m) {
      JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
 
 }
 try {
     Connection conn=DriverManager.getConnection(url, username, password);
    String query="Insert into bookings(movie,user,seats_book)values(?,?,?)";
    PreparedStatement ps=conn.prepareStatement(query);
    ps.setString(1, booktitle);
    ps.setString(2, userField.getText());
    ps.setInt(3, seats);
    int rowsAffected=ps.executeUpdate();
    if (rowsAffected>0) {
    JOptionPane.showMessageDialog(frame, "Booking successfull","null", JOptionPane.INFORMATION_MESSAGE);
    }
 } catch (Exception m) {
         JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

 }try {
      Connection conn=DriverManager.getConnection(url, username, password);
    String query="select *from bookings";
    PreparedStatement ps=conn.prepareStatement(query);
    ResultSet rs=ps.executeQuery();
    bookingModel.setRowCount(0);
while (rs.next()) {
    Object[] row={
        rs.getString("movie"),
          rs.getString("seats_book")
    };
bookingModel.addRow(row);
}
 } catch (Exception m) {
            JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

 }
   try {
  Connection conn1=DriverManager.getConnection(url, username, password);
  String query1="Select *from movies";
  PreparedStatement ps1=conn1.prepareStatement(query1);
  ResultSet rs1=ps1.executeQuery();
  userMovieModel.setRowCount(0); 
  while (rs1.next()) {
    Object[] rowdata = {
                        rs1.getInt("id"),
                        rs1.getString("title"),
                        rs1.getString("genre"),
                        rs1.getFloat("time"),
                        rs1.getFloat("price"),
                        rs1.getInt("seats")
                };
  userMovieModel.addRow(rowdata);
  }
} catch (Exception m) {
         JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

}
             });
myticketsBtn.addActionListener(e -> {
try {
  Connection conn=DriverManager.getConnection(url, username, password);
  String query="select movie,seats_book from bookings";
PreparedStatement ps=conn.prepareStatement(query);
ResultSet rs=ps.executeQuery();
myBookingModel.setRowCount(0);
while (rs.next()) {
  Object[] row={
  rs.getString("movie"),
  rs.getInt("seats_book")
};
myBookingModel.addRow(row);
}
}catch (Exception m) {
           JOptionPane.showMessageDialog(frame, m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

}
  userCardLayout.show(userMainpanel, "MyTicket");
});
myBookingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
              int row=myBookingTable.getSelectedRow();
              if (row!=-1) {
                cancelmovie.setText(myBookingModel.getValueAt(row,0).toString());
                cancelseats.setText(myBookingModel.getValueAt(row,1).toString());
              }
            }
            });
cancelBookingBtn.addActionListener(e -> {
    String movie = cancelmovie.getText();
    int seats = Integer.parseInt(cancelseats.getText());

    try (Connection conn = DriverManager.getConnection(url, username, password)) {

        // 1. Delete the booking
        String deleteQuery = "DELETE FROM bookings WHERE movie=? AND seats_book=?";
        PreparedStatement psDelete = conn.prepareStatement(deleteQuery);
        psDelete.setString(1, movie);
        psDelete.setInt(2, seats);
        int rowsAffected = psDelete.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(frame, "Cancel Booking Successfully", null, JOptionPane.INFORMATION_MESSAGE);

            // 2. Update seats in movies table
            String updateQuery = "UPDATE movies SET seats = seats + ? WHERE title=?";
            PreparedStatement psUpdate = conn.prepareStatement(updateQuery);
            psUpdate.setInt(1, seats);
            psUpdate.setString(2, movie);
            psUpdate.executeUpdate();

            // 3. Refresh bookings table
            String refreshQuery = "SELECT movie, seats_book FROM bookings";
            PreparedStatement psRefresh = conn.prepareStatement(refreshQuery);
            ResultSet rs = psRefresh.executeQuery();

            myBookingModel.setRowCount(0);
            while (rs.next()) {
                Object[] row = { rs.getString("movie"), rs.getInt("seats_book") };
                myBookingModel.addRow(row);
            }
            String query1 = "SELECT * FROM movies";
        PreparedStatement ps1 = conn.prepareStatement(query1);
             ResultSet rs1 = ps1.executeQuery();

            userMovieModel.setRowCount(0); 
            while (rs1.next()) {
                Object[] rowdata = {
                        rs1.getInt("id"),
                        rs1.getString("title"),
                        rs1.getString("genre"),
                        rs1.getFloat("time"),
                        rs1.getFloat("price"),
                        rs1.getInt("seats")
                };
                userMovieModel.addRow(rowdata);
            }
        

        } else {
            JOptionPane.showMessageDialog(frame, "No booking found to cancel!", null, JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
});

       logBtn.addActionListener(e -> {
        int option=JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit", null, JOptionPane.YES_NO_OPTION);
        if (option==0) {
        cardLayout.show(frame.getContentPane(), "login");
        }
       });


        frame.setVisible(true);
    }
}
