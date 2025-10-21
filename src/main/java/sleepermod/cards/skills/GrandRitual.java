package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.GrandRitualAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.GrandRitualPower;
import sleepermod.util.CardStats;

//X awaken X vision
public class GrandRitual extends AbstractSleeperCard {
    public static final String ID = makeID(GrandRitual.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.RARE, 
            CardTarget.SELF,
            -1
    );

//    private int baseMagicNumber = 0;
//    private int magicUpgrade = 1;
    public boolean baseExhaust = true;
    public boolean upgradeExhaust = true;


    public GrandRitual() {
        super(ID, info); 
        setExhaust(baseExhaust,upgradeExhaust);
//        setMagic(baseMagicNumber, magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        addToBot(new ApplyPowerAction(p,p, new GrandRitualPower(p,this.energyOnUse+magicNumber)));
        addToBot(new GrandRitualAction(p, this.freeToPlayOnce, this.upgraded,this.energyOnUse));
    }
}