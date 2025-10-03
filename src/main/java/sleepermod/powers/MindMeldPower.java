package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static sleepermod.SleeperMod.makeID;


// Whenever you draw a forgotten card, gain 1 vigour.
public class MindMeldPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(MindMeldPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public AbstractCreature source;

    public MindMeldPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
        this.source = source;
    }

    @Override
    public void onAwaken(AbstractCard card) {
       addToTop(new ApplyPowerAction(this.owner,this.source,new InsomniaPower(this.owner,1)));
    }
}