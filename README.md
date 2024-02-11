# Project Report

## Environment & Tools
OS: Windows 10 64bit

IDE: IntelliJ 2020.3.3

Java: 15.0.2+7

Git: 2.24.1.windows.2

Maven: 3.6.3

## Purpose
The purpose of the assignment is to play the role of dungeon master where the heroes need to complete a couple of levels
of skeletons before they fight the boss Lich Lord. Each level becomes more difficult with more enemies spawning the further
they go.

## Procedures
Started by creating all the classes and used the UML diagrams to implement some base returns to the constructor and methods.
To start getting some output when running the program.

### Stats
StatsManager is used to create a list of each attribute, traits and combatStats. It was pretty easy to implement where
I just had to create the lists from the Constant class and methods to return the lists. Also, methods to return a random
attribute and a random trait.

BaseStat was also pretty easy where it´s used for attributes and traits and modifies the values. The baseValue and name 
is given from derivatives attribute/trait/combatStat. The staticModifier is used to upgrade the baseStat before gameplay, and 
the dynamicModifier is used during gameplay to modify the stats. Most of the methods only needed to implement a return
for different modified values. The overridden toString return a formatted string where I use - to space out the information.

Attribute and Trait was easy since it only needs to set name and value to BaseStat.

CombatStat receives attribute and trait and uses them to set a new baseValue with the help of combat_stat_multiplier
from Constants. The description gave good information about how to easily implement the constructor.

### Gear
GearManager was difficult before I could work out how to easily display the input from the xml file, so I could get
the proper data out. I then stated by storing all the different types of weapons and amour pieces as key values in the map.
I then went through the key values and checked for that type to add new armour/weapons in a list that are then stored
together with the key in the HashMap.
The methods to get random weapons were pretty similar where the big different was if it should check for a class or a 
type of weapon. And oneHanded weapons also makes sure it´s not twoHanded. They store the weapons that fits the
requirements in a list and then returns one of them.
RandomArmourOfType uses ArmourForRestriction to get a list for all armour pieces and then creates a list of all the
armour pieces of the requested type and returns one of the pieces.

BaseGear is an abstract class that's used to get name, type and restriction from derivatives. restrictions need to check
if there are more than 1 and then uses a hashMap to check for name of restriction and gets the value that are then set
to the list of classRestrictions. The methods just got return values, and the toString just returns the name that are 
used in derivatives.

Weapon and armour was not too hard to implement where it mostly just needed returns for private.

Weapon is derivative of BaseGear and sets the name, type and restriction for the weapon to BaseGear. It uses damage and
wield as private where it´s later used in methods to get the values. It then gets a new Attribute that is set by
new attribute where it uses a random attribute name with random value between 1 and constant upper_bound.

Armour is derivative of BaseGear and sets the name, type and restriction for the armour to BaseGear. It uses protection
and material as private where it´s later used in methods to get the values. It then gets a new Trait that is set by
new Trait where it uses a random trait name with random value between 1 and constant upper_bound.

toString of Weapon and armour returns a string with the name of the weapon from BaseGear and the name of the attribute 
from getStat.

### Characters
BaseCharacters had some difficult parts where I had some problems figuring out how to exactly use addAbilities but then
figured out it just needed to set abilities with given list of baseAbilities. BaseCharacter just use derivatives to store
the characters stats. getTurnInformation is used by baseHero and baseEnemy to log information about whose turn and
character stats. executeActions was a bit difficult to implement where I had to use a lot of if statement to check what
type of ability is to executed and use different values for damage amount depending on ability type. DetermineActions
was not to difficult as it only creates a list of random baseAbilities. RegisterDamage took some time, and it checks if
the attack was magical or not, and if it was magical the character will only deflect damage depending on the characters
defenceRate otherwise it will deflect damage depending on characters defenceRate and armourProtection. Healing just 
adjusts the character HP and returns the new HP. roundReset restores some AP and Energy after each round. ToString uses 
CharacterStats and CharacterEquipment to display all the information about the character to the user. The other methods 
was not so hard and just used the get different values in baseCharacter.

CharacterStats was not too hard where most methods just need to implement an easy return statement. CharacterStats and
toString needed some more attention to complete. CharacterStats maps name of attribute/trait/combatStat with the there
different values. They were not too hard after completing the attribute mapping. The toString method was a bit complicated
to figure out how to get it to look good.

CharacterEquipment had some difficult parts, and some easier parts. getWeapons and getArmourPieces just needed to return
a list of the respected gear. totalWeaponDamage need to only add damage from one weapon if it´s twoHanded since I add 2 
of the same weapon when I create add them. Otherwise, it will add the damage from both weapon pieces. totalArmourProtection
just returns the sum of protection value from all armour pieces. EmptyWeapon and armour slots just returns the amount of 
empty slots left. AddWeapon checks if the amount of weapon slots is higher than 1 and then adds two of the same weapon
if it´s twoHanded otherwise it only adds it ones. If there is only one weapon slot left it will add the weapon.
AddArmourPiece adds the armour if there is space and makes sure only armour types that don´t already exits gets added.
toString was a bit difficult to get it to look good and make the code work well. Use a for loop that runs through all
weapons and makes sure the weapon does not exist already and adds the weapon in the list. ArmourPieces just adds 
each piece of armour to the list. Both lists are used in return and uses IOHelper.formatAsTable to print the list. The 
hard part was to make the output look good.

#### Heroes
BaseHero is used to create the characters stats in BaseCharacter and hold the method to equip the heroes with weapons
and amour pieces. That are used in each Hero, and it makes sure that each Hero is equipped with a two handed weapon
or two one handed weapons Also, with an armour piece of each type and gives the attributes. This took some time to
complete and was a good challenge to complete. resetHeroStats just needs to replenish the charters AP, Energy and Vitality
between each floor. DoTurn logs the turn information and uses turnInformation from BaseCharacter. It then executes the 
attack on enemies.

The different hero classes are created with a stated name as string that are used in BaseHero to equip the hero with
name and base stats. The classes also get equip with weapons and armour pieces for that class and adds a list of the 
different types of abilities the class got. They were all pretty much the same and just needed the different Constants
depending on the class. And not too hard to implement.

#### Enemies
BaseEnemy pretty much the same as BaseHero but uses a list of weapons to equip the enemy with, and they don´t have armour.
There stats are not replenished after each floor, and they execute attack on heroes. Could use most of the code from
BaseHeroes to complete just needed a few changes.

The different enemy classes were much like the hero classes but instead of having a name they get a number. Lich Lord
don´t get any number because there will only be one. There enemies will only be equipped with weapons and no armour. 
But they still got different abilities depending on class. The Lich Lords vitality also gets modified with 
Boss_Health_Multiplier. Was pretty easy to implement but at first I had addAbilities in BaseEnemy, but the figured that
it was better to please it in each class instead of using a hashMap that I used first.

### Abilities
BaseAbility performs the characterAttack through GameEngine and stores the abilities AP/Energy cost and acts as a base
for all abilities. Had to remake a bit where I first didn't know that for example isMagic was abstract and was supposed
to be implemented in the abilities instead. It was pretty straight forward just needed a bit of thinking when 
implementing performAbility.

The abilities were easy as they mostly used information from Constants.
All the abilities derive from BaseAbility and do the same thing but got different stats or information. They were
pretty easy to implement where in the constructor each ability sets its AP/Energy cost to BaseAbility. If it´s a 
magical ability it gets the element when constructed and gets the magical phrase from Constants. The execute method
returns a boolean if the execute was successful. It tries to perform the ability with a string that shows the 
string of the ability with the cost of casting it and how many targets to attack, dmg and if heroes or enemies will
be targeted. toString formats the phrase and element if it got one, and the ability name from Constant.

### Activity logger
Activity logger was pretty much the last thing I completed. Checked how I created the logger in assignment2 and 
made changes, so it would only log to the console. delayExecution was given in assignment information. performLog
performs the logging with given attribute and uses Constants.use_sleep_delay to delay execution. All other logging
methods runs performLog with different information and uses Constants to colour the logging output for easy of read.

### Problems
Had problems with ItelliJ where GearManager was not accessible and to fix it I had to remove it and make a new class.

Been having problem with maven too where I can´t do $mvn test and don´t know how to fix it, tried $mvn clean and restarting
IntelliJ and some different thing, even reinstalling maven, and I could not get it to work.
## Discussion
The purpose has been fulfilled, and it passes all tests. Gotten more experience in many aspects it feels like, learned
some more about UML more how to use foreach and how the classes are connected.

It felt a bit overwhelming at the beginning and felt a bit stressed since I wanted to complete the assignment before
we had the other courses exams. Some of the hardest parts was GearManager/BaseCharacter. In GearManager
if felt hard before I succeed to get an output to display some data to work with. In BaseCharacter I made some problems
where in executeActions I calculated damage too and then discovered that was already done and did not need to implement
that as it then made the characters take double damage. In CharacterStats I first made a list of all attributes, traits
and combatStats but then discovered I already had the lists in StatsManager.

In CharacterEquipment where I add two handed weapons it feels like It could be improved, so I don´t have a double weapon
that I need to take care of in the code in some different places.