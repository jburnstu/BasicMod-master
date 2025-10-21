package sleepermod.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Urgent. remove all debuffs
public class Hypochondria extends AbstractSleeperCard {
    public static final String ID = makeID(Hypochondria.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            1 
    );

    public boolean baseExhaust = true;
    public boolean upgradeExhaust = false;

    public Hypochondria() {
        super(ID, info); 
        this.urgent = true;
        setExhaust(baseExhaust,upgradeExhaust);
//        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RemoveDebuffsAction((AbstractDungeon.player)));
    }
}