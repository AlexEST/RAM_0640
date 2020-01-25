CREATE TABLE AUTHOR(
        ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
                (START WITH 1, INCREMENT BY 1)
        , FIRSTNAME VARCHAR(30) NOT NULL
        , LASTNAME VARCHAR(30) NOT NULL
);


CREATE TABLE CATEGORY(
       ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
                (START WITH 1, INCREMENT BY 1)
       ,TITLE VARCHAR(30) NOT NULL
);

CREATE TABLE BOOK(
	ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
                (START WITH 1, INCREMENT BY 1)
        ,TITLE VARCHAR(100) NOT NULL
	,DESCRIPTION VARCHAR(2000)
	,PRICE DECIMAL(5,2) NOT NULL DEFAULT 9.99
	,PAGES INT NOT NULL
	,DATE_PUBLICATION DATE NOT NULL DEFAULT CURRENT_DATE
	,"LANGUAGE" VARCHAR(25) NOT NULL DEFAULT 'ENG'
	,CATEGORY_ID INT NOT NULL DEFAULT 1
        ,FOREIGN KEY(CATEGORY_ID)
            REFERENCES CATEGORY(ID)
);

CREATE TABLE BOOK_AUTHOR(
	BOOK_ID INT NOT NULL
	,AUTHOR_ID INT NOT NULL
        ,PRIMARY KEY (AUTHOR_ID, BOOK_ID)
        ,FOREIGN KEY(AUTHOR_ID)
            REFERENCES AUTHOR(ID)
        ,FOREIGN KEY(BOOK_ID)
            REFERENCES BOOK(ID)
);

INSERT INTO AUTHOR (FIRSTNAME, LASTNAME) VALUES ('Steve','Sanderson');
INSERT INTO AUTHOR (FIRSTNAME, LASTNAME) VALUES ('Adam','Freeman');
INSERT INTO AUTHOR (FIRSTNAME, LASTNAME) VALUES ('Jon','Galloway');
INSERT INTO AUTHOR (FIRSTNAME, LASTNAME) VALUES ('Phil','Haack');
INSERT INTO AUTHOR (FIRSTNAME, LASTNAME) VALUES ('Brad','Wilson');
INSERT INTO AUTHOR (FIRSTNAME, LASTNAME) VALUES ('K. Scott','Allen');
INSERT INTO AUTHOR (FIRSTNAME, LASTNAME) VALUES ('Jose Rolando','Guay Paz');
INSERT INTO AUTHOR (FIRSTNAME, LASTNAME) VALUES ('Dean','Hume');
INSERT INTO AUTHOR (FIRSTNAME, LASTNAME) VALUES ('Andrew','Stellman');
INSERT INTO AUTHOR (FIRSTNAME, LASTNAME) VALUES ('Jennifer','Greene');
INSERT INTO AUTHOR (FIRSTNAME, LASTNAME) VALUES ('Andrew','Troelsen');
INSERT INTO AUTHOR (FIRSTNAME, LASTNAME) VALUES ('Herbert','Schildt');




INSERT INTO CATEGORY (TITLE) VALUES ('Web');
INSERT INTO CATEGORY (TITLE) VALUES ('Programming');





INSERT INTO BOOK (TITLE, DESCRIPTION, PRICE, PAGES, DATE_PUBLICATION, "LANGUAGE", CATEGORY_ID) 
    VALUES ('Pro ASP.NET MVC 4', 'The ASP.NET MVC 4 Framework is the latest evolution of Microsoft’s ASP.NET web platform.', 38.85, 500, '2013-01-16', 'ENG', 1);
INSERT INTO BOOK (TITLE, DESCRIPTION, PRICE, PAGES, DATE_PUBLICATION, "LANGUAGE", CATEGORY_ID) 
    VALUES ('Professional ASP.NET MVC 4', 'An outstanding author team presents the ultimate Wrox guide to ASP.NET MVC 4Microsoft insiders join giants of the software development community to offer this in-depth guide to ASP.NET MVC, an essential web development technology. Experienced .NET and ASP.NET developers will find all the important information they need to build dynamic, data-driven websites with ASP.NET and the newest release of Microsoft''s Model-View-Controller technology.', 29.47, 400, '2014-02-16', 'ENG', 1);
INSERT INTO BOOK (TITLE, DESCRIPTION, PRICE, PAGES, DATE_PUBLICATION, "LANGUAGE", CATEGORY_ID) 
    VALUES ('Beginning ASP.NET MVC 4', 'Fast ASP.NET Websites delivers just what it promises—practical, hands-on guidance to create faster, more efficient ASP.NET sites and applications. This book offers step-by-step .NET-specific examples showing you how to apply classic page optimization tips, ASP.NET-specific techniques, and ways to leverage new HTML5 features.', 30.71, 300, '2012-03-16', 'ENG', 1);
INSERT INTO BOOK (TITLE, DESCRIPTION, PRICE, PAGES, DATE_PUBLICATION, "LANGUAGE", CATEGORY_ID) 
    VALUES ('Fast ASP.NET Websites', NULL, 24.91, 200, '2011-04-16', 'ENG', 1);
INSERT INTO BOOK (TITLE, DESCRIPTION, PRICE, PAGES, DATE_PUBLICATION, "LANGUAGE", CATEGORY_ID) 
    VALUES ('Pro C# 5.0 and the .NET 4.5 Framework', ' This new edition of Pro C# 5.0 and the .NET 4.5 Platform has been completely revised and rewritten to reflect the latest changes to the C# language specification and new advances in the .NET Framework. You''ll find new chapters covering all the important new features that make .NET 4.5 the most comprehensive release yet, including: .NET APIs for Windows 8 style UI apps; New asynchronous task-based model for async operations; How HTML5 support is being wrapped into C# web applications', 29.00, 1657, '2012-08-21', 'ENG', 2);
INSERT INTO BOOK (TITLE, DESCRIPTION, PRICE, PAGES, DATE_PUBLICATION, "LANGUAGE", CATEGORY_ID) 
    VALUES ('Pro VB 2008 and the .NET 3.5 Platform', 'Anyone with some software development experience interested in the new .NET 3.5 platform and the Visual Basic language. Whether you’re moving to .NET for the first time or you’re already writing applications on .NET 2.0 or .NET 3.0, this book will provide you with a solid grounding in this new technology and serve as a comprehensive reference throughout your coding career. ', 25.00, 1245, '2011-06-22', 'ENG', 2);
INSERT INTO BOOK (TITLE, DESCRIPTION, PRICE, PAGES, DATE_PUBLICATION, "LANGUAGE", CATEGORY_ID) 
    VALUES ('Head First C#', 'Head First C# is a complete learning experience for learning how to program with C#, XAML, the .NET Framework, and Visual Studio. Fun and highly visual, this introduction to C# is designed to keep you engaged and entertained from first page to last.', 29.00, 653,'2012-06-13', 'ENG', 2);
INSERT INTO BOOK (TITLE, DESCRIPTION, PRICE, PAGES, DATE_PUBLICATION, "LANGUAGE", CATEGORY_ID) 
    VALUES ('Java: A Beginner''s Guide', 'Fully updated for Java Platform, Standard Edition 8 (Java SE 8), Java: A Beginner''s Guide, Sixth Edition gets you started programming in Java right away. Bestselling programming author Herb Schildt begins with the basics, such as how to create, compile, and run a Java program. He then moves on to the keywords, syntax, and constructs that form the core of the Java language. This Oracle Press resource also covers some of Java''s more advanced features, including multithreaded programming, generics, and Swing. Of course, new Java SE 8 features such as lambda expressions and default interface methods are described. An introduction to JavaFX, Java''s newest GUI, concludes this step-by-step tutorial.', 19.95, 545, '2014-04-15', 'ENG', 2);
INSERT INTO BOOK (TITLE, DESCRIPTION, PRICE, PAGES, DATE_PUBLICATION, "LANGUAGE", CATEGORY_ID)
    VALUES ('Swing: A Beginner''s Guide', 'Using the practical pedagogy that has made his other Beginner’s Guides so successful, Herb Schildt provides new Swing programmers with a completely integrated learning package. Perfect for the classroom or self-study, Swing: A Beginner’s Guide delivers the appropriate mix of theory and practical coding.', 9.99, 389,'2008-12-12','ENG', 2);
INSERT INTO BOOK (TITLE, DESCRIPTION, PRICE, PAGES, DATE_PUBLICATION, "LANGUAGE", CATEGORY_ID) 
    VALUES ('Herb Schildt''s C++ Programming Cookbook', 'Legendary programming author Herb Schildt shares some of his favorite programming techniques in this high-powered C++ "cookbook." Organized for quick reference, each "recipe" shows how to accomplish a practical programming task. A recipe begins with a list of key ingredients (classes, functions, and headers) followed by step-by-step instructions that show how to assemble them into a complete solution.', 9.99, 621, '2008-04-28', 'ENG', 2);
INSERT INTO BOOK (TITLE, DESCRIPTION, PRICE, PAGES, DATE_PUBLICATION, "LANGUAGE", CATEGORY_ID) 
    VALUES ('Язык программирования C# 5.0 и платформа .NET 4.5', 'Признанный эксперт мирового уровня - Эндрю Троелсен, обучающий .NET с самой первой версии, поможет вам обрести с помощью книги «Язык программирования C# 5.0 и платформа .NET 4.5» глубокое понимание ключевых аспектов языка программирования C#, а также новых функциональных возможностей платформы, которые появились в версии .NET 4.5', 32.95, 1657,'2013-02-22', 'RUS', 2);



INSERT INTO BOOK_AUTHOR VALUES (1, 2);
INSERT INTO BOOK_AUTHOR VALUES (2, 2);
INSERT INTO BOOK_AUTHOR VALUES (3, 7);
INSERT INTO BOOK_AUTHOR VALUES (4, 5);
INSERT INTO BOOK_AUTHOR VALUES (4, 8);
INSERT INTO BOOK_AUTHOR VALUES (5, 11);
INSERT INTO BOOK_AUTHOR VALUES (6, 11);
INSERT INTO BOOK_AUTHOR VALUES (7, 9);
INSERT INTO BOOK_AUTHOR VALUES (7, 10);
INSERT INTO BOOK_AUTHOR VALUES (8, 12);
INSERT INTO BOOK_AUTHOR VALUES (9, 12);
INSERT INTO BOOK_AUTHOR VALUES (10, 12);
INSERT INTO BOOK_AUTHOR VALUES (11, 11);
