package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class SpringCleaningAction extends AbstractGameAction {
    public AbstractPlayer p;

    public SpringCleaningAction() {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }


    public void update () {
        for (AbstractCard c : AwakenAction.awakenedCards) {
            addToTop(new ExhaustSpecificCardAction(c,p.hand));
            if (c.costForTurn == -1) {
                addToTop(new GainEnergyAction(EnergyPanel.getCurrentEnergy()));
            } else if (c.costForTurn > 0) {
                addToTop(new GainEnergyAction(c.costForTurn));
            }
        }
    }
}
