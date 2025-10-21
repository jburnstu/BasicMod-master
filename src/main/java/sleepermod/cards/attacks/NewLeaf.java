package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.NewLeafAttackAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//X damage. If fatal, permanently transform a card in your forgotten pile.
public class NewLeaf extends AbstractSleeperCard {
    public static final String ID = makeID(NewLeaf.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.RARE, 
            CardTarget.ENEMY, 
            1 
    );

    private static final int DMG = 10;
    private static final int UPG_DMG = 4;

    public NewLeaf() {
        super(ID, info); 
        this.exhaust = true;
        setDamage(DMG, UPG_DMG); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        addToBot(new AwakenAction(1,new NewLeafTransformationAction()));
        addToBot(new NewLeafAttackAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        };
    }
