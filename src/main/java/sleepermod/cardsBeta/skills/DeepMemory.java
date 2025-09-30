package sleepermod.cardsBeta.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.DeepMemoryAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.ComaPower;
import sleepermod.powers.TrancePower;
import sleepermod.util.CardStats;

//Forgotten cards cost zero this turn. This turn, when you draw a draw a non-forgotten card, discard it.
public class DeepMemory extends AbstractSleeperCard {
    public static final String ID = makeID(DeepMemory.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            3 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    public boolean baseExhaust = true;
    public boolean upgradeExhaust = false;

    public DeepMemory() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setExhaust(baseExhaust,upgradeExhaust);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DeepMemoryAction(p));
    }
}