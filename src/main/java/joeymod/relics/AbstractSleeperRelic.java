package joeymod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class AbstractSleeperRelic extends BaseRelic {


    public AbstractSleeperRelic(String id, String imageName, RelicTier tier, LandingSound sfx) {
        super(id, imageName, tier, sfx);
    }

    public AbstractSleeperRelic(String id, RelicTier tier, LandingSound sfx) {
        super(id, tier, sfx);
    }

    public AbstractSleeperRelic(String id, String imageName, AbstractCard.CardColor pool, RelicTier tier, LandingSound sfx) {
        super(id, imageName, pool, tier, sfx);
    }

    public void onForget (AbstractCard card) {}
}