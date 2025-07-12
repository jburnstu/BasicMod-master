package joeymod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;

@SpirePatch(clz = CardGroup.class, method = "moveToDiscardPile")
public class moveToDiscardPilePrefixPatch {
    public static SpireReturn<Void> Prefix(Object _self, AbstractCard _c) {
//        System.out.println("Forgotten pile: "+((MySleeperPlayer) AbstractDungeon.player).forgottenPile.group);
//        System.out.println("Card to discard: "+_c);
        if (((MySleeperPlayer) AbstractDungeon.player).forgottenPile.group.contains(_c)){
            return SpireReturn.Return();
        }
        if (!(_c instanceof ForgottenCard)) {
//            Thread.dumpStack();
//            System.out.println("Cards in Hand:" + AbstractDungeon.player.hand.group);
//            System.out.println("moveToDiscardPile called on card: " + _c);
        }
        return SpireReturn.Continue();
    }
}