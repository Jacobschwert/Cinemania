/**
 * ContentList.java
 * 
 * @author Jonathan Bogue
 * @version 0.2
 * 
 * Description: The ContentList class manages a list of Content objects and is controlled by a ContentManager object. The ContentList itself
 * includes an ID, name, description, and of course a list of Contents; it is possible to edit each of these with the provided methods
 * except for the ID, which is intended to be done through the ContentManager, note that this is an unfinished and marginally tested version.
 * 
 */

import java.util.*;

public class ContentList
{
    private static int LIST_HARD_CAP = 100;
    private static int CONTENTS_PER_LINE = 3;
    private int contentListID;
    private int numContents;
    private String contentListName;
    private String contentListDescription;
    private ArrayList<Content> contents;
    private boolean markedForDeletion;
    
    /**
     * Default Constructor
     */
    public ContentList()
    {
        // **********************************************************************
        // Will need a way to assign a unique ID, likely through Content Manager.
        this.contentListID = 0;
        this.numContents = 0;
        this.contentListName = "New List";
        this.contentListDescription = "(Edit the list to change the description.)";
        this.contents = new ArrayList<Content>();
        this.markedForDeletion = false;
    }

    /**
     * Constructor (designed for making a copy)
     * 
     * @param contentListID - The ID of the content list
     * @param numContents - The number of contents within the list
     * @param contentListName - The name of the content list
     * @param contentListDescription - The description of the content list
     * @param contents - The Content objects stored within the list
     */
    public ContentList(int contentListID, int numContents, String contentListName, String contentListDescription, ArrayList<Content> contents)
    {
        this.contentListID = contentListID;
        this.numContents = numContents;
        this.contentListName = contentListName;
        this.contentListDescription = contentListDescription;
        this.contents = new ArrayList<Content>();
        for (int i = 0; i < numContents; i++)
            this.contents.add(contents.get(i));
        this.markedForDeletion = false;
    }

    /**
     * Returns this content list's ID.
     * 
     * @return ID of the content list
     */
    public int getContentListID()
    {
        return this.contentListID;
    }

    /**
     * Returns this content list's name.
     * 
     * @return Name of the content list
     */
    public String getContentListName()
    {
        return this.contentListName;
    }

    /**
     * Returns this content list's description.
     * 
     * @return Description of the content list
     */
    public String getContentListDescription()
    {
        return this.contentListDescription;
    }

    /**
     * Returns the actual list of Content objects.
     * 
     * @return The list of contents
     */
    public ArrayList<Content> getList()
    {
        return this.contents;
    }

    /**
     * Returns true/false depending on if this list is to be deleted (functionally similar to "getting" the variable).
     * 
     * @return True/False
     */
    public boolean checkMarkedForDeletion()
    {
        return this.markedForDeletion;
    }

    /**
     * Changes this content list's ID.
     * 
     * @param contentListID - New ID for this content list
     */
    private void setContentListID(int contentListID)
    {
        // **************Inclusion of this is questionable.
        this.contentListID = contentListID;
    }

    /**
     * Changes this content list's name.
     * 
     * @param contentListName - New name for this content list
     */
    private void setContentListName(String contentListName)
    {
        this.contentListName = contentListName;
    }

    /**
     * Changes this content list's description.
     * 
     * @param contentListDescription - New description for this content list
     */
    private void setContentListDescription(String contentListDescription)
    {
        this.contentListDescription = contentListDescription;
    }

    /**
     * Adds Content to the list of Content.
     * 
     * @param content - A list of Content to add
     */
    private void addContent(ArrayList<Content> content)
    {
        if (!content.isEmpty())
        {
        for (int i = 0; i < content.size(); i++)
            this.contents.add(content.get(i));
        }
    }

    /**
     * Removes the selected Contents from the list.
     * 
     * @param toRemove A list of the indexes of Content to remove
     */
    private void removeContents(ArrayList<Integer> toRemove)
    {
        for (int i = 0; i < toRemove.size(); i++)
            contents.remove((int) toRemove.get(i));
    }

    /**
     * Provides access to editing parts of the content list.
     */
    public void editContentList()
    {
        // This can either be handled with the Content Manager making a copy to edit, or, as it is currently done, the list
        // makes a copy of itself to edit.
        ContentList temp = new ContentList(this.contentListID, this.numContents, this.contentListName,
            this.contentListDescription, this.contents);
        int option = -1;
        char confirm;
        String input;

        do
        {
            System.out.print("\nCurrently editing the " + temp.getContentListName() + " list...\n");
            option = editingMainMenu();
            switch (option)
            {
                // Edit list name.
                case 1:
                    System.out.print("\nEnter a new list name:\n");
                    input = receiveStringInput();
                    // Note there is currently no character limit.
                    temp.setContentListName(input);
                    break;

                // Edit list description.
                case 2:
                    System.out.print("\nEnter a new list description:\n");
                    input = receiveStringInput();
                    // Note there is currently no character limit.
                    temp.setContentListDescription(input);
                    break;

                // Edit contents within list.
                case 3:
                    do
                    {
                        System.out.print("\nCurrently editing contents of the " + temp.getContentListName() + " list...\n");
                        option = editingContentsMenu();

                        switch (option)
                        {
                            // Add content to the list.
                            case 1:
                                // ************************************************************************************
                                // Need to establish going from ContentManager to Content, picking Content to add, then
                                // coming back to the ContentList to add it.
                                if (this.contents.size() < LIST_HARD_CAP)
                                {
                                    // this.contents.add(Content);
                                }
                                else
                                {
                                    System.out.println("List capacity reached, cannot add more content.");
                                }
                                break;

                            // Remove content from the list.
                            case 2:
                                if (temp.displayContents())
                                {
                                    ArrayList<Integer> toRemove = new ArrayList<Integer>();
                                    do
                                    {
                                        System.out.print("Select one piece of Content to remove: ");
                                        option = receiveIntegerInput(1, this.numContents);
                                        toRemove.add(option - 1);

                                        do
                                        {
                                            System.out.print("\nWould you like to remove more Content? (Y/N): ");
                                            confirm = receiveConfirmation();
                                        } while (confirm != 'n');
                                    } while (confirm == 'y');
                                    do
                                    {
                                        System.out.print("\nAre you sure you want to remove the selected Content? (Y/N): ");
                                    } while (confirm != 'y' || confirm != 'n');

                                    if (confirm == 'y')
                                        temp.removeContents(toRemove);
                                }
                                break;

                            // Cancel changes to the contents.
                            case 3:
                                do
                                {
                                    System.out.print("\nAre you sure you want to cancel changes to the contents of this list? (Y/N): ");
                                    confirm = receiveConfirmation();
                                } while (confirm != 'y' || confirm != 'n');

                                if (confirm == 'y')
                                {
                                    temp.getList().clear();
                                    temp.addContent(this.getList());
                                    option = 4;
                                }
                                break;

                            // Confirm changes to the contents.
                            case 4:
                                do
                                {
                                    System.out.print("\nAre you sure you want to confirm changes to the list? (Y/N): ");
                                    confirm = receiveConfirmation();
                                } while (confirm != 'y' || confirm != 'n');

                                if (confirm == 'n')
                                    option = -1;
                                break;

                            default:
                                System.out.println("Error: Unknown option selected, continuing...");
                        }
                    } while (option != 4);
                    break;

                // Show the list as it is with changes made.
                case 4:
                    temp.displayList();
                    break;

                // Delete the list.
                case 5:
                    do
                    {
                        System.out.print("\nAre you sure you want to delete this list? (Y/N): ");
                        confirm = receiveConfirmation();
                    } while (confirm != 'y' || confirm != 'n');
                    
                    if (confirm == 'y')
                    {
                        // *****************************************************************************************
                        // The ContentManager will have to check whether or not this list is marked for deletion (using
                        // the checkMarkedForDeletion() method) to determine if this list should be deleted.
                        this.markedForDeletion = true;
                    }
                    break;

                // Cancel making changes.
                case 6:
                    do
                    {
                        System.out.print("\nAre you sure you want to cancel changes to this list? (Y/N): ");
                        confirm = receiveConfirmation();
                    } while (confirm != 'y' || confirm != 'n');

                    if (confirm == 'y')
                        option = 7;
                    break;

                // Confirm changes made.
                case 7:
                    do
                    {
                        System.out.print("\nAre you sure you want to confirm changes to this list? (Y/N): ");
                        confirm = receiveConfirmation();
                    } while (confirm != 'y' || confirm != 'n');

                    if (confirm == 'y')
                    {
                        setContentListName(temp.getContentListName());
                        setContentListDescription(temp.getContentListDescription());
                        this.contents.clear();
                        addContent(temp.getList());
                    }
                    else
                        option = -1;
                    break;
                    
                default:
                    System.out.println("Error: Unknown option selected, continuing...");
            }
        } while (!this.markedForDeletion && option != 7);
    }

    /**
     * Handles the menu users access to edit the content list.
     * 
     * @return The user's option as an integer
     */
    private int editingMainMenu()
    {
        int option = -1;

        do
        {
            System.out.print("\nSelect an option below:\n");
            System.out.print("\t1. Change List Name\n");
            System.out.print("\t2. Change Description\n");
            System.out.print("\t3. Edit Contents\n");
            System.out.print("\t4. Review List Changes\n");
            System.out.print("\t5. Delete List\n");
            System.out.print("\t6. Cancel Changes\n");
            System.out.print("\t7. Confirm Changes\n");
            System.out.print("\nOption: ");
            option = receiveIntegerInput(1, 7);
        } while (option < 1 || option > 7);

        return option;
    }

    /**
     * Handles the menu users access to edit the contents within a list.
     * 
     * @return The user's option as an integer
     */
    private int editingContentsMenu()
    {
        int option = -1;

        do
        {
            System.out.print("\nSelect an option below:\n");
            System.out.print("\t1. Add Content\n");
            System.out.print("\t2. Remove Content\n");
            System.out.print("\t3. Cancel Changes\n");
            System.out.print("\t4. Confirm Changes\n");
            System.out.print("\nOption: ");
            option = receiveIntegerInput(1, 5);
        } while (option < 1 || option > 4);

        return option;
    }

    /**
     * Displays the name, description, and contents of the list.
     */
    public void displayList()
    {
        System.out.print("\nName: " + this.getContentListName() + "\n");
        System.out.print("\nDescription: " + this.getContentListDescription() + "\n");
        displayContents();
    }

    /**
     * Displays each Content object currently stored within this list.
     * 
     * @return True if the list has Content to display, false if the list is empty.
     */
    private boolean displayContents()
    {
        boolean hasContent = true;
        if (this.numContents == 0)
        {
            System.out.println("\nThis list is currently empty.\n");
            hasContent = false;
        }
        else
        {
            System.out.print("\nContents:\n");
            // The list of contents is displayed such that each line will support CONTENTS_PER_LINE... contents per line, that
            // way the user is not overwhelmed with lines of contents, and a new line is made upon reaching the last piece of Content.
            for (int i = 1; i <= this.numContents; i++)
            {
                System.out.print(i + ". " + this.contents.get(i - 1).getContentName());
                if (i % CONTENTS_PER_LINE == 0 || i == this.numContents)
                    System.out.print("\n");
                else
                    System.out.print("\t");
            }
        }
        return hasContent;
    }

    /**
     * Receives a String input from the user for use in completing a menu option.
     * 
     * @return The user's input as a String
     */
    private String receiveStringInput()
    {
        String input;
        Scanner reader = new Scanner(System.in);
        
        input = reader.nextLine();
        reader.close();
        return input;
    }

    /**
     * Receives an integer input from the user for use in completing a menu option.
     * 
     * @param lowerBound - The minimum value for the user's input (must be 0 or greater)
     * @param higherBound - The maximum value for the user's input (must be greater than or equal to the lower bound)
     * @return The user's input as an int (-1 if it was invalid)
     */
    private int receiveIntegerInput(int lowerBound, int higherBound)
    {
        int integer = -1;
        Scanner reader = new Scanner(System.in);

        try
        {
            integer = reader.nextInt();
        }
        catch (Exception e)
        {
        }
        if (integer < lowerBound || integer > higherBound)
            System.out.println("Invalid input, try again.");
        reader.close();
        return integer;
    }

    /**
     * Receives a character input from the user for use in confirming decisions.
     * 
     * @return 'x' for invalid input, 'y' or 'n' otherwise
     */
    private char receiveConfirmation()
    {
        char confirm = 'x';
        String input = receiveStringInput();

        if (input.length() == 1)
        {
            input.toLowerCase();
            confirm = input.charAt(0);
            if (confirm != 'y' || confirm != 'n')
                confirm = 'x';
        }
        if (confirm == 'x')
            System.out.println("Invalid input, try again.");

        return confirm;
    }
}
