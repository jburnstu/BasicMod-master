package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class AndAnotherThingAction extends AbstractGameAction {

    private int magicNumber;

    public AbstractPlayer p;

    public AndAnotherThingAction(int magicNumber) {
        this.magicNumber = magicNumber;
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }


    public void update () {
        System.out.println("FA Update reached...");
        if (RecollectAction.recalledCards.get(0).cost >= this.magicNumber) {
            addToTop(new RecollectAction(1,false));
        }
        System.out.println("done with update...");
        this.isDone = true;
    }
}