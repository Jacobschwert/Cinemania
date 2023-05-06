public class LoginController {

    public Account login() {
        String uName;
        String password;


        System.out.print("Enter Username: ");
        uName = Main.scanner.nextLine();
        System.out.print("Enter Password: ");
        password = Main.scanner.nextLine();
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

        System.out.print("Enter Username: ");
        uName = Main.scanner.nextLine();
        System.out.print("Enter Password: ");
        password = Main.scanner.nextLine();
        System.out.print("Enter Email: ");
        email = Main.scanner.nextLine();
        System.out.print("Enter Description: ");
        desc = Main.scanner.nextLine();
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

        System.out.println("Enter 'n' if you don't want to change a value.");
        System.out.print("Enter Username: ");
        uName = Main.scanner.nextLine();
        if(uName.equals("n")){
            uName = accountEdit.getUName();
        }
        System.out.print("Enter Password: ");
        password = Main.scanner.nextLine();
        if(password.equals("n")){
            password = accountEdit.getPassword();
        }
        System.out.print("Enter Email: ");
        email = Main.scanner.nextLine();
        if(email.equals("n")){
            email = accountEdit.getEmail();
        }
        System.out.print("Enter Description: ");
        desc = Main.scanner.nextLine();
        if(desc.equals("n")){
            desc = accountEdit.getDescription();
        }
        System.out.print("Do you want to delete your account(y/n)? ");
        tempToDelete = Main.scanner.nextLine();
        if(tempToDelete.equals("y")){
            toDelete = true;
        }

        accountEdit.editAccount(uName, email, desc, password, toDelete);
    }

    public static void main(String args[]){
        // System.out.println(test2.getAccountNumber());
        // LoginController lc = new LoginController();
        // Account test;
        // test = lc.login();
    }
}