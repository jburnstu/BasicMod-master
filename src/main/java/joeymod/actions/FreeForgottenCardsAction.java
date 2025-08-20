package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import joeymod.character.MySleeperPlayer;
import joeymod.patches.AbstractCardBackForgottenCardPatch;

public class FreeForgottenCardsAction extends AbstractGameAction {
    public AbstractPlayer owner;

    public FreeForgottenCardsAction(AbstractPlayer p) {
        this.owner = p;
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        for (AbstractCard c : ((MySleeperPlayer) owner).forgottenPile.group) {
            AbstractCardBackForgottenCardPatch.backForgottenCard.get(c).setCostForTurn(0);
        }
    }
}
