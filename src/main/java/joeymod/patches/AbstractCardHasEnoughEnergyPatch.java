package joeymod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import joeymod.actions.Move;
import joeymod.cards.AbstractSleeperCard;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;


@SpirePatch(clz = AbstractCard.class, method = "hasEnoughEnergy")
public class AbstractCardHasEnoughEnergyPatch {

    @SpirePrefixPatch()
    public static SpireReturn<Boolean> Prefix(Object _self) {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (((AbstractSleeperCard) c).urgent && c!=_self){
                return SpireReturn.Return(false);
            }
        return SpireReturn.Continue();
    }
}


//import java.lang.reflect.Field;
//
//Field field = UseCardAction.class.getDeclaredField("targetCard");
//field.setAccessible(true);
//AbstractCard card = (AbstractCard) field.get(_self);
//field.set(_self, newCard);