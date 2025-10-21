package sleepermod.relics;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import sleepermod.actions.AwakenAction;
import sleepermod.cards.AbstractSleeperCard;

import static sleepermod.SleeperMod.makeID;

// Choose one card in hand at end of combat, starts next round in hand.
public class StickerNote extends AbstractSleeperRelic {
    public static final String ID = makeID(StickerNote.class.getSimpleName());
    private AbstractPlayer p = AbstractDungeon.player;

    public StickerNote() {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL);
    }


    public AbstractRelic makeCopy() {
        return new StickerNote();
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c != targetCard && c instanceof AbstractSleeperCard && ((AbstractSleeperCard) c).urgent) {
                addToTop(new LoseHPAction(AbstractDungeon.player,AbstractDungeon.player,1));
            }
        }
    }
}