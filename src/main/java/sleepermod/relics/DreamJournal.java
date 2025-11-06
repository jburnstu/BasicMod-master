package sleepermod.relics;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import sleepermod.actions.AwakenAction;

import static sleepermod.SleeperMod.makeID;

// Choose one card in hand at end of combat, starts next round in hand.
public class DreamJournal extends AbstractSleeperRelic {
    public static final String ID = makeID(DreamJournal.class.getSimpleName());
    private AbstractPlayer p = AbstractDungeon.player;

    public DreamJournal() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }


    public AbstractRelic makeCopy() {
        return new DreamJournal();
    }


    @Override
    public void atBattleStart() {
        addToBot(new AwakenAction(1,false));
    }

  }