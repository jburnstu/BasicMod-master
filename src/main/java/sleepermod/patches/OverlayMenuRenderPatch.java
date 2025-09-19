package sleepermod.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.ui.panels.ExhaustPanel;
import javassist.CtBehavior;

@SpirePatch(clz = OverlayMenu.class,
        method = "render")
public class OverlayMenuRenderPatch {
    @SpireInsertPatch(locator = OverlayMenuRenderPatch.Locator.class)

    public static void Insert(Object _self, SpriteBatch sb) {
        OverlayMenuForgottenPanelFieldPatch.forgottenPanel.get(_self).render(sb);
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(ExhaustPanel.class, "render");
            int[] lines = LineFinder.findInOrder(ctMethodToPatch, (Matcher)methodCallMatcher);
            for (int i = 0; i < lines.length; i++) {
                lines[i] += 1;
            }
            return lines;
        }
    }
}
