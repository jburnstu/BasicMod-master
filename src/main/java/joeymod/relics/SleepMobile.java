package joeymod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;

import static joeymod.JoeyBasicMod.makeID;

// Whenever you remember a power card, add a copy of that power to your draw pile.
public abstract class SleepMobile extends BaseRelic {
    private static final String NAME = "SleepMobile"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.


    public SleepMobile() {
        super(ID, NAME, MySleeperPlayer.Meta.CARD_COLOR, RARITY, SOUND);
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