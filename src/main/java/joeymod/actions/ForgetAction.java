package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;

public class ForgetAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");

    public static final String[] TEXT = uiStrings.TEXT;

    private MySleeperPlayer p;

    private boolean isRandom;

    private boolean anyNumber;

    private boolean canPickZero;

    public static int numForgotten;

    public ForgetAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this.anyNumber = anyNumber;
        this.p = (MySleeperPlayer) AbstractDungeon.player;
        this.canPickZero = canPickZero;
        this.isRandom = isRandom;
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.SPECIAL;
    }

    public ForgetAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber) {
        this(amount, isRandom, anyNumber);
        this.target = target;
        this.source = source;
    }

    public ForgetAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
        this(amount, isRandom, false, false);
        this.target = target;
        this.source = source;
    }

    public ForgetAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this(amount, isRandom, anyNumber, canPickZero);
        this.target = target;
        this.source = source;
    }

    public ForgetAction(boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this(99, isRandom, anyNumber, canPickZero);
    }

    public ForgetAction(int amount, boolean canPickZero) {
        this(amount, false, false, canPickZero);
    }

    public ForgetAction(int amount, boolean isRandom, boolean anyNumber) {
        this(amount, isRandom, anyNumber, false);
    }

    public ForgetAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero, float duration) {
        this(amount, isRandom, anyNumber, canPickZero);
        this.duration = this.startDuration = duration;
    }

    public void update() {
        ForgottenCard newForgottenCard = new ForgottenCard();
        CardGroup hand = this.p.hand;
        if (this.duration == this.startDuration) {
            System.out.println(this.p.hand.group);
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (!this.anyNumber &&
                    hand.size() <= this.amount) {
                this.amount = hand.size();
                numForgotten = this.amount;
                int tmp = hand.size();
                for (int i = 0; i < tmp; i++) {
                    AbstractCard c = hand.getTopCard();
                    newForgottenCard = Move.toForgottenPile(hand,c,true);
                }
                return;
            }
            if (this.isRandom) {
                for (int i = 0; i < this.amount; i++)
                    newForgottenCard = Move.toForgottenPile(hand,hand.getRandomCard(AbstractDungeon.cardRandomRng),true);
            } else {
                numForgotten = this.amount;
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], numForgotten, this.anyNumber, this.canPickZero);
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group)
                newForgottenCard = Move.toForgottenPile(hand,c,true);
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }
}
