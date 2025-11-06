package sleepermod.cards.skills;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.IncreaseCostAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// Deal X damage. Increase the cost of this card by 1 this combat.
public class Mnemonic extends AbstractSleeperCard {
    public static final String ID = makeID(Mnemonic.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            4 
    );

    private static final int BLOCK = 12;
    private static final int UPG_BLOCK = 4;
    public static final int magicNumber = 1;


    public Mnemonic() {
        super(ID, info); 
        setBlock(BLOCK, UPG_BLOCK); 
        setMagic(magicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
    }

    @Override
    public void triggerOnShuffle() {
        addToBot(new ReduceCostAction(this.uuid, this.magicNumber));
    }
}