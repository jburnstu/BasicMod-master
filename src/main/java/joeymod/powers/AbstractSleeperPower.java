package joeymod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractSleeperPower extends BasePower {

    boolean reduceAtEndOfTurn = false;
    boolean removeAtEndOfTurn = false;

    public AbstractSleeperPower(String id, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount) {
        this(id, powerType, isTurnBased, owner, null, amount);
    }

    public AbstractSleeperPower(String id, PowerType powerType, boolean isTurnBased, AbstractCreature owner, AbstractCreature source, int amount) {
        this(id, powerType, isTurnBased, owner, source, amount, true);
    }

    public AbstractSleeperPower(String id, PowerType powerType, boolean isTurnBased, AbstractCreature owner, AbstractCreature source, int amount, boolean initDescription) {
        this(id, powerType, isTurnBased, owner, source, amount, initDescription, true,false,false);
    }

    public AbstractSleeperPower(String id, PowerType powerType, boolean isTurnBased, AbstractCreature owner, AbstractCreature source, int amount, boolean initDescription, boolean loadImage, boolean reduceAtEnd, boolean removeAtEnd) {
        super(id, powerType, isTurnBased, owner, source, amount, initDescription, loadImage);
        this.reduceAtEndOfTurn = reduceAtEnd;
        this.removeAtEndOfTurn = removeAtEnd;
    }

    public void onForget (AbstractCard card) {}

    public void onAwaken(AbstractCard card) {}

    public void updateDescription() {
        switch (DESCRIPTIONS.length) {
            case 1:
                this.description = DESCRIPTIONS[0];
                break;
            case 2:
                this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
                break;
            case 3:
                if (amount == 1)
                    this.description = DESCRIPTIONS[0];
                else
                    this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
                break;
        }
    }

    public void alterAmountAtEndOfTurn() {
        if (this.reduceAtEndOfTurn) {
            addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        } else if (this.removeAtEndOfTurn){
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        alterAmountAtEndOfTurn();
    }
}
