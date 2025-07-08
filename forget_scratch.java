package com.megacrit.cardcrawl.actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class ForgetAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");

    public static final String[] TEXT = uiStrings.TEXT;

    private AbstractPlayer p;

    private boolean isRandom;

    private boolean anyNumber;

    private boolean canPickZero;

    public static int numForgotten;

    public ForgetAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this.anyNumber = anyNumber;
        this.p = AbstractDungeon.player;
        this.canPickZero = canPickZero;
        this.isRandom = isRandom;
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.FORGET;
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
        if (this.duration == this.startDuration) {
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }
            if (!this.anyNumber &&
                    this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();
                numForgotten = this.amount;
                int tmp = this.p.hand.size();
                for (int i = 0; i < tmp; i++) {
                    AbstractCard c = this.p.hand.getTopCard();
                    this.p.hand.moveToExhaustPile(c);
                }
                return;
            }
            if (this.isRandom) {
                for (int i = 0; i < this.amount; i++)
                    this.p.hand.moveToExhaustPile(this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng));
            } else {
                numForgotten = this.amount;
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, this.anyNumber, this.canPickZero);
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group)
                this.p.hand.moveToExhaustPile(c);
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }
}

// For CardGroup.class
public void moveToForgetPile(AbstractCard c) {
    for (AbstractRelic r : AbstractDungeon.player.relics)
        r.onForget(c); // add to abstractRelic
    for (AbstractPower p : AbstractDungeon.player.powers)
        p.onForget(c); // add to abstractPower
    c.triggerOnForget(); // add to abstractCard
    // resetCardBeforeMoving(c); //Removing this as we want the card to stay exactly as it was
    // AbstractDungeon.effectList.add(new ForgetCardEffect(c)); //Removing to avoid having to do effects
    AbstractDungeon.player.ForgetPile.addToTop(c);

    AbstractCard s = (new Dream(c)).makeCopy();
    addToTop((AbstractGameAction)new MakeTempCardInHandAction(s, theSize));

    AbstractDungeon.player.onCardDrawOrDiscard();
}