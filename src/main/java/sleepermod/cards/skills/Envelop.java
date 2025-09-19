package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// 1 block per forgotten card in deck at the moment
public class Envelop extends AbstractSleeperCard {
    public static final String ID = makeID(Envelop.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int baseMagicNumber = 1;
    public static final int magicUpgrade = 1;

    public Envelop() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        this.baseBlock = 0;
        setMagic(baseMagicNumber,magicUpgrade);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int numberOfForgottenCards = ((MySleeperPlayer) AbstractDungeon.player).forgottenPile.size();
        System.out.println("number of forgotten cards" + numberOfForgottenCards);
        this.baseBlock = numberOfForgottenCards * baseMagicNumber;
        addToBot(new GainBlockAction(p, p, this.block));
    }

    public void applyPowers() {
        int numberOfForgottenCards = ((MySleeperPlayer) AbstractDungeon.player).forgottenPile.size();
        System.out.println("number of forgotten cards" + numberOfForgottenCards);
        this.baseBlock = numberOfForgottenCards * baseMagicNumber;
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return new Envelop();
    }
}
