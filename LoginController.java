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
        System.out.println("Logout");
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
}
