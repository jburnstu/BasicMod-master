package sleepermod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import sleepermod.character.MySleeperPlayer;

import java.util.ArrayList;
import java.util.UUID;

import static sleepermod.SleeperMod.makeID;

// When you recollect and no cards in forgottenPile,: add a new card to your hand (cost 1 less?)
public class TeddyBear extends AbstractSleeperRelic {
    public static final String ID = makeID(TeddyBear.class.getSimpleName());
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    private static boolean usedThisCombat = false;

    public TeddyBear() {
        super(ID, RARITY,SOUND);
        System.out.println("TeddyBear constructor called....");
    }


    public AbstractRelic makeCopy() {
        return new TeddyBear();
    }

    public static ArrayList<UUID> uuidsToRemainForgotten = new ArrayList<UUID>();

    public static final int numberOfCardsToStayForgotten = 3;

//    @Override
//    public void atBattleStart() {
//        usedThisCombat = false;
//    }
//
//    @Override
//    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
//        if (!(usedThisCombat) &&!(targetCard.freeToPlayOnce) && targetCard.costForTurn > 1) {
//            addToBot(new DrawCardAction(1));
//            addToBot(new GainEnergyAction(1));
//            usedThisCombat = true;
//        }
//    }


}