package sleepermod.cardsDeprecated.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RepairPower;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Heal 20HP. At the end of combat, lose 15HP.
public class AllADream extends AbstractSleeperCard {
    public static final String ID = makeID(AllADream.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            1 
    );


    int baseMagicNumber = 20;
    int magicUpgrade = 5;

    public AllADream() {
        super(ID, info); 
        setMagic(baseMagicNumber,magicUpgrade);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p, p, magicNumber));
        addToBot(new ApplyPowerAction(p,p,new RepairPower(p,-15)));
    }


    public AbstractCard makeCopy() {
        return new AllADream();
    }
}