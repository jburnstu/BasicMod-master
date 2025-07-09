package joeymod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class AbstractSleeperRelic extends AbstractRelic {


    public AbstractSleeperRelic(String setId, String imgName, RelicTier tier, LandingSound sfx) {
        super(setId, imgName, tier, sfx);
    }

    public void onForget (AbstractCard card) {}
}