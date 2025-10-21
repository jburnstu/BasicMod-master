package sleepermod.cardsDeprecated.attacks;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.cards.ForgottenCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Costs one less for each forgotten card in your hand. X block
public class Transparent extends AbstractSleeperCard {
    public static final String ID = makeID(Transparent.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.ENEMY, 
            4 
    );

    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 5;

    public Transparent() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }

    public void didDiscard() {
        setCostForTurn(this.costForTurn - 1);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c instanceof ForgottenCard) {
            setCostForTurn(this.cost - 1);
        }
    }

    @Override
    public void triggerOnOtherCardDrawn(AbstractCard c) {
        if (c instanceof ForgottenCard) {
            setCostForTurn(this.cost -1);
        }
    }


    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        int forgottenCardsInHand = 0;
        for (AbstractCard c :AbstractDungeon.player.hand.group) {
            if (c instanceof ForgottenCard) {
                forgottenCardsInHand += 1;
            }
        }
        setCostForTurn(this.cost - forgottenCardsInHand);
    }

    public void atTurnStart() {
        resetAttributes();
        applyPowers();
    }



}