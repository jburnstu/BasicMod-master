package joeymod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import joeymod.cards.ForgottenCard;

import static joeymod.JoeyBasicMod.makeID;

public class LavaLamp extends AbstractSleeperRelic {
    public static final String ID = makeID(LavaLamp.class.getSimpleName());

    public LavaLamp() {
        super(ID, AbstractRelic.RelicTier.UNCOMMON, AbstractRelic.LandingSound.MAGICAL);
    }

    public AbstractRelic makeCopy() {
        return new LavaLamp();
    }

}