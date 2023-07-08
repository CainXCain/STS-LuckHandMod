package luckyhandmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import luckyhandmod.relics.TungstenBranchCandle;

public class canUsePatch {
    @SpirePatch(clz = AbstractCard.class, method = "canUse")
    public static class dailyBlessing {
        @SpirePostfixPatch
        public static boolean Postfix(boolean __result, AbstractCard __instance, AbstractPlayer p, AbstractMonster m) {
            if (__instance.type == AbstractCard.CardType.CURSE) {
                if (__result)
                    return true;
                else if (__instance.costForTurn < -1 && !AbstractDungeon.player.hasRelic(TungstenBranchCandle.ID))
                    return false;
                else return __instance.hasEnoughEnergy();
            }
            return __result;
        }
    }
}
