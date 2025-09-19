package sleepermod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.OverlayMenu;

@SpirePatch(clz = OverlayMenu.class,
        method = "update")
public class OverlayMenuUpdatePatch {
    public static void Prefix(Object _self) {
        OverlayMenuForgottenPanelFieldPatch.forgottenPanel.get(_self).updatePositions();
    }
}
