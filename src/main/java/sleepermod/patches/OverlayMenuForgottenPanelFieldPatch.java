package sleepermod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.OverlayMenu;
import sleepermod.ui.panels.ForgottenPanel;

@SpirePatch(clz = OverlayMenu.class,
            method = SpirePatch.CLASS)
public class OverlayMenuForgottenPanelFieldPatch {
    public static SpireField<ForgottenPanel> forgottenPanel = new SpireField<>(()->new ForgottenPanel());

}
