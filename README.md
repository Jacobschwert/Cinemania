# Cinemania


# Database
Cinemania using a SQLite database for the storage of information regarding the function of the website.  
Currently the tables listed below:
- account: accountID, uName, password, Email, cManager, comments, reviews.
- contentList: contentListID, listName, listDescription, contentIDs, authorID.
- content: contentID, contentName, contentDescription, contentGenres, contentRating, reviews.  
- contentManager: cmID, user, contentLists, pinnedLists, whereToWatch, watchStatus, reccomendationLists.
- watchStatus: uID, cID, status.
- review: WIP.
- comment: WIP.

# Database usage
To connect to the SQLite Database you need to download the jar file found the "lib" folder and add it to your class path.
Currently you will need to create the queries in your own classes and pass that in to either the executeQuery() or executeUpdate(). When you wish to "SELECT" from the database use the executeQuery. When you want to "INSERT", "DELETE" or "UPDATE" you use executeUpdate().

In a class that you want to access the database from you need the below variables:
    private SqliteConnector db = new SqliteConnector();
    private Connection conn = db.connect();
    private SqliteQueries query = new SqliteQueries(conn);
    private String queryString;
    
Below is an example of inserting values into the account class:
        queryString = "INSERT INTO account(accountID, uName, password, email, description) VALUES(" + numberStr + ", '" + userName + "', '" + password + "', '" + email + "', '" + description + "');";
        try{
        query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
        
Replace "account" with the table you want to use, and the values inside the parenthesis with the variables you want, then follow the same formatting for the vlues as show. Note when you are entering TEXT values that they are surrounded by '' in the query.
