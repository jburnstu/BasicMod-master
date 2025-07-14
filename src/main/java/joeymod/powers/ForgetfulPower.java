package joeymod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ForgetfulPower extends AbstractPower {
    public static final String POWER_ID = "Forgetful";

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Weakened");

    public static final String NAME = powerStrings.NAME;

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean justApplied = false;

    private static final int EFFECTIVENESS_STRING = 25;

    public ForgetfulPower(AbstractCreature owner, int amount, boolean isSourceMonster) {
        this.name = NAME;
        this.ID = "Forgetful";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("weak");
        if (isSourceMonster)
            this.justApplied = true;
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
            addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Weakened"));
        } else {
            addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Weakened", 1));
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane")) {
                this.description = DESCRIPTIONS[0] + '(' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
            } else {
                this.description = DESCRIPTIONS[0] + '\031' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
            }
        } else if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane")) {
            this.description = DESCRIPTIONS[0] + '(' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
        } else {
            this.description = DESCRIPTIONS[0] + '\031' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
        }
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            if (!this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane"))
                return damage * 0.6F;
            return damage * 0.75F;
        }
        return damage;
    }
}
