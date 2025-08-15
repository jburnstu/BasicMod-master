package joeymod.archive;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

@SpirePatch(clz = UseCardAction.class, method = "<ctor>", paramtypez = {AbstractCard.class, AbstractCreature.class})
public class UseCardActionConstructorLoggingPatch {
    public static void Prefix(Object _self) {
        System.out.println("UseCardAction constructor called");

    }
}