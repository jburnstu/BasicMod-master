package sleepermod.patches.coremechanics;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import sleepermod.cards.ForgottenCard;

@SpirePatch(clz = AbstractCard.class,
            method = SpirePatch.CLASS)
public class FieldPatchAbstractCardBackForgottenCard {
    public static SpireField<ForgottenCard> backForgottenCard = new SpireField<>(()->null);

    public static SpireField<Boolean> forgetOnUseOnce = new SpireField<>(()->false);
}
