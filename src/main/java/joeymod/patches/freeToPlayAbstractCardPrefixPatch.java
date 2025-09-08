package joeymod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;
import joeymod.powers.VisionPower;

@SpirePatch(clz = CardGroup.class, method = "moveToDiscardPile")
public class freeToPlayAbstractCardPrefixPatch {
    public static SpireReturn<Boolean> Prefix(Object _self) {
        if (AbstractDungeon.player != null && AbstractDungeon.currMapNode != null &&
                (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                AbstractDungeon.player.hasPower(VisionPower.POWER_ID) &&
                _self instanceof ForgottenCard) {
            return SpireReturn.Return(true);
        }
        return SpireReturn.Continue();
    }
}