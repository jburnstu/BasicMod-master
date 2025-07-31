package joeymod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import joeymod.cards.ForgottenCard;

public class AmnesiaPower extends AbstractPower {
    public static final String POWER_ID = "Amnesia";

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Weakened");

    public static final String NAME = powerStrings.NAME;

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean justApplied = false;

    private static final int EFFECTIVENESS_STRING = 25;

    public AmnesiaPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Amnesia";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("weak");
        this.type = AbstractPower.PowerType.DEBUFF;
        this.isTurnBased = true;
        this.priority = 99;
    }

    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
            return;
        }
        if (this.amount == 0) {
            addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Amnesia"));
        } else {
            addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Amnesia", 1));
        }
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c instanceof ForgottenCard) {
            ((ForgottenCard) c).backForgottenCard.exhaustOnUseOnce = true;
        }
    }
}
