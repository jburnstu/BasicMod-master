package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import joeymod.character.MySleeperPlayer;

import java.util.ArrayList;
import java.util.Iterator;

public class RecollectAction extends AbstractGameAction {

    private AbstractPlayer p;

    private boolean isRandom;

    public AbstractGameAction followUpAction = null;

    public static ArrayList<AbstractCard> recalledCards = new ArrayList<>();

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RecollectAction");

    private ArrayList<AbstractCard> recalls = new ArrayList<>();

    public RecollectAction(int amount, boolean isRandom) {
        System.out.println("Constructor starting...");
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.isRandom = isRandom;
        setValues((AbstractCreature)this.p, (AbstractCreature)AbstractDungeon.player, this.amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        System.out.println("Constructor complete...");
    }

    public RecollectAction(int amount, AbstractGameAction followUpAction) {
        this(amount,false);
        this.followUpAction = followUpAction;
    }

    public void update() {
        System.out.println("update starting...");
        recalledCards.clear();
        CardGroup f = ((MySleeperPlayer) this.p).forgottenPile;
        System.out.println("Checking duration...");
        if (this.duration == Settings.ACTION_DUR_FAST) {
            System.out.println("RecollectAction starting...");
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
                AbstractCard abstractCard = f.getTopCard();
                abstractCard.unfadeOut();
                Move.fromForgottenPile(abstractCard);
                recalledCards.add(abstractCard);
                endActionWithFollowUp();
                abstractCard.unhover();
                abstractCard.fadingOut = false;
                this.isDone = true;
                return;
            }
            System.out.println("Past if block which shouldn't trigger...");
            for (AbstractCard abstractCard : f.group) {
                System.out.println("Inside suspicious for loop...");
                abstractCard.stopGlowing();
                abstractCard.unhover();
                abstractCard.unfadeOut();
            }
            if (f.isEmpty()) {
                System.out.println("inside second ifempty block...");
                f.group.addAll(this.recalls);
                this.recalls.clear();
                this.isDone = true;
                return;
            }
            System.out.println("creating select screen....");
            AbstractDungeon.gridSelectScreen.open(f, 1, "Choose a card to recollect", false);
            System.out.println("screen successfully created...");
            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                Move.fromForgottenPile(c);
                System.out.println("Adding card to recalledCards...");
                recalledCards.add(c);
                c.unhover();
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.p.hand.refreshHandLayout();
            f.group.addAll(this.recalls);
            this.recalls.clear();
            for (AbstractCard c : f.group) {
                c.unhover();
                c.target_x = CardGroup.DISCARD_PILE_X;
                c.target_y = 0.0F;
            }
        endActionWithFollowUp();
        }
        tickDuration();
    }


    private void endActionWithFollowUp() {
        this.isDone = true;
        if (this.followUpAction != null)
            addToTop(this.followUpAction);
    }
}

