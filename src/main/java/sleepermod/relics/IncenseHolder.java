package sleepermod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.ForgottenCard;

import static sleepermod.SleeperMod.makeID;

// When you play an unplayable card from forgotten, exhaust it and gain one energy.
public class IncenseHolder extends AbstractSleeperRelic {
    public static final String ID = makeID(IncenseHolder.class.getSimpleName()); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.


    public IncenseHolder() {
        super(ID, RARITY, SOUND);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c instanceof ForgottenCard && (((ForgottenCard) c).frontForgottenCard.type == AbstractCard.CardType.STATUS || ((ForgottenCard) c).frontForgottenCard.type == AbstractCard.CardType.CURSE)) {
            ((ForgottenCard) c).frontForgottenCard.exhaustOnUseOnce = true;
            addToTop(new GainEnergyAction(1));
        }
    }
}