package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// X damage per card remembered this combat
public class PsychicTsunami extends AbstractSleeperCard {
    public static final String ID = makeID(PsychicTsunami.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.UNCOMMON, 
            CardTarget.ENEMY, 
            1 
    );

//    private static final int DAMAGE = 2;
//    private static final int UPG_DAMAGE = 2;
    private static final int baseMagicNumber = 4;
    private static final int magicUpgrade = 1;

    public PsychicTsunami() {
        super(ID, info); 
        this.magicNumber = baseMagicNumber;
        setMagic(magicNumber, magicUpgrade);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        int numberOfForgottenCards = ((MySleeperPlayer) AbstractDungeon.player).cardsRememberedThisCombat.size();
//        damage = numberOfForgottenCards * this.magicNumber;
        calculateCardDamage(m);
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
                System.out.println("Psychic Tsunami dealt" + damage);
    }

    public void applyPowers() {
        int numberOfRememberedCards = ((MySleeperPlayer) AbstractDungeon.player).cardsRememberedThisCombat.size();
        if (numberOfRememberedCards > 0) {
            this.baseDamage = numberOfRememberedCards * this.magicNumber;
            super.applyPowers();
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + numberOfRememberedCards;
            if (numberOfRememberedCards == 1) {
                this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
            } else {
                this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
            }
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


    public AbstractCard makeCopy() {
        return new PsychicTsunami();
    }
}
