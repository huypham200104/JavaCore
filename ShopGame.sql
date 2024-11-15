


  CREATE TABLE `Users` (
    `UserID` int PRIMARY KEY AUTO_INCREMENT,
    `UserName` VARCHAR(50),
    `Email` VARCHAR(100),
    `Password` VARCHAR(50),
    `Role` enum('admin','customer')
  );
    CREATE TABLE `KindOfGame` (
    `GameId` INT PRIMARY KEY ,
    `NameGame` VARCHAR(100),
    `Platform` VARCHAR(50),
    `Genre` VARCHAR(50)
  );

  CREATE TABLE `AccountGame` (
    `IngameId` INT PRIMARY KEY,
    `UserNameGame` VARCHAR(50),
    `PasswordGame` VARCHAR(50),
    `Price` int,
    `Status` int,
    `GameId` int,

    FOREIGN KEY (GameId) REFERENCES KindOfGame(GameId)

  );

  CREATE TABLE `Transactions` (
    `TransactionID` INT PRIMARY KEY AUTO_INCREMENT,
    `IngameID` int,
    `TransactionDate` DATE,
    `Total` int,
    `UserID` int,
    FOREIGN KEY (IngameID) REFERENCES AccountGame(IngameID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
    
  );
