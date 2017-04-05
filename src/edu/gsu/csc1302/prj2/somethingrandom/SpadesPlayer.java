package edu.gsu.csc1302.prj2.somethingrandom;

import edu.gsu.csc1302.coll1.Card;
import edu.gsu.csc1302.coll1.Card.Rank;
import edu.gsu.csc1302.coll1.Card.Suit;
import edu.gsu.csc1302.coll1.Deck;

/**
 * Spades Player to manage the four AI player types.
 *
 * @author stgilbert
 * @author J-Dierberger
 *
 */
public class SpadesPlayer implements PlayerInterface {

	/**
	 * Aggressive player type constant.
	 */
	public static final int TYPE_AGGRESSIVE = 0;

	/**
	 * Chicken player type constant.
	 */
	public static final int TYPE_CHICKEN = 1;

	/**
	 * Wildcard player type constant.
	 */
	public static final int TYPE_WILDCARD = 2;

	/**
	 * Savagely intelligent player type constant.
	 */
	public static final int TYPE_INTELLIGENT = 3;

	/**
	 * User controlled player constant.
	 */
	public static final int TYPE_USER = 4;

	/**
	 * Int to track the actual designated player type.
	 */
	private int playerType;

	/**
	 * The hand of the player.
	 */
	private Deck hand;

	/**
	 * The controller object.
	 */
	private final Controller myController;

	/**
	 * Player constructor.
	 * @param type Player type to use.
	 * @param controller Controller object for the game
	 */
	public SpadesPlayer(final int type, final Controller controller) {
		playerType = type;
		if (playerType > 4) {
			playerType = 4;
		}
		hand = null;
		myController = controller;
	}

	@Override
	public final void setHand(final Deck deck) {
		hand = deck;
	}

	@Override
	public final int bet() {
		int r = 0;
		switch (playerType) {
		case TYPE_AGGRESSIVE:
			break;
		case TYPE_CHICKEN:
			break;
		case TYPE_WILDCARD:
			break;
		case TYPE_INTELLIGENT:
			break;
		case TYPE_USER:
			System.out.println("Enter your bet:");
			r = Integer.parseInt(SpadesGame.IN.nextLine());
			if (r > 13) {
				r = 13;
			}
			break;
		default:
			throw new RuntimeException(
					"DEFAULT CASE IN SWITCH THAT SHOULDN'T DEFAULT");
		}
		return r;
	}

	@Override
	public final int talk() {
		int r = 0;
		switch (playerType) {
			case TYPE_AGGRESSIVE:
				break;
			case TYPE_CHICKEN:
				break;
			case TYPE_WILDCARD:
				break;
			case TYPE_INTELLIGENT:
				break;
			case TYPE_USER:
				break;
			default:
				throw new RuntimeException(
						"DEFAULT CASE IN SWITCH THAT SHOULDN'T DEFAULT");
		}
		return r;
	}

	@Override
	public final Card play() {
		Card r = null;
		switch (playerType) {
		case TYPE_AGGRESSIVE:
			break;
		case TYPE_CHICKEN:
			break;
		case TYPE_WILDCARD:
			break;
		case TYPE_INTELLIGENT:
			break;
		case TYPE_USER:
			r = playerTurn();
			break;
		default:
			throw new RuntimeException(
					"DEFAULT CASE IN SWITCH THAT SHOULDN'T DEFAULT");
		}
		return r;
	}

	/**
	 * The turn method is called when it's the player's turn.
	 * When it's the players turn, they need to be able to do
	 * various tasks.
	 * Thus, to de-clutter code I made a method exclusively for
	 * handling the code to ask the player what they want to do
	 * and performing those actions.
	 * @return The card the player has chosen to play
	 */
	private Card playerTurn() {
		Card r = null;
		// Loop till we break (when we get a card)
		while (true) {
			// Get user input and parse it for the switch statement.
			System.out.println("What would you like to do? (type \"?\" for help)");
			String str = SpadesGame.IN.nextLine().toLowerCase();
			String[] command;

			try {
				// The string will only contain () if the player is providing args.
				if (str.contains("(") || str.contains(")")) {
					command = str.split("("); // Separate the command from the () contents
					command[1] = str.replace(")", ""); // Delete the closing parentheses.
				} else {
					/*
					 * Otherwise just set the arguments to be the command.
					 * That way we can just switch off the arguments (so that
					 * if they player uses a command with args we won't need a
					 * separate switch statement then if they use a command
					 * without args).
					 */
					command = new String[]{str};
				}
			} catch (Exception e) {
				System.out.println("It seems you formatted your command incorrectly.");
				command = new String[]{str};
			}

			switch (command[0]) {
				case "?":
					System.out.println(
							"\"talk\": Ask your teammate how many books they can win");
					System.out.println(
							"\"view_hand\": View the cards in your hand");
					System.out.println(
							"\"view_table\": View the cards on the table");
					System.out.println(
							"\"play(RANK SUIT)\": Play the card of the given RANK and SUIT.");
					break;
				case "help":
					System.out.println(
							"\"talk\": Ask your teammate how many books they can win");
					System.out.println(
							"\"view_hand\": View the cards in your hand");
					System.out.println(
							"\"view_table\": View the cards on the table");
					System.out.println(
							"\"play(RANK SUIT)\": Play the card of the given RANK and SUIT.");
					break;
				case "talk":
					System.out.println("Your team mate estimates he can win "
							+ myController.getTeamMate(this).talk() + "books.");
					break;
				case "view_hand":
					System.out.println("Your hand:");
					System.out.println(hand);
					break;
				case "view_table":
					System.out.println(
							"Cards on the table (P1 -> P4; null means not played):");
					System.out.println(myController.getPlayedCards());
					break;
				case "play":
					// Make sure args are given and that they're correct
					if (command.length == 1) {
						System.out.println(
								"You have not provided any information about what card to play!"
								);
						System.out.println("Please try again.");
						break;
					}
					if (!command[1].contains(" ")) {
						System.out.println("This command doesn't contain a space!");
						System.out.println("Should be: play(RANK SUIT)");
						System.out.println("Please put a space between the rank and suit.");
						break;
					}
					String[] args = command[1].split(" ");
					if (args.length < 2) {
						System.out.println("Not enough arguments provided!");
						System.out.println("Please give the card's rank and suit.");
					}
					Rank rank;
					try {
						rank = Rank.valueOf(args[0].toUpperCase());
					} catch (Exception e) {
						System.out.println("Rank formatted incorrectly! Please try again.");
						break;
					}
					Suit suit;
					try {
						suit = Suit.valueOf(args[0].toUpperCase());
					} catch (Exception e) {
						System.out.println("Rank formatted incorrectly! Please try again.");
						break;
					}
					// If we have the cards...
					if (hand.contains(CardConstants.getCard(args[0] + "." + args[1]))) {
						// Remove the card the player has chosen.
						r = hand.remove(hand.indexOf(
								CardConstants.getCard(args[0] + "." + args[1])));
					} else {
						System.out.println(
								"You do not have this card! Please enter a card you have");
					}
					break;
				default:
					System.out.println("I'm sorry, that is not a valid command.");
					break;
			}
			if (r != null) {
				break;
			}
		}
		return r;
	}

}
