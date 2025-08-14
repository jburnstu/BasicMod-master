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
        if (AbstractDungeon.player instanceof MySleeperPlayer && ((MySleeperPlayer) AbstractDungeon.player).forgottenPile.group.contains(_c)){
            System.out.println("Discard Avoided via patch...");
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }
}