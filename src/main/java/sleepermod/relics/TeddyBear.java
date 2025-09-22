package sleepermod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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

    public TeddyBear() {
        super(ID, RARITY,SOUND);
        System.out.println("TeddyBear constructor called....");
    }


    public AbstractRelic makeCopy() {
        return new TeddyBear();
    }

    public static ArrayList<UUID> uuidsToRemainForgotten = new ArrayList<UUID>();

//    public static CardGroup forgottenPileAtEndOfCombat = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);


    public static int numberOfCardsToRemainForgotten = 3;
    public static int countNumberOfCardsForgotten = 0;

//    @Override
//    public void atBattleStartPreDraw () {
//        System.out.println("cardsToRemainForgotten: " + cardsToRemainForgotten.toString());
//        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
//            if (cardsToRemainForgotten.contains(c)) {
//                Move.toForgottenPile(AbstractDungeon.player.drawPile,c,false);
//            }
//        }
//    }

    @Override
    public void onVictory () {
        System.out.println("onVictory for teddybear entered");
        flash();
        uuidsToRemainForgotten.clear();
        countNumberOfCardsForgotten = 0;

//        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
//            if (c instanceof ForgottenCard) {
//                forgottenCardsAtEndOfCombat.addToRandomSpot(c);
//            }
//        }
//        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
//            if (c instanceof ForgottenCard) {
//                forgottenCardsAtEndOfCombat.addToRandomSpot(c);
//            }
//        }
//        for (AbstractCard c : AbstractDungeon.player.hand.group) {
//            if (c instanceof ForgottenCard) {
//                forgottenCardsAtEndOfCombat.addToRandomSpot(c);
//            }
//        }

        CardGroup forgottenPileAtEndOfCombat = new CardGroup(((MySleeperPlayer) AbstractDungeon.player).forgottenPile, CardGroup.CardGroupType.UNSPECIFIED);
        forgottenPileAtEndOfCombat.shuffle(AbstractDungeon.shuffleRng);

          for (AbstractCard forgottenPileCard : forgottenPileAtEndOfCombat.group) {
              for (AbstractCard masterDeckCard : AbstractDungeon.player.masterDeck.group) {
                  if ( masterDeckCard.uuid == forgottenPileCard.uuid) {
                    System.out.println("Card added");
                    uuidsToRemainForgotten.add(masterDeckCard.uuid);
                    countNumberOfCardsForgotten++;
                    if (countNumberOfCardsForgotten == numberOfCardsToRemainForgotten) {
                        break;
                    }
                }
            }
        }
        
//        for (AbstractCard c : forgottenCardsAtEndOfCombat.group) {
//            System.out.println("FieldPatchAbstractCardBackForgottenCard.backForgottenCard.get(c): " +FieldPatchAbstractCardBackForgottenCard.backForgottenCard.get(c));
//            if (((ForgottenCard) c).frontForgottenCard != null) {
//                System.out.println("Card added");
//                cardsToRemainForgotten.add(((ForgottenCard) c).frontForgottenCard);
//                countNumberOfCardsForgotten++;
//                if (countNumberOfCardsForgotten == numberOfCardsToRemainForgotten) {
//                    break;
//                }
//            }
//        }
        System.out.println("end of TeddyBear" + uuidsToRemainForgotten.toString());
    }
}