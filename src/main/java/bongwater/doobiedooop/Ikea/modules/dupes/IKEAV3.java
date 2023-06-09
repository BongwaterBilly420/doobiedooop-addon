package bongwater.doobiedooop.Ikea.modules.dupes;

import bongwater.doobiedooop.doobiedooopAddon;
import bongwater.doobiedooop.Ikea.utils.TimerUtils;
import meteordevelopment.meteorclient.events.game.GameLeftEvent;
import meteordevelopment.meteorclient.events.game.OpenScreenEvent;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Modules;
import meteordevelopment.meteorclient.utils.player.InvUtils;
import meteordevelopment.meteorclient.utils.player.PlayerUtils;
import meteordevelopment.orbit.EventHandler;
import meteordevelopment.orbit.EventPriority;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.Hand;

public class IKEAV3  extends Module {


    public IKEAV3(){
        super(doobiedooopAddon.doobiedooopAddon,"IKEAV3","EZPZ");
    }




    private final TimerUtils throwTimer = new TimerUtils();

    @Override
    public void onDeactivate() {
        throwTimer.reset();
        Modules.get().get("Sneak").toggle();
    }

    public void onActivate(){
        Modules.get().get("Sneak").toggle();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onTickThrow(TickEvent.Post event){

        for(Entity entity : mc.world.getEntities()){
            if(!(entity instanceof ChestBoatEntity boat)) continue;

            if(PlayerUtils.distanceTo(boat.getPos()) >5) continue;
            if(!boat.hasPassengers() && !(mc.currentScreen instanceof GenericContainerScreen)){
                interact(boat);
            }
            if(!boat.hasPassengers()) return;

            if (!(mc.currentScreen instanceof HandledScreen screen)) return;
            if (!(screen instanceof GenericContainerScreen container)) return;
            if(throwTimer.hasReached(100)){
                Inventory inv = container.getScreenHandler().getInventory();

                for (int i = 0; i < inv.size(); i++) {
                    InvUtils.drop().slotId(i);
                }


            }  throwTimer.reset();
        }

    }
    @EventHandler
    private void onGameLeft(GameLeftEvent event) { toggle(); }

    @EventHandler
    private void onScreenOpen(OpenScreenEvent event) {
        if (event.screen instanceof DisconnectedScreen) {
            toggle();
        }
    }

    private void interact(Entity entity) {
            mc.interactionManager.interactEntity(mc.player, entity, Hand.MAIN_HAND);
    }



}
