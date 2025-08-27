package joeymod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import joeymod.cards.AbstractSleeperCard;
import joeymod.powers.AmnesiaPower;
import joeymod.character.MySleeperPlayer;
import joeymod.util.CardStats;

//Gain 1 energy. gain 1 amnesia.
public class CopingMechanism extends AbstractSleeperCard {
    public static final String ID = makeID(CopingMechanism.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            0 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private int baseMagicNumber = 1;
    private int magicUpgrade = 1;

    public CopingMechanism() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new ApplyPowerAction(p,p,new AmnesiaPower(p,1)));
        addToBot(new GainEnergyAction(magicNumber));
    }
}