# SleeperMod

This is a mod for Slay The Spire. See details of the play behaviour on its page in the Steam Workshop (Search for "SleeperMod" in the Slay The Spire section).

## General Coding Comments

### Potential Employers
If you're here because of a link from a job application -- thank you for having a look at my code! While I've made a rough indication below where the code here isn't my own, it's somewhat in the nature of modding to copy a fair amount of code from the base-game, so sometimes this line gets blurred. For instance, the cards themselves are basically all copied from the same template, with differing permutations of lines of code taken from the base game.

In particular, the places where I've done the most "ground-up" coding are in the following sub-folders:
- patches: this is more or less all me (the Locator nested class in the Insert patches is fairly boilerplate though).
- actions(.core): this is a real mix, as even where code is borrowed from the decompiled base-game, it was often a lot of work to adapt it here.
  - in particular, the classes "RememberAction" and "Move" in actions.core are mostly my own;
  - whereas the class "ForgetAction" in actions.core was adapted from the base game's "ExhaustAction". I've added the latter as "ReferenceExhaustAction", so that the amount of copying is clear.
- powers (note this is "sleepermod / powers", not "sleepermod / cards / powers"): the functional code here is more or less all me, although the structure of each file is prescribed by the mod template.

Therefore I'd recommend having a look in these folders, for the work I lay the strongest claim to / am proudest of :)

### Commenting
I am working through now and ensuring the comments in all the files are up-to-date. Tthere are quite a lot of files I copied and pasted as a starting point (eg all 75 cards!) so I found a couple of early comments that got erroneously copied all over the place.

In general I've gone fairly light on comments, as I feel the code is fairly self-explanatory *provided a knowledge of the game's base code*. I have just provided comments where I feel that even someone who understands how Slay The Spire itself is coded, would still need a pointer. I suggest the Slay The Spire BaseMod wiki (https://github.com/Alchyr/BasicMod/wiki) for a fuller understanding of the base game's code.

### Use of GitHub
Although I started this project with the intention of using the full branching capabilities of GitHub, I quickly realised this was overkill for a one-man project with a surprisingly linear development. Now that the mod is "finished", I might consider using branches for overall mechanical decisions I haven't yet settled to myself.

I have committed as often as necessary to save my progress on this project. The size of commits varies a lot, as in some cases moving a single package around caused a large number of files to update. One take-away for me would be to half all other development while I perform one of these moves, as it occasionally muddied the water of actual code changes vs. pacakge structure.

### Feedback and Future Updates
I have every intention of continuing to maintain and update this mod, albeit at a slower pace. I've reached out to friends and the modding community for feedback (although of course they can't see the code, so there'll be no feedback there per se).

## src -> main -> java -> sleepermod

The main java code is in src -> main -> java -> sleepermod. The file structure is borrowed and adapted from BasicMod, a freely available mod template.

The SleeperMod class's file sits directly in this package. It does most of the work of calling the other packages and classes, initialising the various new objects, and patching the code I've written into the base game. Most of the structure of this class came with the mod template, so all credit goes to BasicMod for this, not me!

The folders within:
- actions:
  - the game uses the AbstractGameAction class to track changes to the game-state. Wherever a card's effect is too complicated to express with existing Action classes, I have given it a dedicated class here.
  - generally the name of the card's class with "Action" sufffixed, although there are execpetions.
  - some of the files here are very heavily inspired by Action subclasses in the base game (such as my ForgetAction from the game's ExhaustAction). Others are written more or less from the ground up. There is no actual temmplate class provided in the template (I don't think it's set up for people to be making their own actions?) so I would consider all the code here effectively mine.
  - I've added a couple of sub-packages:
    - "core" contains the key Actions -- each of these is called by several different cards / powers / other actions.
    - "followUps" contains actions that are called as a follow-up to another action (either Forget or Awaken).
- archive:
  - old classes no longer in use.
  - Does not generally include old cards (these are in cardsDeprecated instead).
- cards:
  - the cards currently loaded by the game, sub-packaged into attacks, skills, powers, and statuses.
  - the structure of each card's dedicated subclass is taken from BasicMod (ie the dedicated variables for Damage / Block, the containment of data within CardInfo etc.) -- I'm not sure after working on this that it's how I would have done it, and in any case they get all the credit for it.
  - this package also includes the AbstractSleeperCard class, from which all these other cards inherit. It includes various methods which are called by the character's specific Actions (such as ForgetAction and AwakenAction etc) as well as triggering various isinstance clauses across the codebase.
  - the ForgottenCard class also sits at the package level, as it's so unique (and I mgiht eventually try to create its own CardType enum rather than having it be a status). 
- cardsBeta:
  - cards whose code has not yet been fully written up.
- cardsDeprecated:
  - cards are moved here for easy reference if they are no longer in use (functionally equivalent to cardsBeta).
- character:
  - the AbstractCharacter subclass lives here.
  - the structure of this is provided by the mod template, so not my own work.
- patches:
  - these classes have a wide array of functionality, from adding fields to existing classes, to altering their methods.
  - they are roughly sorted by functionality, and the naming convention is based on the type of patch, and the class / method being edited.
- potions:
  - as-yet unused, here in case any new potions are added.
- powers:
  - the base game uses "power" to refer to any buff / debuff placed on a creature.
    - (not to be confused with cards -> powers, which is cards of the type "Power". I don't like this naming convention -- I might have gone with "buffs" eg -- but I've kept it for consistency with the base game and other mods I've looked at.)
    - the basic structure of this is taken from the mod template, but all the code here is mine.
  - The classes' names are generally drawn from the card that applies them with "Power" suffixed -- there are exceptions though, especially for common powers applied from multiple sources.
  - the folder also contains the AbstractSleeperPower class, an intermediate subclass from which all these powers inherit.
- relics:
  - extra relics which are each then loaded in the SleeperMod class.
  - all relics here inherit frmo AbstractSleeperRelic.
  - there is a sub-folder for deprecated relics.
- screens:
  - contains the class to handle the Forgotten Pile's view screen.
- ui(.panels):
  - contains the class to handle the "panel" for the Forgotten Pile (ie the button to view it).

## src -> main -> resources -> sleepermod

This folder contains some image files and json objects with text strings, which provide the images and text that appear on cards, power icons, relic icons, etc.. Overall the images are incomplete (I've only added them for a couple of cards, and the mod template provides the ones used for other cards) but the text in the package "localization.eng" is more or less up-to-date.

##Previous ReadMe

Below is the ReadMe that the mod template came with, for full transparency.

# Basic Mod

This is an empty Slay the Spire mod + a modding tutorial.

This tutorial will help with setup and the basics of Slay the Spire modding, but it will not teach you Java. If you know nothing of Java or programming in general, you are strongly recommended to look up a free online course and do at least some of it. It is possible to do modding with almost no proper knowledge, but it will make things much more difficult.

---

## Check the wiki to get started:

https://github.com/Alchyr/BasicMod/wiki

---

## Know what you're doing?

You can still use this mod as a base, or you could use another template like https://github.com/DarkVexon/ProTemplate

You can find more options in the pins of the #modding-technical channel in the Slay the Spire discord server.

---

### Some HD Slay the Spire art assets (courtesy of Gremious, creator of DefaultMod):

Includes:
- Empty Relic Template feat. empty bottle
- Empty Card Template
- Color-Changable cardback
- A couple of HD Monster vectors (Louse, Nob, Sentry, Sneaky Gremlin)
- A coupe of HD items (J.A.X., A Coin)
- 2 people silhouettes
- A curse Background

https://github.com/Gremious/StS-DefaultModBase#some-hd-slay-the-spire-art-assets

---
