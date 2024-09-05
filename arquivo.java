import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.io.BufferedReader; // Add this import statement

public class arquivo {
    public static void user_saveCsv(User user, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String[] userData = user.toArray();
            writer.write(String.join(",", userData));
            writer.newLine();
            System.out.println("User data saved to CSV file."); //LOG
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int nextId(String fileName){
        String lastLine = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            reader.readLine(); //To skip first line information
            String line;
            while((line = reader.readLine()) != null){
                lastLine = line;
            }
        } catch (IOException e) { //Erro to read file
            System.out.println("Error to read file");
            e.printStackTrace();
        }
        if(!lastLine.isEmpty()){
            String[] data = lastLine.split(",");
            int lastId=Integer.parseInt(data[0]); //Id is in first column
            //System.out.println("here"+lastId); //LOG 
            return lastId+1;
        }else{
            return 1; //FIle is empty and will be started with 1
        }
    }

    public static User GetUser(String fileName, int id){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            reader.readLine(); //To skip first line information
            String line;
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                if(id == Integer.parseInt(data[0])){
                    User user = new User(Integer.parseInt(data[0]),data[1],Integer.parseInt(data[2]), data[3], data[4], data[5], data[6], data[7]);
                    return user;
                }
            }
        } catch (IOException e) { //Erro to read file
            System.out.println("Error to read file");
            e.printStackTrace();
        }return null;
    }
    
    
    // I was need this methot to hash all passwords in the file
    // I implemented this method to hash my old file passwords
    public static void hashPassCsv(int size){
        String fileName = "userHash.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            for (int i = 1 ; i <= size ; i++){
                User user = GetUser("user.csv",i);
                if (user != null) {
                    String userPass = user.getPass();
                    String HashPass = "";
                    try {
                        HashPass = hashing.hashString(userPass); //hashing last password
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    //Update user password to the hashed password
                    user.SetPass(HashPass);
                    user_saveCsv(user,fileName);
                } else {
                    System.out.println("User with id " + i + " not found.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}