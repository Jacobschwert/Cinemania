DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS contentList;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS tvShow;
DROP TABLE IF EXISTS contentManager;
DROP TABLE IF EXISTS watchStatus;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS review;


CREATE TABLE account (
    accountID INTEGER PRIMARY KEY,
    uName TEXT,
    password TEXT,
    description TEXT,
    email TEXT,
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

CREATE TABLE movie (
    contentID INTEGER PRIMARY KEY,
    contentName TEXT,
    contentDescription TEXT,
    contentGenres TEXT,
    contentRating REAL,
    reviews TEXT
);

CREATE TABLE tvShow (
    contentID INTEGER PRIMARY KEY,
    contentName TEXT,
    contentDescription TEXT,
    contentGenres TEXT,
    contentRating REAL,
    reviews TEXT,
    seasons INTEGER,
    episodes INTEGER
);

CREATE TABLE contentManager (
    cmID INTEGER PRIMARY KEY,
    user INTEGER,
    contentLists TEXT,
    pinnedLists TEXT,
    buyProviders TEXT,
    rentProviders TEXT,
    flatProviders TEXT
);

CREATE TABLE watchStatus (
    uID INTEGER,
    cID INTEGER,
    status INTEGER,
    FOREIGN KEY(uID) REFERENCES account(accountID),
    FOREIGN KEY(cID) REFERENCES content(contentID)
);

CREATE TABLE review (
    feedbackID INTEGER PRIMARY KEY,
    targetID INTEGER,
    likes INTEGER,
    feedbackAuthor INTEGER,
    comments TEXT,
    reviewContent TEXT,
    FOREIGN KEY(feedbackAuthor) REFERENCES account(accountID)
);

CREATE TABLE comment (
    feedbackID INTEGER PRIMARY KEY,
    feedbackContent TEXT,
    likes INTEGER,
    feedbackAuthor INTEGER,
    FOREIGN KEY(feedbackAuthor) REFERENCES account(accountID)
);

DELETE FROM account;
SELECT * FROM account;