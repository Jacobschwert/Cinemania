/**
 * ContentList.java
 * 
 * @author Jonathan Bogue
 * @version 0.3
 * 
 * Description: The ContentList class manages a list of Content objects and is controlled by a ContentManager object. The ContentList itself
 * includes an ID, name, description, and of course a list of Contents; it is possible to edit each of these with the provided methods
 * except for the ID, which is intended to be done through the ContentManager, note that this is a mostly finished and marginally tested version.
 * 
 */

import java.util.*;

public class ContentList
{
    private static final int LIST_HARD_CAP = 100;
    private static final int CONTENTS_PER_LINE = 3;
    private int contentListID;
    private int numContents;
    private String contentListName;
    private String contentListDescription;
    private ArrayList<Content> contents;
    
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
     * Returns this content list's size.
     * 
     * @return Size of the content list
     */
    public int getContentListSize()
    {
        return this.numContents;
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
     * Returns the maximum number of Contents that can be stored in the list.
     * 
     * @return The maximum number of Contents that can be stored in the list
     */
    public int getListCapacity()
    {
        return LIST_HARD_CAP;
    }

    /**
     * Changes this content list's ID.
     * 
     * @param contentListID - New ID for this content list
     */
    public void setContentListID(int contentListID)
    {
        // **************Inclusion of this is questionable.
        this.contentListID = contentListID;
    }

    /**
     * Changes this content list's name.
     * 
     * @param contentListName - New name for this content list
     */
    public void setContentListName(String contentListName)
    {
        this.contentListName = contentListName;
    }

    /**
     * Changes this content list's description.
     * 
     * @param contentListDescription - New description for this content list
     */
    public void setContentListDescription(String contentListDescription)
    {
        this.contentListDescription = contentListDescription;
    }

    /**
     * Adds a single Content object to this list of Content so long as the hard cap is not reached.
     * 
     * @param content - A Content object to add
     */
    public void addContent(Content content)
    {
        if (this.numContents < LIST_HARD_CAP)
        {
            this.numContents++;
            this.contents.add(content);
        }
    }

    /**
     * Adds a list of Content to this list of Content so long as the hard cap is not reached.
     * 
     * @param content - A list of Content to add
     */
    public void addContent(ArrayList<Content> contentList)
    {
        if (!contentList.isEmpty())
        {
            for (int i = 0; i < contentList.size() && this.numContents < LIST_HARD_CAP; i++)
                this.contents.add(contentList.get(i));
            this.numContents = this.contents.size();
        }
    }

    /**
     * Removes the selected Contents from the list.
     * 
     * @param toRemove A list of the indexes of Content to remove
     */
    public void removeContents(ArrayList<Integer> toRemove)
    {
        for (int i = 0; i < toRemove.size(); i++)
            contents.remove((int) toRemove.get(i));
        this.numContents = this.contents.size();
    }

    /**
     * Displays the name, description, and contents of the list.
     * 
     * @return The result of displayContents (true with Content to display, false if empty).
     */
    public boolean displayList()
    {
        System.out.print("\nName: " + this.getContentListName() + "\n");
        System.out.print("\nDescription: " + this.getContentListDescription() + "\n");
        return displayContents();
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
}