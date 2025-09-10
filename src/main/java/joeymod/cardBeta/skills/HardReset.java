package joeymod.cardBeta.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import joeymod.actions.HardResetAction;
import joeymod.cards.AbstractSleeperCard;
import joeymod.character.MySleeperPlayer;
import joeymod.util.CardStats;

//Urgent. Lose all your block. Gain 2 energy. draw three cards.
public class HardReset extends AbstractSleeperCard {
    public static final String ID = makeID(HardReset.class.getSimpleName());
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
    private int magicNumber = 2;

    public HardReset() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        this.magicNumber = magicNumber;
        this.urgent = true;
        setMagic(magicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HardResetAction(p, this.upgraded, this.freeToPlayOnce, this.energyOnUse));
//        addToBot(new RemoveAllBlockAction(p,p)); //how to get this?
//        addToBot(new GainEnergyAction(2));
//        addToBot(new DrawCardAction(3));
    }
}