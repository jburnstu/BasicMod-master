package sleepermod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import sleepermod.actions.Move;
import sleepermod.patches.coremechanics.FieldPatchAbstractCardBackForgottenCard;

import java.util.ArrayList;

import static sleepermod.SleeperMod.makeID;


// Forgotten cards remain forgotten in between combats.
public class WideEyedDoll extends AbstractSleeperRelic {
    public static final String ID = makeID(WideEyedDoll.class.getSimpleName());
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    private boolean expensiveCardPlayedLastTurn = false;

    public WideEyedDoll() {
        super(ID, RARITY,SOUND);
        System.out.println("TeddyBear constructor called....");
    }


    public AbstractRelic makeCopy() {
        return new WideEyedDoll();
    }

//    @Override
//    public void atTurnStart() {
//        if (expensiveCardPlayedLastTurn) {
//            addToBot(new DrawCardAction(1));
//            addToBot(new GainEnergyAction(1));
//            expensiveCardPlayedLastTurn = false;
//        }
//    }
//
//    @Override
//    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
//        if (!(targetCard.freeToPlayOnce) && targetCard.costForTurn > 1) {
//            expensiveCardPlayedLastTurn = true;
//        }
//    }


}