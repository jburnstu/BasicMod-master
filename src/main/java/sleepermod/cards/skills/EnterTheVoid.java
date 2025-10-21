package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.EnterTheVoidPower;
import sleepermod.util.CardStats;


public class EnterTheVoid extends AbstractSleeperCard {
    public static final String ID = makeID(EnterTheVoid.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            1 
    );

    private int baseMagicNumber = 1;
    private int magicUpgrade = 1;

    public EnterTheVoid() {
        super(ID, info);
        this.urgent = true;
        setMagic(baseMagicNumber,magicUpgrade);
//        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,magicNumber)));
        addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,magicNumber)));
        addToBot(new ApplyPowerAction(p,p,new EnterTheVoidPower(p,1)));
    }
}