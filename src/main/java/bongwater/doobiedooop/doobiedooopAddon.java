package bongwater.doobiedooop;

import bongwater.doobiedooop.Ikea.modules.dupes.IKEADupe;
import bongwater.doobiedooop.Ikea.modules.dupes.IKEAV3;
import bongwater.doobiedooop.Ikea.modules.dupes.IKEAV5;
import bongwater.doobiedooop.Ikea.modules.utility.AutoItemMove;
import bongwater.doobiedooop.Ikea.modules.utility.DubCounter;
import bongwater.doobiedooop.Ikea.modules.utility.NoChestRender;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class doobiedooopAddon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category doobiedooopAddon = new Category("doobiedooopAddon");

    @Override
    public void onInitialize() {
        LOG.info("Initializing doobiedooop-Addon");

        initializeModules(Modules.get());

        initializeCommands();
    }

    private void initializeModules(Modules modules) {
        // Modules
        Modules.get().add(new IKEADupe());
        Modules.get().add(new AutoItemMove());
        Modules.get().add(new DubCounter());
        Modules.get().add(new NoChestRender());
        Modules.get().add(new IKEAV3());
        Modules.get().add(new IKEAV5());
    }

    private void initializeCommands() {
        // Commands
    }

    public void onRegisterCategories() {
        Modules.registerCategory(doobiedooopAddon);
    }

    public String getPackage() {
        return "bongwater.doobiedooop";
    }
}
