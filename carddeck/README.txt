How To Run this application :
	- Make sure JAVA_HOME refer to a valid jdk 8 path.
	- Make sure M2_HOME refer to a valid maven path
	- At the root of the project execute the following command : mvn spring-boot:run
	
Interface includes :
	deck.cards.ICard : Represents a card object that goes within a deck.
	
	deck.IDeck : Represents a deck which is a stack of 52 ICards.
	
	deck.IDeckInfo : Represents additional informations for a IDeck instance such as
		creation date, last update date and unique ID.
		
	deck.service.IDeckController : Represents a controller for IDeck that uses a map of
 		IDeckInfo to kepp track of the IDeck it manages. 
 		It provide a facade for IDeck functionalities.

Enum includes :
	deck.cards.Face : Represents a card face. Ex : Queen or Ace
	
	deck.cards.Suit : Represents a suit for cards. Ex : Spade or Heart
	
Implementation includes :
	deck.cards.impl.Card : Implements ICard, it uses the default ordinal comparator 
		to compare cards between on another.	
			   
	deck.impl.Deck : Implements IDeck, its uses an array to store cards and shuffle them 
		using their index.
		
	deck.impl.DeckInfo : Implements IDeckInfo
	
	deck.service.impl.DeckController : Implements IDeckController, it define the resource 
		end points and uses a ConcurrentHashMap to prevent  concurrent access while writing.
		With each call a check is made to remove unused IDeckInfo.
		
	deck.service.impl.Application : The application starting point, controller gets auto 
		discovered since it is in the same package.
		
Tests :
	All the implementation (except for Application) have their counterpart inside src/test/java.
	Each test is named after the class it test within the same package and with the suffix Test.
	