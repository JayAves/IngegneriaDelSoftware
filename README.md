
# Lorenzo Il Magnifico - Java Project 

This Java Project was made as part of _Ingegneria del Sofware_ course, held by Professor Sampietro.

## Getting Started

In _Lorenzo il Magnifico_, you are the head of an influential family in Florence during the Renaissance. You hope to rise through the ranks of nobility to gain even more prestige in the city, but you’ll have to rely on your family if you want to achieve your goals. Over the course of six rounds, you send out family members to different areas in the city to collect resources, develop territories, build your military strength, and pay tribute to the church. However, the members of your family have different strengths. You have to decide if you want to send out the golden child to complete a task, or the black sheep. With careful planning, you’ll be able to lead your family to a higher status level among the city’s elite.

### Game Rules

Complete Game Rules can be found in "_src_" subfolder called "_data_" (/src/data/GameRules.pdf).


## How to start the client-side of the App

Main for clients is located in class OnlineApp. Run it to start the game! You can do this more than one and have multiple clients at the same time.

### Disconnection & Reconnection
If you ever disconnect from on-going game, re-run main and re-write your player name. You will join the game again!

#### ActionTimer expired
If Action timer expires, Player is automatically out of the game. If you want to get back in, run another main and follow the instructions above for reconnection.

## How to start the server-side of the App

Main for server is located in class ServerMain (in package **server**). Run it to start the server!

## Customize your games

Following game parts can be customized:

###Resource Bonuses
* GameBoard ActionSpaces' bonuses  (find them in Bonus.json)
* PersonalBonusTiles bonuses (find them in personalbounstile.json)
* Faith Track bonuses (find them in faithTrack.json)

###Cards
* Cards (find them in cards.json)
* ExcommunicationCards (find them in ExcommunicationsCards.json)
* LeaderCards (find them in leaderCards.json)

###Timers
* Action - timer player has to do an Action
* Room  - timer before a new starts after two players are in queue
Both can be found in Timer.json

All these json files can be found in folder "_data_".

## Project Requirements developed

## Built With

* [JUnit](http://junit.org/junit4/) - Junit 4
* [Maven](https://maven.apache.org/) - Dependency Management
* [Jacoco](http://www.eclemma.org/jacoco/trunk/doc/maven.html) - Runtime agent to your tests and allows basic report creation

## Authors

* **Pietro Melzi** - *[PietroMelzi](https://github.com/PietroMelzi)* - **828862**
* **Pietro Grotti** - *[JayAves](https://github.com/JayAves)* - **825760**
* **Giovanni Mele** - *[LesterNygaard](https://github.com/LesterNygaard)* - **832382**

We're all Politecnico di Milano students.


## Acknowledgments- libraries and tools 

* [Gson](https://github.com/google/gson) - Google project for Json management 
* [SonarQube](https://www.sonarqube.org/)- Code inspection tool
* The project was developed in Eclipse Java Neon Environment

