package joeymod.cards.attacks;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import joeymod.cards.AbstractSleeperCard;
import joeymod.character.MySleeperPlayer;
import joeymod.util.CardStats;

// X damage per card remembered this combat
public class PsychicTsunami extends AbstractSleeperCard {
    public static final String ID = makeID(PsychicTsunami.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
//    private static final int DAMAGE = 2;
//    private static final int UPG_DAMAGE = 2;
    private static final int baseMagicNumber = 1;
    private static final int magicUpgrade = 1;

    public PsychicTsunami() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.forget = true;
        this.magicNumber = baseMagicNumber;
        setMagic(magicNumber, magicUpgrade);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        int numberOfForgottenCards = ((MySleeperPlayer) AbstractDungeon.player).cardsRememberedThisCombat.size();
//        damage = numberOfForgottenCards * this.magicNumber;
        calculateCardDamage(null);
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
                System.out.println("Psychic Tsunami dealt" + damage);
    }

    public void applyPowers() {
        int numberOfForgottenCards = ((MySleeperPlayer) AbstractDungeon.player).cardsRememberedThisCombat.size();
        if (numberOfForgottenCards > 0) {
            this.baseDamage = numberOfForgottenCards * this.magicNumber;
            super.applyPowers();
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return new PsychicTsunami();
    }
}
