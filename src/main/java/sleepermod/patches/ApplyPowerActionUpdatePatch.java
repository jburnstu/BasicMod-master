package sleepermod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;


@SpirePatch(clz = ApplyPowerAction.class, method = "<ctor>",
                        paramtypez={AbstractCreature.class,
                            AbstractCreature.class,
                            AbstractPower.class,
                            int.class,
                            boolean.class,
                            AbstractGameAction.AttackEffect.class}
)
public class ApplyPowerActionUpdatePatch {
    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(ApplyPowerAction _self, AbstractCreature target,  AbstractCreature source,AbstractPower powerToApply) {
        if (AbstractDungeon.player.hasRelic("LavaLamp") && source != null && source.isPlayer && target != source && powerToApply.ID
                .equals("Woozy")) {
            AbstractDungeon.player.getRelic("LavaLamp").flash();
            powerToApply.amount++;
            _self.amount++;
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(ApplyPowerAction.class, "powerToApply");
            int[] lines = LineFinder.findInOrder(ctMethodToPatch, fieldAccessMatcher);
//            for (int i = 0; i < lines.length; i++) {
//                lines[i] += 1;
//            }
//            System.out.println(lines);
            return lines;
        }
    }
}