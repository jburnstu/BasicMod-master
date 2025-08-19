package joeymod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import joeymod.actions.ForgetAction;
import joeymod.actions.Move;
import joeymod.cards.ForgottenCard;

import java.util.ArrayList;

import static joeymod.JoeyBasicMod.makeID;

// When you recollect and no cards in forgottenPile,: add a new card to your hand (cost 1 less?)
public class TeddyBear extends AbstractSleeperRelic {
    public static final String ID = makeID("TeddyBear");

    public TeddyBear() {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    public ArrayList<AbstractCard> cardsToForget = new ArrayList<>();
    public AbstractPlayer p = AbstractDungeon.player;
    public boolean activated = false;

    public AbstractRelic makeCopy() {
        return new TeddyBear();
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStartPreDraw() {
        this.activated = false;
        this.cardsToForget.clear();
    }

    @Override
    public void onPlayerEndTurn() {
        if (!this.activated) {
            this.activated = true;
            flash();
            for (AbstractCard c : p.hand.group) {
                System.out.println("First for loop reached");
                if (!(c instanceof ForgottenCard)){
                    System.out.println("if loop reached");
                    cardsToForget.add(c);
                }
            }
            for (AbstractCard c : cardsToForget) {
                System.out.println("Second for loop reached");
                Move.toForgottenPile(p.hand,c,true);
            }
            addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, cardsToForget.toArray().length)));
        }
    }
}