package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.GrandRitualAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.GrandRitualPower;
import sleepermod.util.CardStats;

//X awaken X vision
public class GrandRitual extends AbstractSleeperCard {
    public static final String ID = makeID(GrandRitual.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            -1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private int baseMagicNumber = 0;
    private int magicUpgrade = 1;
    public boolean baseExhaust = true;
    public boolean upgradeExhaust = true;


    public GrandRitual() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setExhaust(baseExhaust,upgradeExhaust);
        setMagic(baseMagicNumber, magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p, new GrandRitualPower(p,this.energyOnUse+magicUpgrade)));
    }
}