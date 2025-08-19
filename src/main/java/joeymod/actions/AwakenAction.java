package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import joeymod.character.MySleeperPlayer;

import java.util.ArrayList;

public class AwakenAction extends AbstractGameAction {

    private AbstractPlayer p;

    private boolean isRandom;

    public AbstractGameAction followUpAction = null;

    public static ArrayList<AbstractCard> awakenedCards = new ArrayList<>();

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AwakenAction");

    public AwakenAction(int amount, boolean isRandom) {
        System.out.println("Constructor starting...");
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.isRandom = isRandom;
        setValues(this.p, AbstractDungeon.player, this.amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public AwakenAction(int amount, AbstractGameAction followUpAction) {
        this(amount,false);
        this.followUpAction = followUpAction;
    }

    public void update() {
//        System.out.println("update starting...");
        awakenedCards.clear();
        CardGroup f = ((MySleeperPlayer) this.p).forgottenPile;
//        System.out.println("Checking duration...");
        if (this.duration == Settings.ACTION_DUR_FAST) {
//            System.out.println("AwakenAction starting...");
            if (AbstractDungeon.player.hand.size() == 10) {
                AbstractDungeon.player.createHandIsFullDialog();
                this.isDone = true;
                return;
            }
            if (f.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (f.size() == 1) {
                AbstractCard c = f.getTopCard();
                c.unfadeOut();
                Move.fromForgottenPile(c);
                awakenedCards.add(c);
                endActionWithFollowUp();
                c.unhover();
                c.fadingOut = false;
                this.isDone = true;
                return;
            }
//            System.out.println("Past if block which shouldn't trigger...");
            for (AbstractCard c : f.group) {
//                System.out.println("Inside suspicious for loop...");
                c.stopGlowing();
                c.unhover();
                c.unfadeOut();
            }

            System.out.println("creating select screen -- printing f.group....");
            System.out.println(f.group);
            AbstractDungeon.gridSelectScreen.open(f, this.amount, "Choose a card to recollect", false);
            System.out.println("screen successfully created...");
            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                Move.fromForgottenPile(c);
                System.out.println("Adding card to recalledCards...");
                awakenedCards.add(c);
                c.unhover();
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.p.hand.refreshHandLayout();
            for (AbstractCard c : f.group) {
                c.unhover();
                c.target_x = CardGroup.DISCARD_PILE_X;
                c.target_y = 0.0F;
            }
        endActionWithFollowUp();
        System.out.println("Escaped followUp block...");
        return;
        }
        tickDuration();
    }


    private void endActionWithFollowUp() {
        System.out.println("Reached endActionWithFollowUp...");
        System.out.println("this.followUpAction:" + this.followUpAction);
        this.isDone = true;
        if (this.followUpAction != null) {
            System.out.println("Action queued...");
            addToTop(this.followUpAction);
        }
        System.out.println("Passed the followUp...");


    }
}

