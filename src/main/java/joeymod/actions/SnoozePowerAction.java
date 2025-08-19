package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SnoozePowerAction extends AbstractGameAction {

    public CardGroup cardsToDisplay = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);;

    public int numberOfCards;

    private float startingDuration;

    public SnoozePowerAction(int numberOfCards) {
        this.numberOfCards = numberOfCards;
        cardsToDisplay.group = DrawCardAction.drawnCards;
        this.duration = 0.0F;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == this.startingDuration) {
            AbstractDungeon.gridSelectScreen.open(cardsToDisplay, this.numberOfCards,true, "Choose a card(s) to forget");
        }
        else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards)
                addToBot(new ForgetSpecificCardAction(c,AbstractDungeon.player.hand));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        tickDuration();
    }
}

