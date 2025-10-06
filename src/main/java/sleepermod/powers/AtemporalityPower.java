package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sleepermod.SleeperMod.makeID;


// Whenever you forget a card, draw a card.
public class AtemporalityPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(AtemporalityPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public boolean usedThisTurn = false;

    public AtemporalityPower(AbstractCreature owner, int amount) {

        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        usedThisTurn = false;
    }

//    @Override
//    public void onPlayCard(AbstractCard card, AbstractMonster m) {
//        if (!usedThisTurn && card instanceof ForgottenCard) {
//            addToTop(new DrawCardAction(this.owner, this.amount));
//            usedThisTurn = true;
//        }
//    }

    @Override
    public void onRemember(AbstractCard card, AbstractCreature m) {
        addToTop(new DrawCardAction(this.owner, this.amount));
    }

}