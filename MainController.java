import java.util.Scanner;

public class MainController {
    
    public void listStartupOptions() {
        // Print welcome message
        System.out.println("                                                                        .         .                                                                                ");
        System.out.println("        ,o888888o.     8 8888 b.             8 8 8888888888            ,8.       ,8.                   .8.          b.             8  8 8888          .8.          ");
        System.out.println("       8888     `88.   8 8888 888o.          8 8 8888                 ,888.     ,888.                 .888.         888o.          8  8 8888         .888.         ");
        System.out.println("    ,8 8888       `8.  8 8888 Y88888o.       8 8 8888                .`8888.   .`8888.               :88888.        Y88888o.       8  8 8888        :88888.        ");
        System.out.println("    88 8888            8 8888 .`Y888888o.    8 8 8888               ,8.`8888. ,8.`8888.             . `88888.       .`Y888888o.    8  8 8888       . `88888.       ");
        System.out.println("    88 8888            8 8888 8o. `Y888888o. 8 8 888888888888      ,8'8.`8888,8^8.`8888.           .8. `88888.      8o. `Y888888o. 8  8 8888      .8. `88888.      ");
        System.out.println("    88 8888            8 8888 8`Y8o. `Y88888o8 8 8888             ,8' `8.`8888' `8.`8888.         .8`8. `88888.     8`Y8o. `Y88888o8  8 8888     .8`8. `88888.     ");
        System.out.println("    88 8888            8 8888 8   `Y8o. `Y8888 8 8888            ,8'   `8.`88'   `8.`8888.       .8' `8. `88888.    8   `Y8o. `Y8888  8 8888    .8' `8. `88888.    ");
        System.out.println("    `8 8888       .8'  8 8888 8      `Y8o. `Y8 8 8888           ,8'     `8.`'     `8.`8888.     .8'   `8. `88888.   8      `Y8o. `Y8  8 8888   .8'   `8. `88888.   ");
        System.out.println("       8888     ,88'   8 8888 8         `Y8o.` 8 8888          ,8'       `8        `8.`8888.   .888888888. `88888.  8         `Y8o.`  8 8888  .888888888. `88888.  ");
        System.out.println("        `8888888P'     8 8888 8            `Yo 8 888888888888 ,8'         `         `8.`8888. .8'       `8. `88888. 8            `Yo  8 8888 .8'       `8. `88888. ");
        System.out.println("\n\n\n\n\n\n");
    
        
        // Print options
        System.out.println("\n**************************************");
        System.out.println("*      Please Select a Choice        *");
        System.out.println("**************************************\n");

        System.out.println("1. View Your Account");
        System.out.println("2. View Content");
        System.out.println("3: Exit");
        // Get user input
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        // Call appropriate method based on user input
        if (choice == 1) {
            listAccountOptions();
        } 
        else if (choice == 2) {
            listContentOptions();
        }
        else if (choice == 3) {
            System.out.println("Goodbye!");
            System.exit(0);
        } 
        else {
            System.out.println("Invalid choice.");
        }
    }
    
    
    private void listAccountOptions() {
        // Code to list account options
        System.out.println("Account options: optionA, optionB, optionC");
    }
    
    private void listContentOptions() {
        // Code to list content options
        System.out.println("Content options: optionX, optionY, optionZ");
    }
    
}
