package joeymod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import joeymod.cards.ForgottenCard;

@SpirePatch(clz = CardGroup.class, method = "moveToDiscardPile")
public class moveToDiscardPileLoggingPatch {
    public static void Prefix(Object _self, AbstractCard _c) {
        if (!(_c instanceof ForgottenCard)) {
            Thread.dumpStack();
            System.out.println("moveToDiscardPile called on card: " + _c);
        }
    }
}