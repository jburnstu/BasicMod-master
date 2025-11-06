package sleepermod.relics.deprecated;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import sleepermod.actions.Move;
import sleepermod.patches.coremechanics.FieldPatchAbstractCardBackForgottenCard;
import sleepermod.relics.AbstractSleeperRelic;

import java.util.ArrayList;

import static sleepermod.SleeperMod.makeID;


// Forgotten cards remain forgotten in between combats.
public class ArchiveWideEyedDoll extends AbstractSleeperRelic {
    public static final String ID = makeID(ArchiveWideEyedDoll.class.getSimpleName());
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public ArchiveWideEyedDoll() {
        super(ID, RARITY,SOUND);
        System.out.println("TeddyBear constructor called....");
    }


    public AbstractRelic makeCopy() {
        return new ArchiveWideEyedDoll();
    }

    public static ArrayList<AbstractCard> cardsToRemainForgotten;

//    public boolean canSpawn() {
//        return AbstractDungeon.player.hasRelic("TeddyBear");
//    }

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
            if (!(FieldPatchAbstractCardBackForgottenCard.backForgottenCard.get(c) == null)) {
                cardsToRemainForgotten.add(c);
            }
        }
    }
}