package sleepermod.cards.skills;


import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.ForgetSpecificCardAction;
import sleepermod.actions.ForgetTopCardOfDiscardAction;
import sleepermod.actions.MakeTempForgottenCardInDiscardAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.cards.statuses.Dizzy;
import sleepermod.cards.statuses.Trauma;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// Deal X damage. Shuffle a trauma into your deck
public class ExposureTherapy extends AbstractSleeperCard {
    public static final String ID = makeID(ExposureTherapy.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.COMMON, 
            CardTarget.SELF, 
            0 
    );

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 4;
    public static final int magicNumber = 1;


    public ExposureTherapy() {
        super(ID, info); 
        setBlock(BLOCK, UPG_BLOCK); 
        this.cardsToPreview = new Trauma();
        setMagic(magicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        addToBot(new MakeTempCardInDiscardAction(new Trauma(),1));
        addToBot(new ForgetTopCardOfDiscardAction(1));
    }



}