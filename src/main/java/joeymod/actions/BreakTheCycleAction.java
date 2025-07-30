package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class BreakTheCycleAction extends AbstractGameAction {

    private int magicNumber;

    public AbstractPlayer p;

    public BreakTheCycleAction(int magicNumber) {
        System.out.println("FA constructor called...");
        this.magicNumber = magicNumber;
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }


    public void update () {
        System.out.println("FA Update reached...");
        if (RecollectAction.recalledCards.get(0).type == AbstractCard.CardType.ATTACK) {
            addToTop(new GainBlockAction(p,RecollectAction.recalledCards.get(0).damage));
        }
        System.out.println("done with update...");
        this.isDone = true;
    }
}