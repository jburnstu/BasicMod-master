package joeymod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import joeymod.cards.ForgottenCard;

import static joeymod.JoeyBasicMod.makeID;

public class AmnesiaPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(AmnesiaPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public AmnesiaPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

//    public void onPlayCard(AbstractCard c, AbstractMonster m) {
//        if (c instanceof ForgottenCard) {
//            ((ForgottenCard) c).backForgottenCard.exhaustOnUseOnce = true;
//        }
//    }

    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (c instanceof ForgottenCard) {
            flash();
            ((ForgottenCard) c).frontForgottenCard.exhaustOnUseOnce = true;
            this.amount--;
            if (this.amount == 0) {
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Double Tap"));
            }
        }
    }
}
