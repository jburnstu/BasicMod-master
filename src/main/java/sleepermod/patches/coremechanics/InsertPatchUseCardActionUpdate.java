package sleepermod.patches.coremechanics;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import sleepermod.actions.Move;
import sleepermod.cards.AbstractSleeperCard;


@SpirePatch(clz = UseCardAction.class, method = "update")
public class InsertPatchUseCardActionUpdate {

//    static Logger log = Logger.getLogger("MyLogger");
    @SpireInsertPatch(locator = Locator.class,localvars = {"targetCard"})
    public static void Insert(UseCardAction _self, AbstractCard targetCard) {
        AbstractCard newForgottenCard;
        boolean forgetCard = (FieldPatchAbstractCardBackForgottenCard.forgetOnUseOnce.get(targetCard)
                ||(targetCard instanceof AbstractSleeperCard && ((AbstractSleeperCard) targetCard).forget));
        if (forgetCard) {
            FieldPatchAbstractCardBackForgottenCard.forgetOnUseOnce.set(targetCard,false);
//            System.out.println("forgetCard activated -- targetCard:" + targetCard.getClass());
//            System.out.println(targetCard.dontTriggerOnUseCard);
            newForgottenCard = Move.toForgottenPile(AbstractDungeon.player.hand, targetCard,false);
            AbstractDungeon.actionManager.addToTop(new ShowCardAndPoofAction(targetCard));
            AbstractDungeon.player.cardInUse = null;
            if (_self.reboundCard) { //Will never happen for now
                AbstractDungeon.player.hand.moveToDeck(newForgottenCard, false);
            } else if (newForgottenCard.shuffleBackIntoDrawPile) {
                AbstractDungeon.player.hand.moveToDeck(newForgottenCard, true);
            } else if (newForgottenCard.returnToHand) {
                AbstractDungeon.player.hand.moveToHand(newForgottenCard);
                AbstractDungeon.player.onCardDrawOrDiscard();
            } else {
//                System.out.println("Reached part where forgottenCard is discarded");
                AbstractDungeon.player.hand.moveToDiscardPile(newForgottenCard);
//                System.out.println("Discard Pile Size:" + AbstractDungeon.player.discardPile.size());
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "moveToDeck");
            int[] lines = LineFinder.findInOrder(ctMethodToPatch, (Matcher)methodCallMatcher);
            for (int i = 0; i < lines.length; i++) {
                lines[i] += 1;
            }
//            System.out.println(lines);
            return lines;
        }
    }
}