package joeymod.cards.skills;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import joeymod.cards.AbstractSleeperCard;
import joeymod.cards.statuses.Trauma;
import joeymod.character.MySleeperPlayer;
import joeymod.util.CardStats;

// Deal X damage. Shuffle a trauma into your deck
public class ExposureTherapy extends AbstractSleeperCard {
    public static final String ID = makeID(ExposureTherapy.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4 ;
    public static final int magicNumber = 1;


    public ExposureTherapy() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setBlock(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        this.cardsToPreview = new Trauma();
        setMagic(magicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        addToBot(new MakeTempCardInDrawPileAction(new Trauma(), 1, true, true));
    }



}