package joeymod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;
import joeymod.actions.Move;
import joeymod.cards.AbstractSleeperCard;



@SpirePatch(clz = UseCardAction.class, method = "update")
public class UseCardActionUpdatePatch {

//    static Logger log = Logger.getLogger("MyLogger");
    @SpireInsertPatch(locator = Locator.class,localvars = {"targetCard"})
    public static SpireReturn<Void> Insert(UseCardAction _self, AbstractCard targetCard) {
        AbstractCard newForgottenCard;
        boolean forgetCard = targetCard instanceof AbstractSleeperCard && ((AbstractSleeperCard) targetCard).forget;
        if (forgetCard) {
            System.out.println("targetCard:" + targetCard.getClass());
            newForgottenCard = Move.toForgottenPile(AbstractDungeon.player.hand, targetCard);
            if (_self.reboundCard) {
                AbstractDungeon.player.hand.moveToDeck(newForgottenCard, false);
            } else if (newForgottenCard.shuffleBackIntoDrawPile) {
                AbstractDungeon.player.hand.moveToDeck(newForgottenCard, true);
            } else if (newForgottenCard.returnToHand) {
                AbstractDungeon.player.hand.moveToHand(newForgottenCard);
                AbstractDungeon.player.onCardDrawOrDiscard();
            } else {
                System.out.println("Reached part where forgottenCard is discarded");
                AbstractDungeon.player.hand.moveToDiscardPile(newForgottenCard);
            }
            AbstractDungeon.actionManager.addToBottom(new HandCheckAction());
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(AbstractRelic.class, "flash");
            int[] lines = LineFinder.findAllInOrder(ctMethodToPatch, (Matcher)methodCallMatcher);
            for (int i = 0; i < lines.length; i++) {
                lines[i] += 1;
            }
            return lines;
        }
    }
}


//import java.lang.reflect.Field;
//
//Field field = UseCardAction.class.getDeclaredField("targetCard");
//field.setAccessible(true);
//AbstractCard card = (AbstractCard) field.get(_self);
//field.set(_self, newCard);