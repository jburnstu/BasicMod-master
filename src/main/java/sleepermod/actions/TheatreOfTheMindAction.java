package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sleepermod.cards.ForgottenCard;
import sleepermod.powers.VisionPower;

public class TheatreOfTheMindAction extends AbstractGameAction {
    public int passedBlock;

    public TheatreOfTheMindAction(AbstractPlayer target) {
        this.target = target;
    }

    @Override
    public void update() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ForgottenCard) {
                count++;
            }
        }
        if (!(count ==0)) {
            addToBot(new ApplyPowerAction(this.target,this.target,new VisionPower(this.target,count)));
        }
        this.isDone = true;
    }
}
