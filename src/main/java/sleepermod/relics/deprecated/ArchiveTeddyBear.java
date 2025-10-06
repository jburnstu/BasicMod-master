package sleepermod.relics.deprecated;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import sleepermod.actions.Move;
import sleepermod.cards.ForgottenCard;
import sleepermod.relics.AbstractSleeperRelic;

import java.util.ArrayList;

import static sleepermod.SleeperMod.makeID;

// When you recollect and no cards in forgottenPile,: add a new card to your hand (cost 1 less?)
public class ArchiveTeddyBear extends AbstractSleeperRelic {
    public static final String ID = makeID(ArchiveTeddyBear.class.getSimpleName());

    public ArchiveTeddyBear() {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    public ArrayList<AbstractCard> cardsToForget = new ArrayList<>();
    public AbstractPlayer p;
    public boolean activated = false;

    public AbstractRelic makeCopy() {
        return new ArchiveTeddyBear();
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
            p = AbstractDungeon.player;
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