package sleepermod.patches.coremechanics;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;


@SpirePatch(clz = AbstractCard.class, method = "makeStatEquivalentCopy")
public class PostfixPatchAbstractCardMakeStatEquivalentCopy {
    public static AbstractCard Postfix(AbstractCard _result, Object _self) {
        boolean forgottenInMaster = FieldPatchAbstractCardBackForgottenCard.forgottenInMasterDeck.get(_self);
        FieldPatchAbstractCardBackForgottenCard.forgottenInMasterDeck.set(_result,forgottenInMaster);
        return _result;
    }
}