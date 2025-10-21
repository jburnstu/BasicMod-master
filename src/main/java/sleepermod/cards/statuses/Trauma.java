package sleepermod.cards.statuses;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.Move;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Lose 2HP. forget.
public class Trauma extends AbstractSleeperCard {
    public static final String ID = makeID(Trauma.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS, 
            CardRarity.CURSE, 
            CardTarget.SELF, 
            0 
    );

    public static final int baseMagicNumber = 2;


    public Trauma() {
        super(ID, info);
        this.exhaust = true;
        this.urgent = true;
        setMagic(baseMagicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, magicNumber, DamageInfo.DamageType.THORNS)));
    }

//    @Override
//    public void triggerOnPlayedFromForgotten(AbstractPlayer p, AbstractMonster m, boolean randomTarget) {
//      }
}