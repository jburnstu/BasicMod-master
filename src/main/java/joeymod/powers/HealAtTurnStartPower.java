package joeymod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RegenAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import joeymod.cards.ForgottenCard;

import static joeymod.JoeyBasicMod.makeID;


// Whenever you draw a forgotten card, gain 1 vigour.
public class HealAtTurnStartPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(HealAtTurnStartPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    public boolean reduceAtEndOfTurn = true;


    public HealAtTurnStartPower(AbstractCreature owner, AbstractCreature source, int amount) {

        super(POWER_ID, TYPE, false, owner, amount);
        this.source = source;
    }

    @Override
    public void atStartOfTurn() {
        if (this.amount > 0 ) {
            addToTop(new HealAction(this.owner,this.source,this.amount));
        }
        addToBot(new RemoveSpecificPowerAction(this.owner,this.source,this.ID));
        }



}