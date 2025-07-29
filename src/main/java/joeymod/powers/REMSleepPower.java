package joeymod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import joeymod.cards.ForgottenCard;

import static joeymod.JoeyBasicMod.makeID;


// Whenever you draw a forgotten card, gain 1 vigour.
public class REMSleepPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(REMSleepPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;


    public REMSleepPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card instanceof ForgottenCard) {
            addToTop(new ApplyPowerAction(this.owner,this.owner, new VigorPower(this.owner,1)));
        }
    }

}