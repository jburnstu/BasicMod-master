package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sleepermod.cards.ForgottenCard;

public class ReinterpretAction extends AbstractGameAction {

    private int magicNumber;

    public AbstractPlayer p;

    public ReinterpretAction() {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }


    public void update () {
        CardGroup tmp = new CardGroup(AbstractDungeon.player.discardPile, CardGroup.CardGroupType.UNSPECIFIED);
        tmp.shuffle(AbstractDungeon.shuffleRng);
        for (AbstractCard c : tmp.group) {
            if (c instanceof ForgottenCard) {
                this.p.discardPile.removeCard(c);
                this.p.discardPile.moveToDeck(c, false);
                break;
            }
        }
    }
}
