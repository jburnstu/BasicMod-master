package joeymod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import joeymod.cards.ForgottenCard;

import static joeymod.JoeyBasicMod.makeID;


public class ComaPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(ComaPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    public static final boolean removeAtEnd = true;

    public ComaPower(AbstractCreature owner, int amount) {

        super(POWER_ID, TYPE, true, owner, amount);
        this.removeAtEndOfTurn = removeAtEnd;
    }


    @Override
    public void onCardDraw(AbstractCard c) {
        if (!(c instanceof ForgottenCard)) {
            addToBot(new DiscardSpecificCardAction(c));
        }
    }

}
