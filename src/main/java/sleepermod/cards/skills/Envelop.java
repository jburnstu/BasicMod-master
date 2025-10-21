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
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            1 
    );

    private static final int baseMagicNumber = 2;
    public static final int magicUpgrade = 1;

    public Envelop() {
        super(ID, info); 
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
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + numberOfForgottenCards;
        if (numberOfForgottenCards == 1) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }


    public AbstractCard makeCopy() {
        return new Envelop();
    }
}
