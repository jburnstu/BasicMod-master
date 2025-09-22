package sleepermod.patches.forgottenpanel;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.OverlayMenu;

@SpirePatch(clz = OverlayMenu.class,
        method = "update")
public class PrefixPatchOverlayMenuUpdate {
    public static void Prefix(Object _self) {
        FieldPatchOverlayMenuForgottenPanel.forgottenPanel.get(_self).updatePositions();
    }
}
