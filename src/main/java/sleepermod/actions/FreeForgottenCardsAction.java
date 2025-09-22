package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import sleepermod.character.MySleeperPlayer;
import sleepermod.patches.coremechanics.FieldPatchAbstractCardBackForgottenCard;

public class FreeForgottenCardsAction extends AbstractGameAction {
    public AbstractPlayer owner;

    public FreeForgottenCardsAction(AbstractPlayer p) {
        this.owner = p;
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        for (AbstractCard c : ((MySleeperPlayer) owner).forgottenPile.group) {
            FieldPatchAbstractCardBackForgottenCard.backForgottenCard.get(c).setCostForTurn(0);
        }
    }
}
