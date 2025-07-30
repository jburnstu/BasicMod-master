package joeymod.cards.cardBeta.skills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import joeymod.actions.RecollectAction;
import joeymod.cards.AbstractSleeperCard;
import joeymod.actions.ForeshadowingAction;
import joeymod.character.MySleeperPlayer;
import joeymod.util.CardStats;

//Recollect 1. If it's an attack, apply 1 vulnerable to all enemies.
public class Foreshadowing extends AbstractSleeperCard {
    public static final String ID = makeID(Foreshadowing.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private int baseMagicNumber = 2;
    private RecollectAction recollectAction;


    public Foreshadowing() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        this.magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        System.out.println("Recollect Card Called...");
        addToBot(new RecollectAction(this.magicNumber, (AbstractGameAction) new ForeshadowingAction(this.magicNumber)));
        System.out.println("This shouldn't trigger until we're done..."+RecollectAction.recalledCards);
    }
}
