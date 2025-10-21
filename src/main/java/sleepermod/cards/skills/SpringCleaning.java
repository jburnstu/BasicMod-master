package sleepermod.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import sleepermod.actions.AwakenAction;
import sleepermod.actions.SpringCleaningAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Recollect 1 card and exhaust it. exhaust
public class SpringCleaning extends AbstractSleeperCard {
    public static final String ID = makeID(SpringCleaning.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            -1 
    );

    private static final int baseMagicNumber = 0;
    private static final int magicUpgrade = 1;

    public SpringCleaning() {
        super(ID, info); 
        this.exhaust = true;
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.freeToPlayOnce)
            AbstractDungeon.player.energy.use(EnergyPanel.totalCount);
        int chemicalXBonus = (p.hasRelic("Chemical X"))? 2:0;
        addToBot(new AwakenAction(energyOnUse + magicNumber + chemicalXBonus, new SpringCleaningAction()));
        }
}