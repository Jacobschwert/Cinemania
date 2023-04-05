-- SQLite, if a table needs to be restructured, just drop it and recreate
CREATE TABLE account (
    accountID INTEGER PRIMARY KEY,
    FName TEXT,
    LName TEXT,
    Email TEXT NOT NULL
);

CREATE TABLE contentList (
    contentListID INTEGER PRIMARY KEY,
    authorID INTEGER,
    listName TEXT,
    listDescription TEXT,
    contentIDs TEXT, -- This will be saved in a string, but interpretted as a list
    FOREIGN KEY(authorID) REFERENCES account(accountID)
);

CREATE TABLE content (
    contentID INTEGER PRIMARY KEY,
    contentName TEXT,
    contentType INTEGER,
    contentDescription TEXT,
    contentGenres TEXT, -- See previous comment
    contentRating REAL,
    whereToWatch TEXT,
    watchStatus INTEGER, -- Store 0 for unwatched and 1 for watched
    reviewList TEXT -- List of review IDs(TBC) stored similarly as above
);