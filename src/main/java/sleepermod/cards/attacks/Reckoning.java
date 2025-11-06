package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.ReckoningAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.cards.ForgottenCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//X damage for each forgotten card in your hand
public class Reckoning extends AbstractSleeperCard {
    public static final String ID = makeID(Reckoning.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.UNCOMMON, 
            CardTarget.ENEMY, 
            1 
    );

    private static final int DMG = 6;
    private static final int UPG_DMG = 2;
    private static int baseMagicNumber = 1;
    private static int magicUpgrade = 1;


    public Reckoning() {
        super(ID, info); 
        setDamage(DMG, UPG_DMG); 
        setMagic(baseMagicNumber, magicUpgrade);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ReckoningAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),magicNumber));
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ForgottenCard)
                count++;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }
}
