DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS contentList;
DROP TABLE IF EXISTS content;
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
    FOREIGN KEY(cManager) REFERENCES contentManager(cmID), 
    FOREIGN KEY(comments) REFERENCES feedback(feedbackID),
    FOREIGN KEY(reviews) REFERENCES feedback(feedbackID)
);

CREATE TABLE contentList (
    contentListID INTEGER PRIMARY KEY,
    authorID INTEGER,
    listName TEXT,
    listDescription TEXT,
    contentIDs TEXT, 
    FOREIGN KEY(authorID) REFERENCES account(accountID)
);

CREATE TABLE content (
    contentID INTEGER PRIMARY KEY,
    contentName TEXT,
    contentDescription TEXT,
    contentGenres TEXT,
    contentRating REAL,
    reviews TEXT,
    FOREIGN KEY(reviews) REFERENCES feedback(feedbackID)
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

CREATE TABLE watchStatus (
    uID INTEGER,
    cID INTEGER,
    status TEXT,
    FOREIGN KEY(uID) REFERENCES account(accountID),
    FOREIGN KEY(cID) REFERENCES content(contentID)
);

CREATE TABLE review (
    feedbackID INTEGER PRIMARY KEY,
    feedbackContent TEXT,
    likes INTEGER,
    feedbackAuthor INTEGER,
    FOREIGN KEY(feedbackAuthor) REFERENCES account(accountID)
);

CREATE TABLE comment (
    feedbackID INTEGER PRIMARY KEY,
    feedbackContent TEXT,
    likes INTEGER,
    feedbackAuthor INTEGER,
    FOREIGN KEY(feedbackAuthor) REFERENCES account(accountID)
);
