package sleepermod.patches.specificuse;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import sleepermod.actions.Move;
import sleepermod.relics.TeddyBear;
import sleepermod.relics.WideEyedDoll;

import java.util.ArrayList;


@SpirePatch(clz = CardGroup.class, method = "initializeDeck")
public class InsertPatchCardGroupInitializeDeck {

//    static Logger log = Logger.getLogger("MyLogger");
    @SpireInsertPatch(locator = Locator.class, localvars = {"copy"})
    public static void Insert(CardGroup _self, CardGroup masterDeck, CardGroup copy) {

        ArrayList<AbstractCard> cardsToStartForgotten = new ArrayList<AbstractCard>();

        System.out.println("InitializeDeck patch entered");
        if (AbstractDungeon.player.hasRelic(WideEyedDoll.ID)) {
            for (AbstractCard c : copy.group) {
                if (WideEyedDoll.cardsToRemainForgotten.contains(c)) {
                    Move.toForgottenPile(copy, c, false);
                }
            }
        } else if (AbstractDungeon.player.hasRelic(TeddyBear.ID)) {
            System.out.println("Teddybear IF entered");
            System.out.println(TeddyBear.uuidsToRemainForgotten.toString());
            for (AbstractCard c : copy.group) {
                if (TeddyBear.uuidsToRemainForgotten.contains(c.uuid)) {
                    System.out.println("uuidsToRemainForgotten IF entered");
                    cardsToStartForgotten.add(c);
                }
            }
        }
        for (AbstractCard c: cardsToStartForgotten) {
            Move.toForgottenPile(copy,c,false);
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher.NewExprMatcher newExprMatcher = new Matcher.NewExprMatcher(CardGroup.class);
            int[] lines = LineFinder.findInOrder(ctMethodToPatch, newExprMatcher);
            for (int i = 0; i < lines.length; i++) {
                lines[i] += 1;
            }
//            System.out.println(lines);
            return lines;
        }
    }
}