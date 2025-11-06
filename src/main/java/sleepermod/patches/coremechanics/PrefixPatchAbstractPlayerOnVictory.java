package sleepermod.patches.coremechanics;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import sleepermod.cards.ForgottenCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.FreeCardPower;
import sleepermod.powers.TrancePower;

@SpirePatch(clz = AbstractPlayer.class, method = "onVictory")
public class PrefixPatchAbstractPlayerOnVictory {
    public static void Prefix(Object _self) {
        if (_self instanceof MySleeperPlayer) {
            for (AbstractCard masterCard : ((MySleeperPlayer) _self).masterDeck.group) {
                System.out.println("In masterCard loop with " + masterCard + ", uuid" + masterCard.uuid);
                FieldPatchAbstractCardBackForgottenCard.forgottenInMasterDeck.set(masterCard, false);
                for (AbstractCard c : ((MySleeperPlayer) _self).forgottenPile.group) {
                    System.out.println("In forgottenpile loop with " + c + ", uuid" + c.uuid);
                    if (masterCard.uuid == c.uuid) {
                        System.out.println("Success on " + masterCard);
                        FieldPatchAbstractCardBackForgottenCard.forgottenInMasterDeck.set(masterCard, true);
                        break;
                    }
                }
            }
        }
    }
}