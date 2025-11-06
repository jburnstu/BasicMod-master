package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.patches.coremechanics.FieldPatchAbstractCardBackForgottenCard;
import sleepermod.util.CardStats;

//X block. draw 1 card and forget one card.
public class AbsentMinded extends AbstractSleeperCard {
    public static final String ID = makeID(AbsentMinded.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

//    private static final int BLOCK = 8;
//    private static final int UPG_BLOCK = 3;
    private int baseMagicNumber = 1;

    public AbsentMinded() {
        super(ID, info); 
//        this.forget = true;
//        setBlock(BLOCK,UPG_BLOCK);
        setMagic(baseMagicNumber);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        FieldPatchAbstractCardBackForgottenCard.forgetOnUseOnce.set(p.drawPile.getTopCard(),true);
        addToBot(new PlayTopCardAction( (AbstractDungeon.getCurrRoom()).monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false));        }
    }
