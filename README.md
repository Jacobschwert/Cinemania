# Cinemania


# Database
Cinemania using a SQLite database for the storage of information regarding the function of the website.  
Currently the tables listed below:
- account: accountID, uName, password, Email, cManager, comments, reviews.
- contentList: contentListID, listName, listDescription, contentIDs, authorID.
- content: contentID, contentName, contentDescription, contentGenres, contentRating, reviews.  
- feedback: feedbackID, feedbackContent, likes, feedbackAuthor.
- contentManager: cmID, user, contentLists, pinnedLists, whereToWatch, watchStatus, reccomendationLists.
- watchStatus: uID, cID, status.
- review: WIP.
- comment: WIP.

# Database usage
To connect to the SQLite Database you need to download the jar file found the "lib" folder and add it to your class path.
Currently you will need to create the queries in your own classes and pass that in to either the executeQuery() or executeUpdate(), but changes will be made to that soon. When you wish to "SELECT" from the database use the executeQuery. When you want to "INSERT", "DELETE" or "UPDATE" you use executeUpdate().
