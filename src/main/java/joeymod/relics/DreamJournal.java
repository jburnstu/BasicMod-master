package joeymod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import joeymod.actions.Move;
import joeymod.patches.AbstractCardBackForgottenCardPatch;

import java.util.ArrayList;

import static joeymod.JoeyBasicMod.makeID;


// Forgotten cards remain forgotten in between combats.
public class DreamJournal extends AbstractSleeperRelic {
    public static final String ID = makeID(DreamJournal.class.getSimpleName());
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public DreamJournal() {
        super(ID, RARITY,SOUND);
        System.out.println("TeddyBear constructor called....");
    }

    public String getUpdatedDescription() {
        return "Try this";
    }


    public AbstractRelic makeCopy() {
        return new DreamJournal();
    }

    public ArrayList<AbstractCard> cardsToRemainForgotten;


    @Override
    public void atBattleStartPreDraw () {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (cardsToRemainForgotten.contains(c)) {
                Move.toForgottenPile(AbstractDungeon.player.drawPile,c,false);
            }
        }
        cardsToRemainForgotten.clear();
    }

    @Override
    public void onVictory () {
        flash();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (!(AbstractCardBackForgottenCardPatch.backForgottenCard.get(c) == null)) {
                cardsToRemainForgotten.add(c);
            }
        }
    }
}