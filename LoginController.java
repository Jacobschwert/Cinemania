import java.util.Scanner;

public class LoginController {

    public Account login() {
        String uName;
        String password;
        Scanner loginInfo = new Scanner(System.in); 

        System.out.print("Enter Username: ");
        uName = loginInfo.nextLine();
        System.out.print("Enter Password: ");
        password = loginInfo.nextLine();
        loginInfo.close();
        Account loginAcct = new Account(uName, password);
        if(loginAcct.getAccountNumber() == 0){
            return null;
        }
        else{
            return loginAcct;
        }
    }

    public Account logout() {
        System.out.println("You have logged out.");
        return null;
    }

    public Account signup() {
        String uName;
        String password;
        String email;
        String desc;
        
        Scanner signupInfo = new Scanner(System.in);

        System.out.print("Enter Username: ");
        uName = signupInfo.nextLine();
        System.out.print("Enter Password: ");
        password = signupInfo.nextLine();
        System.out.print("Enter Email: ");
        email = signupInfo.nextLine();
        System.out.print("Enter Description: ");
        desc = signupInfo.nextLine();
        signupInfo.close();
        Account signupAcct = new Account(uName, email, desc, password);
        return signupAcct;
    }

    public void editAccount(Account accountEdit){//Do stuff here
        String uName;
        String password;
        String email;
        String desc;
        String tempToDelete;
        Boolean toDelete = false;
        
        Scanner editInfo = new Scanner(System.in);

        System.out.println("Enter 'n' if you don't want to change a value.");
        System.out.print("Enter Username: ");
        uName = editInfo.nextLine();
        if(uName.equals("n")){
            uName = accountEdit.getUName();
        }
        System.out.print("Enter Password: ");
        password = editInfo.nextLine();
        if(password.equals("n")){
            password = accountEdit.getPassword();
        }
        System.out.print("Enter Email: ");
        email = editInfo.nextLine();
        if(email.equals("n")){
            email = accountEdit.getEmail();
        }
        System.out.print("Enter Description: ");
        desc = editInfo.nextLine();
        if(desc.equals("n")){
            desc = accountEdit.getDescription();
        }
        System.out.print("Do you want to delete your account(y/n)? ");
        tempToDelete = editInfo.nextLine();
        if(tempToDelete.equals("y")){
            toDelete = true;
        }
        editInfo.close();

        accountEdit.editAccount(uName, email, desc, password, toDelete);
    }

    public static void main(String args[]){
        // System.out.println(test2.getAccountNumber());
        // LoginController lc = new LoginController();
        // Account test;
        // test = lc.login();
    }
}
