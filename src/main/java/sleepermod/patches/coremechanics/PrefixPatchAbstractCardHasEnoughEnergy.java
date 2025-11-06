package sleepermod.patches.coremechanics;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.relics.StickerNote;


@SpirePatch(clz = AbstractCard.class, method = "hasEnoughEnergy")
public class PrefixPatchAbstractCardHasEnoughEnergy {

    @SpirePrefixPatch()
    public static SpireReturn<Boolean> Prefix(AbstractCard _self) {
        if (!(_self instanceof AbstractSleeperCard && (((AbstractSleeperCard) _self).urgent))&&!(_self.isInAutoplay)) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c instanceof AbstractSleeperCard && ((AbstractSleeperCard) c).urgent && !(AbstractDungeon.player.hasRelic(StickerNote.ID))) {
                    ((AbstractCard) _self).cantUseMessage = "I have an Urgent card I must play first.";
                    return SpireReturn.Return(false);
                }
            }
        }
        return SpireReturn.Continue();
    }
}