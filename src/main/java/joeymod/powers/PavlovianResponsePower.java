package joeymod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import joeymod.cards.ForgottenCard;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;
import static joeymod.JoeyBasicMod.makeID;


public class PavlovianResponsePower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(PavlovianResponsePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;


    public PavlovianResponsePower(AbstractCreature owner, int amount) {

        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c instanceof ForgottenCard && ((ForgottenCard) c).forgottenCard.type == AbstractCard.CardType.ATTACK) {
            addToTop(new ApplyPowerAction(this.owner, this.owner, new GainStrengthPower(this.owner,this.amount)));
        }

    }

}