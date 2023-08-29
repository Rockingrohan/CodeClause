
import java.sql.*;
import java.util.Random;

public class Randompassword{
    public static void main(String[] args) {
        generatePassword(8);
    }

    public static void generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] generatepassword = new char[length];

        generatepassword[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        generatepassword[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        generatepassword[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        generatepassword[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< length ; i++) {
            generatepassword[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        StringBuilder pass= new StringBuilder();
        for(int i = 0; i< 8; i++) {
            pass.append(generatepassword[i]);
        }

        //connecting to xampp server
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/psw?characterEncoding=latin1","root","");

            System.out.println("connected");
            System.out.println(generatepassword);

            String sql = "INSERT INTO psd VALUES (?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, pass.toString());
            stmt.executeUpdate();
            System.out.println("done");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
