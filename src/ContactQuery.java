
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ContactQuery {

    public boolean insertContact(Contact cont) {
        boolean contactIsCreated = true;
        Connection con = myConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO `contacts`(`fname`, `lname`, `groupc`, `phone`, `email`, `address`, `pic`, `userid`) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, cont.getFname());
            ps.setString(2, cont.getLname());
            ps.setString(3, cont.getGroupc());
            ps.setString(4, cont.getPhone());
            ps.setString(5, cont.getEmail());
            ps.setString(6, cont.getAddress());
            ps.setBytes(7, cont.getPic());
            ps.setInt(8, cont.getUid());

            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "New Contact Added");
                contactIsCreated = true;
            } else {
                JOptionPane.showMessageDialog(null, "Something Wrong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contactIsCreated;
    }

    public void updateContact(Contact cont, boolean withImage) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        String updateQuery = "";

        if (withImage == true) {
            updateQuery = "UPDATE `contacts` SET `fname`=?,`lname`=?,`groupc`=?,`phone`=?,`email`=?,`address`=?,`pic`=?,`userid`=? WHERE `id`=?";

            try {
                ps = con.prepareStatement(updateQuery);
                ps.setString(1, cont.getFname());
                ps.setString(2, cont.getLname());
                ps.setString(3, cont.getGroupc());
                ps.setString(4, cont.getPhone());
                ps.setString(5, cont.getEmail());
                ps.setString(6, cont.getAddress());
                ps.setBytes(7, cont.getPic());
                ps.setInt(8, cont.getUid());
                ps.setInt(9, cont.getCid());

                if (ps.executeUpdate() != 0) {
                    JOptionPane.showMessageDialog(null, "Contact Data Edited");
                } else {
                    JOptionPane.showMessageDialog(null, "Something Wrong");
                }
            } catch (SQLException ex) {
                Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            updateQuery = "UPDATE `contacts` SET `fname`=?,`lname`=?,`groupc`=?,`phone`=?,`email`=?,`address`=?,`userid`=? WHERE `id`=?";

            try {
                ps = con.prepareStatement(updateQuery);
                ps.setString(1, cont.getFname());
                ps.setString(2, cont.getLname());
                ps.setString(3, cont.getGroupc());
                ps.setString(4, cont.getPhone());
                ps.setString(5, cont.getEmail());
                ps.setString(6, cont.getAddress());
                ps.setInt(7, cont.getUid());
                ps.setInt(8, cont.getCid());

                if (ps.executeUpdate() != 0) {
                    JOptionPane.showMessageDialog(null, "Contact Data Edited");
                } else {
                    JOptionPane.showMessageDialog(null, "Something Wrong");
                }
            } catch (SQLException ex) {
                Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

    public void deleteContact(int id) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("DELETE FROM `contacts` WHERE `id` = ?");
            ps.setInt(1, id);

            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Contact Deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Something Wrong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Contact> contactList(int userId) {
        ArrayList<Contact> contactList = new ArrayList<Contact>();

        Connection con = myConnection.getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT `id`, `fname`, `lname`, `groupc`, `phone`, `email`, `address`, `pic`, `userid` FROM `contacts` where userid = " + userId);

            Contact ct;

            while (rs.next()) {
                ct = new Contact(
                        rs.getInt("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("groupc"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getBytes("pic"),
                        0
                );
                contactList.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return contactList;
    }
}
