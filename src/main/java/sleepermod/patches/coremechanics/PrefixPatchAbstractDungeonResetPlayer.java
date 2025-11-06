package sleepermod.patches.coremechanics;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sleepermod.character.MySleeperPlayer;


@SpirePatch(clz = AbstractDungeon.class, method = "resetPlayer")
public class PrefixPatchAbstractDungeonResetPlayer {

    public static void Prefix(AbstractPlayer ___player) {
        if (___player instanceof MySleeperPlayer) {
            ((MySleeperPlayer) ___player).forgottenPile.clear();
            ((MySleeperPlayer) ___player).cardsRememberedThisCombat.clear();
        }
    }
}