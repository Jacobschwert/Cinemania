DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS contentList;
DROP TABLE IF EXISTS content;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS tvShow;
DROP TABLE IF EXISTS contentManager;
DROP TABLE IF EXISTS feedback;
DROP TABLE IF EXISTS watchStatus;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS review;


CREATE TABLE account (
    accountID INTEGER PRIMARY KEY,
    uName TEXT,
    password TEXT,
    Email TEXT NOT NULL,
    cManager INTEGER,
    comments TEXT,
    reviews TEXT,
    FOREIGN KEY(cManager) REFERENCES contentManager(cmID)
);

CREATE TABLE contentList (
    contentListID INTEGER PRIMARY KEY,
    authorID INTEGER,
    listName TEXT,
    listDescription TEXT,
    contentIDs TEXT, 
    FOREIGN KEY(authorID) REFERENCES account(accountID)
);

CREATE TABLE tvShow (
    id INTEGER PRIMARY KEY,
    name TEXT,
    description TEXT,
    genre TEXT,
    rating REAL,
    reviews INTEGER,
    episodes INTEGER,
    seasons INTEGER,
    statusID INTEGER, --We will create a random ID number to represent the status of each media content as they are marked
    FOREIGN KEY(statusID) REFERENCES watchStatus(statusID) --The default will as unwatched
);

CREATE TABLE movie (
    id INTEGER PRIMARY KEY,
    name TEXT,
    description TEXT,
    genre TEXT,
    rating REAL,
    reviews INTEGER,
    statusID INTEGER,
    FOREIGN KEY(statusID) REFERENCES watchStatus(statusID)
);

CREATE TABLE contentManager (
    cmID INTEGER PRIMARY KEY,
    user INTEGER,
    contentLists INTEGER,
    pinnedLists INTEGER,
    whereToWatch TEXT,    
    watchStatus INTEGER,
    reccomendationLists INTEGER
);

-- CREATE TABLE feedback (
--     feedbackID INTEGER PRIMARY KEY,
--     feedbackContent TEXT,
--     likes INTEGER,
--     feedbackAuthor INTEGER,
--     FOREIGN KEY(feedbackAuthor) REFERENCES account(accountID)
-- );

CREATE TABLE watchStatus ( --This table was kept in to allow the application to show multiple instances of if something was watched
    statusID INTEGER PRIMARY KEY, --There will be repeats of the status ID, but will be differentiated by different user ID's
    uID INTEGER,
    status INTEGER,
    FOREIGN KEY(uID) REFERENCES account(accountID)
);

CREATE TABLE review (
    reviewID INTEGER PRIMARY KEY,
    reviewSummary TEXT,
    likes INTEGER,
    reviewAuthor INTEGER,
    reviewTarget INTEGER,
    rating INTEGER, --How to show list of comments
    comments INTEGER,
    FOREIGN KEY(reviewAuthor) REFERENCES account(accountID)
);

CREATE TABLE comment (
    commentID INTEGER PRIMARY KEY,
    commentSummary TEXT,
    likes INTEGER,
    commentAuthor INTEGER,
    commentTarget INTEGER,
    FOREIGN KEY(commentAuthor) REFERENCES account(accountID)
);
