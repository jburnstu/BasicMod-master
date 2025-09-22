package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GainEnergyIfForgetAction extends AbstractGameAction {
    private int energyGain;
    private boolean didForget;

    public GainEnergyIfForgetAction(int amount, boolean didForget) {
        setValues(AbstractDungeon.player, AbstractDungeon.player, 0);
        this.duration = Settings.ACTION_DUR_FAST;
        this.energyGain = amount;
        this.didForget = didForget;
    }

    public void update() {
        if (this.didForget) {
            AbstractDungeon.player.gainEnergy(this.energyGain);
            AbstractDungeon.actionManager.updateEnergyGain(this.energyGain);
            for (AbstractCard c : AbstractDungeon.player.hand.group)
                c.triggerOnGainEnergy(this.energyGain, true);
        }
        this.isDone = true;
    }
}
