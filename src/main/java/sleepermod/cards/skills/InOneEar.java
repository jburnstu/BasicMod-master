package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.ForgetTopCardOfDiscardAction;
import sleepermod.actions.InOneEarAction;
import sleepermod.actions.ForgetAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.cards.statuses.Dizzy;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;


public class InOneEar extends AbstractSleeperCard {
    public static final String ID = makeID(InOneEar.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.COMMON, 
            CardTarget.SELF, 
            1 
    );

    private int baseMagicNumber = 2;
    private int magicUpgrade = 1;

    public InOneEar() {
        super(ID, info); 
        setMagic(baseMagicNumber, magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ForgetAction(1,false,false,false));
        addToBot(new DrawCardAction(magicNumber));
    }
}