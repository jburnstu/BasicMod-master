package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.MakeTempForgottenCardInDiscardAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Urgent. deal x damage and gain x block. add a copy of this card to your discard. Forget.
public class Compulsion extends AbstractSleeperCard {
    public static final String ID = makeID(Compulsion.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.UNCOMMON, 
            CardTarget.ENEMY, 
            0 
    );

    private static final int DMG = 3;
    private static final int UPG_DMG = 1;
    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 1;

    public Compulsion() {
        super(ID, info); 
//        this.forget = true;
        setDamage(DMG,UPG_DMG);
        setBlock(BLOCK,UPG_BLOCK); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new GainBlockAction(p,block));
        addToBot(new MakeTempForgottenCardInDiscardAction(makeStatEquivalentCopy(), 1));
        };
    }
