package sleepermod.cards.attacks;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.ForgetTopCardOfDiscardAction;
import sleepermod.actions.MakeTempForgottenCardInDiscardAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.cards.statuses.Dizzy;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// Deal X damage. add a dizzy into your deck
public class SpinningStrike extends AbstractSleeperCard {
    public static final String ID = makeID(SpinningStrike.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.COMMON, 
            CardTarget.ENEMY, 
            0 
    );

    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;
    public static final int magicNumber = 1;


    public SpinningStrike() {
        super(ID, info); 
        setDamage(DAMAGE, UPG_DAMAGE); 
        this.cardsToPreview = new Dizzy();
        setMagic(magicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new MakeTempCardInDiscardAction(new Dizzy(),1));
        addToBot(new ForgetTopCardOfDiscardAction(1));
    }



}