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

    private final boolean upgrade;

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhumeAction");

    public static final String[] TEXT = uiStrings.TEXT;

    private ArrayList<AbstractCard> recalls = new ArrayList<>();

    public RecollectAction(AbstractPlayer p, int magicNumber) {
        this.p = AbstractDungeon.player;
        setValues((AbstractCreature)this.p, (AbstractCreature)AbstractDungeon.player, this.amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public update() {
        CardGroup f = ((MySleeperPlayer) this.p).forgottenPile;
        if (this.duration == Settings.ACTION_DUR_FAST) {
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
                if (((AbstractCard)f.group.get(0)).cardID.equals("Exhume")) {
                    this.isDone = true;
                    return;
                }
                AbstractCard abstractCard = f.getTopCard();
                abstractCard.unfadeOut();
                this.p.hand.addToHand(abstractCard);
                if (AbstractDungeon.player.hasPower("Corruption") && abstractCard.type == AbstractCard.CardType.SKILL)
                    abstractCard.setCostForTurn(-9);
                f.removeCard(abstractCard);
                abstractCard.unhover();
                abstractCard.fadingOut = false;
                this.isDone = true;
                return;
            }
            for (AbstractCard abstractCard : f.group) {
                abstractCard.stopGlowing();
                abstractCard.unhover();
                abstractCard.unfadeOut();
            }
            if (f.isEmpty()) {
                f.group.addAll(this.recalls);
                this.recalls.clear();
                this.isDone = true;
                return;
            }
            AbstractDungeon.gridSelectScreen.open(f, 1, TEXT[0], false);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                this.p.hand.addToHand(c);
                if (AbstractDungeon.player.hasPower("Corruption") && c.type == AbstractCard.CardType.SKILL)
                    c.setCostForTurn(-9);
                f.removeCard(c);
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
        }
        tickDuration();
    }
}
