/********************************

 Developed By: Vedant Desai
 e-mail: vedantdesai767@gmail.com
 ig: vedant_767

********************************/
import java.util.*;
import java.lang.*;
import java.io.*;
class phoneBookOperations{
    String reset = "\u001B[0m", red = "\u001B[31m", green = "\u001B[32m";
    String yellow = "\u001B[33m", blue = "\u001B[34m", bg = "\u001B[46m";
    String bgY = "\u001B[43m", black = "\u001B[31m", bold = "\u001B[1m";
    Vector<String> phoneBook = new Vector<>();
    Vector<String> name = new Vector<>();
    File csvfile = new File("phonebook.csv");
    Scanner sc = new Scanner(System.in);
    void findNumber(){
        if(phoneBook.size() == 0){
            System.out.println(red + "Phonebook is empty" + reset);
        } else {
            System.out.println(yellow + "Enter a number or name to search in the phonebook: " + reset);
            String ph = sc.nextLine();
            for (int i = 0; i < phoneBook.size(); i++) {
                if (ph.equals(phoneBook.get(i)) || ph.equals(name.get(i))) {
                    System.out.println(green + "Phone Number Found" + reset);
                    delay(100);
                    System.out.println(blue + "Name: " + name.get(i));
                    delay(100);
                    System.out.println("Number: " + phoneBook.get(i) + reset);
                    delay(100);
                    break;
                } else {
                    System.out.println(red + "Phone number not found" + reset);
                }
            }
        }
        System.out.println();
    }

    void modifyNumber() {
        if(phoneBook.size() == 0){
            System.out.println(red + "Phonebook is empty" + reset);
        } else {
            boolean mod = true;
            while (mod) {
                System.out.println(yellow + "What do you want to modify? " + reset);
                System.out.println(blue + "1. Number" + reset);
                System.out.println(blue + "2. Name" + reset);
                int ch = sc.nextInt();

                sc.nextLine();

                if (ch == 1) {
                    System.out.println(yellow + "Enter the number to be modified: " + reset);
                    String ph = sc.nextLine();
                    for (int i = 0; i < phoneBook.size(); i++) {
                        if (ph.equals(phoneBook.get(i))) {
                            System.out.println(yellow + "Enter new number: " + reset);
                            String newPh = sc.nextLine();
                            phoneBook.set(i, newPh);
                            System.out.println(green + "Phone number modified successfully" + reset);
                            break;
                        } else {
                            System.out.println(red + "Phone number not found" + reset);
                        }
                    }
                    mod = false;
                } else if (ch == 2) {
                    System.out.println(yellow + "Enter the name to be modified: " + reset);
                    String nm = sc.nextLine();
                    for (int i = 0; i < name.size(); i++) {
                        if (nm.equals(name.get(i))) {
                            System.out.println(yellow + "Enter new name: " + reset);
                            String newNm = sc.nextLine();
                            name.set(i, newNm);
                            System.out.println(green + "Name modified successfully" + reset);
                            break;
                        } else {
                            System.out.println(red + "Name not found" + reset);
                        }
                    }
                    mod = false;
                } else {
                    System.out.println(red + "Invalid input" + reset);
                }
            }
        }
    }

    void writeNumber(){
        String temp = "null";
        boolean numberLength = true;
        System.out.println(yellow + "Enter name of the contact: " + reset);
        String ctName = sc.nextLine();
        name.add(ctName);
        while(numberLength) {
            if (temp.length() != 10) {
                System.out.println("Phone number should contain 10 digits");
                System.out.println(yellow + "Enter a new number: " + reset);
                temp = sc.nextLine();
            }else{
                phoneBook.add(temp);
                numberLength = false;
            }
        }
        System.out.println(green + "Phone number added to list" + reset);
        System.out.println();
    }

    void deleteNumber(){
        if(phoneBook.size() == 0){
            System.out.println("Phonebook is empty");
        } else {
            System.out.println(yellow + "Enter a number or name to be deleted: " + reset);
            String num = sc.nextLine();
            for (int i = 0; i < phoneBook.size(); i++) {
                if (num.equals(phoneBook.get(i)) || num.equals(name.get(i))) {
                    phoneBook.remove(i);
                    name.remove(i);
                    System.out.println(green + "Phone number deleted from list successfully" + reset);
                    break;
                } else {
                    System.out.println(red + "Phone number not found" + reset);
                }
            }
        }
        System.out.println();
    }

    void displayHelp(){
        System.out.println();
        System.out.println(blue + "********** Command Details **********" + reset);
        System.out.println(blue + "/write: Insert a new contact to the phonebook" + reset);
        try { Thread.sleep(500); }catch(InterruptedException e){ System.out.println();}
        System.out.println(blue + "/find: Search for an existing contact in the phonebook" + reset);
        try { Thread.sleep(500); }catch(InterruptedException e){ System.out.println();}
        System.out.println(blue + "/modify: modify an existing contact in the phonebook" + reset);
        try { Thread.sleep(500); }catch(InterruptedException e){ System.out.println();}
        System.out.println(blue + "/delete: Delete a contact from phonebook" + reset);
        try { Thread.sleep(500); }catch(InterruptedException e){ System.out.println();}
        System.out.println(blue + "/writeToFile: Write contacts data to .csv (comma separated values) file" + reset);
        try { Thread.sleep(500); }catch(InterruptedException e){ System.out.println();}
        System.out.println(blue + "*Note: All commands are case sensitive" + reset);
        try { Thread.sleep(500); }catch(InterruptedException e){ System.out.println();}
        System.out.println(blue + "*************************************" + reset);
        try { Thread.sleep(500); }catch(InterruptedException e){ System.out.println();}
        System.out.println();
    }
    static void delay(int sec){
        try {
            Thread.sleep(sec);
        }catch(InterruptedException e){
            System.out.println();
        }
    }

    void writeDataToFile(boolean lengthCheck){
        try {
            boolean fileLength = true;
            if(csvfile.length() != 0 && lengthCheck){
                fileLength = false;
            }
            FileWriter writer = new FileWriter(csvfile, fileLength);

            if(csvfile.length() == 0){
                writer.write("Name" + "," + "Phone Number" + "\n");
            }

            for(int i = 0; i < phoneBook.size(); i++){
                writer.write(name.get(i) + "," + phoneBook.get(i) + "\n");
            }

            writer.close();
            System.out.println("Data Written to file");
            System.out.println();
        } catch(IOException e){
            System.out.println("Error while writing data into file");
        }
    }
}

class developer extends phoneBookOperations{
    void devCommands(evaluator obj){
        System.out.println(bg + black + bold + "******* Developer Commands *******" + reset);
        delay(500);
        System.out.println(bg + black + bold + "/display: to display all contacts " + reset);
        delay(500);
        System.out.println(bg + black + bold + "/del: to delete whole data        " + reset);
        delay(500);
        System.out.println(bg + black + bold + "/delete: to delete the file       " + reset);
        delay(500);
        System.out.println(bg + black + bold + "/quit: to log out                 " + reset);
        delay(500);
        boolean devRes = true;
        while(devRes) {
            System.out.print(bgY + black + bold + "Developer's response: " + reset);
            String devResp = sc.nextLine();
            System.out.println();
            if(devResp.equals("/display")){
                obj.display();
            } else if(devResp.equals("/del")){
                phoneBook.clear();
                name.clear();
            } else if (devResp.equals("/delete")) {
                if(csvfile.length() == 0){
                    System.out.println("File is empty");
                }else {
                    csvfile.delete();
                }
            } else if (devResp.equals("/quit")) {
                System.out.println("Developer logged out successfully");
                System.out.println();
                delay(500);
                devRes = false;
            } else {
                System.out.println(red + "Incorrect Command" + reset);
            }

        }
    }
    void devLogin(evaluator obj){
        boolean dev = true;
        int count = 0;
        while(dev) {
            System.out.println(yellow + "Enter password for login: " + reset);
            String key = sc.nextLine();
            if (key.equals("javac")) {
                System.out.println();
                System.out.println(green + "Login Successful..." + reset);
                delay(1000);
                obj.devCommands(obj);
                dev = false;
            } else {
                delay(100);
                System.out.println(red + "Incorrect password" + reset);
                count++;
                if (count >= 5) {
                    System.out.println();
                    System.out.println(red + "5 Incorrect attempts" + reset);
                    delay(2000);
                    System.out.println(green + "Hint: Name of java compiler" + reset);
                }
            }
        }
    }
    void display(){
        System.out.println();
        System.out.println(blue + "Name        " + "Contact   " + reset);
        for(int i = 0; i < phoneBook.size(); i++){
            System.out.println(name.get(i) + "     " + phoneBook.get(i));
        }
        System.out.println();
    }
}

class evaluator extends developer{
    boolean commandEvaluator(String str, evaluator obj){
        if(str.equals("/write")){
            obj.writeNumber();
        } else if (str.equals("/find")) {
            obj.findNumber();
        } else if (str.equals("/modify")) {
            obj.modifyNumber();
        } else if (str.equals("/delete")) {
            obj.deleteNumber();
        } else if (str.equals("/writeToFile")){
            boolean dataWritten = true;
            obj.writeDataToFile(dataWritten);
        } else if (str.equals("/dev")) {
            obj.devLogin(obj);
        } else if (str.equals("help")) {
            obj.displayHelp();
        } else if (str.equals("/exit")) {
            System.out.print("Exiting program");
            delay(200);
            System.out.print(".");
            delay(200);
            System.out.print(".");
            delay(200);
            System.out.print(".");
            return false;
        } else {
            System.out.println(red + "Invalid command" + reset);
            return true;
        }
        return true;
    }
}

public class phoneBookBot {
    public static void main(String[] args) {
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String blue = "\u001B[34m";
        evaluator obj = new evaluator();
        Scanner sc = new Scanner(System.in);
        String cmd, choice;
        boolean cnt = true, cmdEvaluator = true;
        while(cnt) {
            System.out.println(blue + "Enter /start to start" + reset);
            cmd = sc.nextLine();
            if (cmd.equals("/start")) {
                System.out.println(green + "Welcome to PhoneBook Bot..." + reset);
                phoneBookOperations.delay(500);
                System.out.println();
                System.out.println(yellow + "Command List: " + reset);
                phoneBookOperations.delay(500);
                System.out.println(blue + "/write");
                phoneBookOperations.delay(500);
                System.out.println("/find");
                phoneBookOperations.delay(500);
                System.out.println("/modify");
                phoneBookOperations.delay(500);
                System.out.println("/delete");
                phoneBookOperations.delay(500);
                System.out.println("/writeToFile");
                phoneBookOperations.delay(500);
                System.out.println("/exit" + reset);
                phoneBookOperations.delay(500);
                System.out.println(green + "Type 'help' to know the command details" + reset);
                phoneBookOperations.delay(500);
                System.out.println(green + "Type /dev for developer login" + reset);
                phoneBookOperations.delay(500);

                while (cmdEvaluator) {
                    try {
                        Thread.sleep(500);
                        System.out.print(yellow + "User's response: " + reset);
                        choice = sc.nextLine();
                        cmdEvaluator = obj.commandEvaluator(choice, obj);
                    }catch(InterruptedException e){
                        System.out.println();
                    }
                }

            cmdEvaluator = true;
            cnt = false;
            } else {
                System.out.println(red + "Incorrect command" + reset);
            }
        }
    }
}