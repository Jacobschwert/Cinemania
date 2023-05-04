# Cinemania


# Database
Cinemania using a SQLite database for the storage of information regarding the function of the website.  
Currently the tables listed below:
- account: accountID, uName, password, description, email, cManager, comments, reviews.
- contentList: contentListID, authorID, listName, listDescription, contentIDs.
- movie: contentID, contentName, contentDescription, contentGenres, contentRating, reviews.  
- tvShow: contentID, contentName, contentDescription, contentGenres, contentRating, reviews, seasons, episodes.  
- contentManager: cmID, user, contentLists, pinnedLists, buyProviders, rentProviders, flatProviders.
- watchStatus: uID, cID, status.
- review: feedbackID, feedbackContent, likes, feedbackAuthor, comments.
- comment: feedbackID, feedbackContent, likes, feedbackAuthor.

# Database usage
To connect to the SQLite Database you need to download the jar file found the "lib" folder and add it to your class path.
Currently you will need to create the queries in your own classes and pass that in to either the executeQuery() or executeUpdate(). When you wish to "SELECT" from the database use the executeQuery. When you want to "INSERT", "DELETE" or "UPDATE" you use executeUpdate().

In a class that you want to access the database from you need the below variables: 
- private SqliteConnector db = new SqliteConnector();
- private Connection conn = db.connect();
- private SqliteQueries query = new SqliteQueries(conn);
- private String queryString;
    
Below is an example of inserting values into the account class: <br>
queryString = "INSERT INTO account(accountID, uName, password, email, description) VALUES(" + numberStr + ", '" + userName + "', '" + password + "', '" + email + "', '" + description + "');";<br>
        try{<br>
        query.executeUpdate(queryString);<br>
        } catch(SQLException e){<br>
            e.printStackTrace();<br>
        }<br>
 
Other examples can be found in Account.java <br>
Replace "account" with the table you want to use, and the values inside the parenthesis with the variables you want, then follow the same formatting for the vlues as show. Note when you are entering TEXT values that they are surrounded by '' in the query.

# Generate ID
Below is example of generating and ID, changes would need to be made to fit your requirements, but follow the general flow of this. <br>
Boolean moveOn = false;
        int number = 0;
        while (moveOn == false){ //Generate accountID
            number = rand.nextInt(88888) + 11111; //This gives a range of 11111 - 99999
            queryString = "SELECT accountID FROM account WHERE accountID = " + number + ";" ;
            ResultSet rs;
            try{
                rs = query.executeQuery(queryString);
                if (!rs.next()) {
                    this.accountID = number;
                    moveOn = true;
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
