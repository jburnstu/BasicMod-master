package joeymod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;

import static joeymod.JoeyBasicMod.makeID;

// Whenever you remember a power card, add a copy of that power to your draw pile.
public class SleepMobile extends AbstractSleeperRelic {
       public static final String ID = makeID(SleepMobile.class.getSimpleName()); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.UNCOMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.


    public SleepMobile() {
        super(ID, RARITY, SOUND);
    }

    public boolean usedThisTurn = false;

    public void atTurnStart() {
        usedThisTurn = false;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c instanceof ForgottenCard && ((ForgottenCard) c).frontForgottenCard.type == AbstractCard.CardType.POWER) {
            AbstractCard cardCopy = ((ForgottenCard) c).frontForgottenCard.makeStatEquivalentCopy();
            AbstractDungeon.player.drawPile.addToRandomSpot(cardCopy);
        }
    }
}