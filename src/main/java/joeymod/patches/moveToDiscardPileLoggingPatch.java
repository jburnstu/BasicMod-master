package joeymod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import joeymod.cards.ForgottenCard;

@SpirePatch(clz = CardGroup.class, method = "moveToDiscardPile")
public class moveToDiscardPileLoggingPatch {
    public static SpireReturn<Void> Prefix(Object _self, AbstractCard _c) {
        if (!((CardGroup) _self).group.contains(_c)){
            return SpireReturn.Return();
        }
        if (!(_c instanceof ForgottenCard)) {

            Thread.dumpStack();
            System.out.println("Cards in Hand:" + AbstractDungeon.player.hand.group);
            System.out.println("moveToDiscardPile called on card: " + _c);
        }
        return SpireReturn.Continue();
    }
}