package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.cards.ForgottenCard;
import sleepermod.cards.skills.Slumber;
import sleepermod.character.MySleeperPlayer;

import java.util.ArrayList;

public class ForgetAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");

//    public static final String[] TEXT = uiStrings.TEXT;

    private MySleeperPlayer p;

    private boolean isRandom;

    private boolean anyNumber;

    private boolean canPickZero;

    public static int numForgotten;

    public AbstractGameAction followUpAction = null;

    public static ArrayList<AbstractCard> forgottenCards = new ArrayList<>();

    public static boolean onlyAttacks;

    public ForgetAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this.anyNumber = anyNumber;
        this.p = (MySleeperPlayer) AbstractDungeon.player;
        this.canPickZero = canPickZero;
        this.isRandom = isRandom;
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.SPECIAL;
        this.onlyAttacks = false;
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

    public ForgetAction(int amount, AbstractGameAction followUpAction) {
        this(amount,false);
        this.followUpAction = followUpAction;
    }

    public ForgetAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero, boolean onlyAttacks, AbstractGameAction followUpAction) {
        this(amount, isRandom, anyNumber, canPickZero);
        this.followUpAction = followUpAction;
        this.onlyAttacks = onlyAttacks;
    }


    public void update() {
        forgottenCards.clear();
        ForgottenCard newForgottenCard = new ForgottenCard();
        CardGroup forgettable = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c: this.p.hand.group) {
            if (!(c instanceof ForgottenCard)) {
                System.out.println("first forget if passed -- only attacks value is " + this.onlyAttacks);
                if (!(this.onlyAttacks) || c.type == AbstractCard.CardType.ATTACK) {
                    System.out.println("second forget if passed");
                    forgettable.addToTop(c);
                }
            }
        }
        if (this.duration == this.startDuration) {
            if (forgettable.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (!this.anyNumber &&
                    forgettable.size() <= this.amount) {
                this.amount = forgettable.size();
                numForgotten = this.amount;
                int tmp = forgettable.size();
                for (int i = 0; i < tmp; i++) {
                    AbstractCard c = forgettable.getTopCard();
                    Move.toForgottenPile(this.p.hand,c,true);
                    forgottenCards.add(c);
                    forgettable.removeCard(c);
                }
                endActionWithFollowUp();
                return;
            }
            if (this.isRandom) {
                for (int i = 0; i < this.amount; i++) {
                    AbstractCard c = forgettable.getRandomCard(AbstractDungeon.cardRandomRng);
                    Move.toForgottenPile(this.p.hand, c, true);
                    forgottenCards.add(c);
                    forgettable.removeCard(c);
                    this.p.hand.refreshHandLayout();
                    this.p.hand.applyPowers();
                }
                endActionWithFollowUp();
            } else {
                numForgotten = this.amount;
                AbstractDungeon.gridSelectScreen.open(forgettable, numForgotten, "Choose a card(s) to forget",this.anyNumber, this.canPickZero);
                tickDuration();
                return;
            }
        }
        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                c.unhover();
                Move.toForgottenPile(this.p.hand, c, true);
                forgottenCards.add(c);
                this.p.hand.refreshHandLayout();
                this.p.hand.applyPowers();
            }
            endActionWithFollowUp();
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.p.hand.refreshHandLayout();
        }
        tickDuration();
    }

    private void endActionWithFollowUp() {
        this.isDone = true;
        if (this.followUpAction != null) {
            addToTop(this.followUpAction);
        }
    }
}
