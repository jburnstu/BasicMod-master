package sleepermod.cardsDeprecated.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.ForgetTopCardAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;


public class BrainZap extends AbstractSleeperCard {
    public static final String ID = makeID(BrainZap.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.COMMON, 
            CardTarget.ENEMY, 
            1 
    );

    private static final int DMG = 10;
    private static final int UPG_DMG = 4;
    private static final int baseMagicNumber = 1;

    public BrainZap() {
        super(ID, info); 

        setDamage(DMG, UPG_DMG); 
        setMagic(baseMagicNumber);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ForgetTopCardAction(null,true));
        };
    }
