package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class SpringCleaningAction extends AbstractGameAction {

    private int magicNumber;

    public AbstractPlayer p;

    public SpringCleaningAction(int magicNumber) {
        this.magicNumber = magicNumber;
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }


    public void update () {
        for (AbstractCard c : AwakenAction.awakenedCards) {
            addToTop(new ExhaustSpecificCardAction(c,p.hand));
        }
    }
}
