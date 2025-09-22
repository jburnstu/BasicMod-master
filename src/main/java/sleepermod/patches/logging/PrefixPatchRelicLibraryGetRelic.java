package sleepermod.patches.logging;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.HashMap;

@SpirePatch(clz = RelicLibrary.class, method = "getRelic")
public class PrefixPatchRelicLibraryGetRelic {
    public static void Prefix(String key, HashMap<String, AbstractRelic> ___sharedRelics) {
    if (___sharedRelics.containsKey(key))
        System.out.println(key+": Shared");
    else
        System.out.println(key+": Circlet");
        }
    }
