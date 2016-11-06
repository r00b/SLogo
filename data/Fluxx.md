#Fluxx Design

Delia Li, Robert Steilberg, Ezra Klein, Grayson Wise

###Classes
1. A brief description of the primary classes you envision as part of implementing Fluxx, focused on their behavior not state (like CRC cards). Your design description should clearly identify how you plan to allow new rules and variants to be added to the game.
See code outlines below for primary classes envisioned.
Our design plans to have a game controller that accesses player decisions and adds them to classes that store state, including win conditions and new rules, which can be modified along the player's choices.
2. A detailed description of how the classes' methods will handle the use cases given in the next section.
See embedded image for hierarchies and relationships that address use cases.

![Fluxx design and hierarchies](https://lh3.googleusercontent.com/-WtqJSq-osyo/WBu6SmHLXQI/AAAAAAAAAQY/A7l1_4STII4dGzJfpBECVDKE6brrbUbEwCLcB/s0/0ca24615b5f44d6fb61a843a38966256.jpeg "0ca24615b5f44d6fb61a843a38966256.jpeg")


Classes:

- Main
- Game: GoalCard currGoal; ArrayList< Rule > currRules; ArrayList < Card > discarded, undrawn; ArrayList< Player > players; checkIfMetGoal();

```
while(gameNotOver){
for(player : players){
	player.playTurn()
}
checkIfMetGoal()
}
```

 - Player: ArrayList< Card > cards; ArrayList< KeeperCard > keepers; drawCard(); discardCard(); playCard(); getCurrHand(); getKeepers();
 - Card: executeCard()
	 - RuleCard
	 - GoalCard
	 - ActionCard
	 - KeeperCard
	 - Draw
 - Rule: setCondition()
	 - HandLimit: int limit, getLimit(), setLimit()
	 - PlayRule
	 - SwapPlays
	 - Keeper
 - Goal
	 - ArrayList< Keeper > currKeepers
	 - boolean win