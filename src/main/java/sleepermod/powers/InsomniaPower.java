package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static sleepermod.SleeperMod.makeID;



public class InsomniaPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(InsomniaPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    public boolean reduceAtEnd = false;

    public InsomniaPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
        this.reduceAtEndOfTurn = reduceAtEnd;
    }

//    @Override
//    public void atEndOfTurn(boolean isPlayer) {
//        if (this.amount >= this.owner.currentHealth) {
//            addToBot(new LoseHPAction(this.owner, this.owner, this.owner.currentHealth));
//        } else {
//            addToBot(new ApplyPowerAction(this.owner, AbstractDungeon.player, new InsomniaPower(this.owner, this.amount)));
//        }
//    }
//
//    @Override
//    public void onForget(AbstractCard c) {
//        this.amount--;
//    }
        @Override
    public void onForget(AbstractCard c) {
        addToTop(new LoseHPAction(this.owner,null,this.amount));
        this.amount--;
    }

}


