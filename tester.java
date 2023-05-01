/*
 * This class should act like a controller, specifically one that has gotten past the Account class already.
 */

import java.io.*;
import java.util.*;
import java.net.*;

class tester
{
    // This global variable is purely for testing.
    static Scanner reader = new Scanner(System.in);

    public static void main(String args[])
    {
        // Initialize things for testing.
        int option = -1;
        int size = 0;
        ContentManager manager = new ContentManager();
        

        // Technically this would be things you can do at the Account, but since it's the first and main class it's made the main menu.
        do
        {
            System.out.println("Main Menu");
            System.out.println("\t1. Access Content Manager");
            System.out.println("\t#. Other Options");
            System.out.print("\nSelect Access Content Manager: ");
            option = receiveIntegerInput(1, 1);
        } while (option != 1);

        // This would really be all the things you could do at the main menu, not just Content Manager stuff.
        switch(option)
        {
            case 1:
                do
                {
                    System.out.println("\nNow using Content Manager\n");
                    System.out.println("\t1. View/Search Content");
                    System.out.println("\t2. View Recommendations");
                    System.out.println("\t3. Create New Content List");
                    System.out.println("\t4. View/Edit My Content Lists");
                    System.out.println("\t5. View Pinned Lists");
                    System.out.println("\t6. Search Users");
                    System.out.println("\t7. Exit Content Manager");
                    System.out.print("\n Select an option: ");
                    option = receiveIntegerInput(1, 7);
                
                
                    switch(option)
                    {
                        // This is definitely not how this case would work, but for testing purposes what you see is what you get.
                        // Also I didn't feel like putting in every piece of Star Wars content with how much of it there is.
                        case 1:
                            // manager.contentSearch("Star Wars");
                            fakeContentSearch();
                            break;

                        // As you can see, this is not implemented at all, so don't use it yet.
                        case 2:
                            // manager.getRecommendationLists();
                            System.out.println("\n***Not Implemented***\n");
                            break;

                        // An empty list gets made and added to the list of ContentLists, note that there's currently no maximum
                        // number of them checked for.
                        case 3:
                            manager.createNewContentList();
                            System.out.println("New Content List created successfully.");
                            // ***Fallthrough would actually work well here, making a new list and going straight to viewing them.

                        // View the ContentLists you've made and edit them if desired (the editing parts are basically ripped from
                        // the old version of ContentList).
                        case 4:
                            size = manager.getContentLists().size();
                            if (size > 0)
                            {
                                System.out.print("\nYour Content Lists:\n");
                                for (int i = 0; i < size; i++)
                                {
                                    System.out.print("\n\t" + (i + 1) + ". " + manager.getContentLists().get(i).getContentListName() + "\n");
                                    System.out.print("Description: " + manager.getContentLists().get(i).getContentListDescription() + "\n");
                                }
                                do
                                {
                                    System.out.print("\nSelect a list to edit or enter 0 to return to the Content Manager menu.");
                                    System.out.print("\nOption: ");
                                    option = receiveIntegerInput(0, size);
                                    if (option > 0 && option < size)
                                    {
                                        editContentList(manager, manager.getContentLists().get(option - 1));
                                    }
                                } while (option < 0 || option > size);
                            }
                            else
                                System.out.println("No Content Lists to display.");
                            break;

                        // Also not implemented yet.
                        case 5:
                            // manager.getPinnedLists();
                            System.out.println("\n***Not Implemented***\n");
                            break;

                        // This isn't implemented either.
                        case 6:
                            // manager.userSearch("Some Guy");
                            System.out.println("\n***Not Implemented***\n");
                            break;

                        // Simply confirming exiting the Content Manager.
                        case 7:
                            System.out.println("\nExiting Content Manager...");
                            break;
                        
                        default:
                            System.out.println("\nQuit trying to break this fragile code.\n");
                            break;
                    }
                } while (option != 7);

                System.out.println("That's it, that's all there is right now.");
                break;
            default:
                System.out.println("What?");
        }
        reader.close();
    }

    // Used to simulate searching for Content since it's not implemented as of yet.
    private static void fakeContentSearch()
    {
        ContentList contentFromSearch = accessContentsFromSearch();
        System.out.println("\nNow viewing/searching Content\n");
        System.out.println("STAR WARS\n");
        for (int i = 0; i < contentFromSearch.getContentListSize(); i++)
        {
            System.out.print("\t" + (i + 1) + ". " + contentFromSearch.getList().get(i).getContentName() + "\n");
        }
    }

    // This is just used to make creating a fake Content search for testing purposes much simpler.
    private static ContentList accessContentsFromSearch()
    {
        ContentList contentFromSearch = new ContentList();
        contentFromSearch.setContentListID(99);
        contentFromSearch.setContentListName("Content Search");
        contentFromSearch.setContentListDescription("What is retrieved from a search (for testing).");
        contentFromSearch.addContent(new Content(1, "Star Wars Episode I: The Phantom Menace", "Desc.", (float) 5.0));
        contentFromSearch.addContent(new Content(2, "Star Wars Episode II: Attack of the Clones", "Desc.", (float) 5.0));
        contentFromSearch.addContent(new Content(3, "Star Wars Episode III: Revenge of the Sith", "Desc.", (float) 5.0));
        contentFromSearch.addContent(new Content(4, "Star Wars Episode IV: A New Hope", "Desc.", (float) 5.0));
        contentFromSearch.addContent(new Content(5, "Star Wars Episode V: The Empire Strikes Back", "Desc.", (float) 5.0));
        contentFromSearch.addContent(new Content(6, "Star Wars Episode VI: Return of the Jedi", "Desc.", (float) 5.0));
        contentFromSearch.addContent(new Content(7, "Star Wars Episode VII: The Force Awakens", "Desc.", (float) 5.0));
        contentFromSearch.addContent(new Content(8, "Star Wars Episode VIII: The Last Jedi", "Desc.", (float) 5.0));
        contentFromSearch.addContent(new Content(9, "Star Wars Episode IX: The Rise of Skywalker", "Desc.", (float) 5.0));
        contentFromSearch.addContent(new Content(10, "Rogue One: A Star Wars Story", "Desc.", (float) 5.0));
        contentFromSearch.addContent(new Content(11, "Solo: A Star Wars Story", "Desc.", (float) 5.0));
        contentFromSearch.addContent(new Content(12, "Star Wars The Clone Wars", "Desc.", (float) 5.0));
        contentFromSearch.addContent(new Content(13, "Star Wars Rebels", "Desc.", (float) 5.0));
        contentFromSearch.addContent(new Content(14, "The Mandalorian", "Desc.", (float) 5.0));
        return contentFromSearch;
    }

    // Read the name of the method to see what it does.
    private static int receiveIntegerInput(int lowerBound, int higherBound)
    {
        int integer = -1;

        try
        {
            integer = reader.nextInt();
        }
        catch (Exception e)
        {
        }
        if (integer < lowerBound || integer > higherBound)
            System.out.println("Invalid input, try again.");
        return integer;
    }

    // Read the name of the method to see what it does.
    private static String receiveStringInput()
    {
        String input;
        
        input = reader.nextLine();
        return input;
    }

    // Read the name of the method to see what it does.
    private static char receiveConfirmation()
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

    // Select Content to add to a ContentList, returns -1 if no content is found.
    private static int findContent(ContentManager manager)
    {
        int contentID = -1;
        int option = -1;
        ArrayList<ContentList> listOfLists;
        ContentList list;
        ContentList contentFromSearch = accessContentsFromSearch();

        // It might be possible to have Content repeatedly added from this menu to the same list, but for now it lets the user add one Content
        // object and stops searching to return the found contentID.
        do
        {
            System.out.print("\nCurrently searching for Content...\n");
            option = findContentMenu();
            switch (option)
            {
                // This would be the user simply searching for Content to add, obviously this is not exactly how it would work though.
                case 1:
                    // manager.contentSearch("Star Wars");
                    do
                    {
                        fakeContentSearch();
                        System.out.print("\nOption: ");
                        option = receiveIntegerInput(1, 14);
                        contentID = contentFromSearch.getList().get(option - 1).getContentID();
                    } while (option < 1 || option > contentFromSearch.getContentListSize());
                    option = 4;
                    break;

                // This is the user selecting from a Recommendation List of theirs, though this is not exactly implemented yet.
                case 2:
                    // listOfLists = manager.getRecommendationLists();
                    // ***Select a list***
                    // list = listOfLists.get(option);

                    // ***Select Content***
                    // option = receiveIntegerInput(1, list.getContentListSize());
                    // contentID = list.getList().get(option - 1).getContentID();
                    System.out.println("\n***Not Implemented***\n");
                    option = 4;
                    break;
                
                // This is the user selecting from a Pinned List, but I wonder if it should also be possible to search user Accounts to find their
                // lists first and then add from them; this is also not implemented yet.
                case 3:
                    // listOfLists = manager.getPinnedLists();
                    // ***Select a list***
                    // list = listOfLists.get(option);

                    // ***Select Content***
                    // option = receiveIntegerInput(1, list.getContentListSize());
                    // contentID = list.getList().get(option - 1).getContentID();
                    System.out.println("\n***Not Implemented***\n");
                    option = 4;
                    break;

                case 4:
                    System.out.println("\nCancelling Content search...");
                    break;

                default:
                    System.out.println("\nQuit trying to break things, it's rude.\n");
                    break;
            }
        } while (option != 4);

        return contentID;
    }

    // Menu for selecting a way to find Content.
    private static int findContentMenu()
    {
        int option = -1;

        do
        {
            System.out.print("\nSelect an option below:\n");
            System.out.print("\t1. Search for Content\n");
            System.out.print("\t2. Select from Recommendation List\n");
            System.out.print("\t3. Select from Pinned List\n");
            System.out.print("\t4. Cancel\n");
            System.out.print("\nOption: ");
            option = receiveIntegerInput(1, 4);
        } while (option < 1 || option > 4);

        return option;
    }

    // Edit a ContentList as desired.
    private static void editContentList(ContentManager manager, ContentList toEdit)
    {
        // This can either be handled with the Content Manager making a copy to edit, or, as it is currently done, the list
        // makes a copy of itself to edit.
        ContentList temp = new ContentList(toEdit.getContentListID(), toEdit.getContentListSize(), toEdit.getContentListName(),
            toEdit.getContentListDescription(), toEdit.getList());
        int option = -1;
        char confirm;
        String input;

        do
        {
            System.out.print("\nCurrently editing the " + temp.getContentListName() + " list...\n");
            option = editingContentListMenu();
            switch (option)
            {
                // Edit list name.
                case 1:
                    System.out.print("\nEnter a new list name:\n");
                    input = receiveStringInput();
                    // Note there is currently no checking of input.
                    temp.setContentListName(input);
                    break;

                // Edit list description.
                case 2:
                    System.out.print("\nEnter a new list description:\n");
                    input = receiveStringInput();
                    // Note there is currently no checking of input.
                    temp.setContentListDescription(input);
                    break;

                // Edit contents within list.
                case 3:
                    do
                    {
                        System.out.print("\nCurrently editing contents of the " + temp.getContentListName() + " list...\n");
                        option = editingContentListContentsMenu();

                        switch (option)
                        {
                            // Add content to the list.
                            case 1:
                                if (temp.getContentListSize() < temp.getListCapacity())
                                {
                                    option = findContent(manager);
                                    if (option == -1)
                                    {
                                        System.out.println("No content selected, moving on...");
                                    }
                                    else
                                    {
                                        // This would probably actually be done through the ContentManager to match the ID with Content.
                                        temp.addContent(findContentWithID(option));
                                    }
                                }
                                else
                                {
                                    System.out.println("List capacity reached, cannot add more content.");
                                }
                                break;

                            // Remove content from the list.
                            case 2:
                                if (temp.displayList())
                                {
                                    ArrayList<Integer> toRemove = new ArrayList<Integer>();
                                    do
                                    {
                                        System.out.print("Select one piece of Content to remove: ");
                                        option = receiveIntegerInput(1, temp.getContentListSize());
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
                                    temp.addContent(toEdit.getList());
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
                    // ******************Should this be a comparison between the old and new list?
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
                        manager.deleteList(toEdit);
                        option = 7;
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
                        toEdit.setContentListName(temp.getContentListName());
                        toEdit.setContentListDescription(temp.getContentListDescription());
                        toEdit.getList().clear();
                        toEdit.addContent(temp.getList());
                    }
                    else
                        option = -1;
                    break;
                    
                default:
                    System.out.println("Error: Unknown option selected, continuing...");
            }
        } while (option != 7);
    }

    // Menu for editing a ContentList.
    private static int editingContentListMenu()
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

    // Menu for editing the Content within a ContentList.
    private static int editingContentListContentsMenu()
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

    // This method is only really here to help find Content using the ID since the example involves pre-defined Content in contentFromSearch.
    private static Content findContentWithID(int contentID)
    {
        ContentList contentFromSearch = accessContentsFromSearch();
        Content item = new Content(0, "Not Found", "Did not find Content", (float) 0.0);
        boolean found = false;
        for (int x = 0; x < contentFromSearch.getContentListSize() && !found; x++)
        {
            if (contentID == contentFromSearch.getList().get(x).getContentID())
            {
                item = contentFromSearch.getList().get(x);
                found = true;
            }
        }
        return item;
    }
}