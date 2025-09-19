package sleepermod.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import sleepermod.cards.ForgottenCard;

import java.util.ArrayList;

import static sleepermod.SleeperMod.makeID;


// Gain 2 block at end of turn for every forgotten card in your hand.
public class SleepingMouse extends AbstractSleeperRelic {
    public static final String ID = makeID(SleepingMouse.class.getSimpleName());
    private static final RelicTier RARITY = RelicTier.RARE; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public SleepingMouse() {
        super(ID, RARITY,SOUND);
    }

    public String getUpdatedDescription() {
        return "Try this";
    }


    public AbstractRelic makeCopy() {
        return new SleepingMouse();
    }

    public ArrayList<AbstractCard> cardsToRemainForgotten;


    public void onPlayerEndTurn() {
        int blockPerForgottenCard = 2;
        int block = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ForgottenCard) {
                block += blockPerForgottenCard;
            }
        }
        if (!(block == 0)) {
            flash();
            addToBot(new GainBlockAction(AbstractDungeon.player, null,block));
        }
    }
}