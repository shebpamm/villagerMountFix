package com.shebpamm.villagerMountFix;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTransformEvent;

public class EventListener implements Listener{
	

	
	 @EventHandler
     public void onEntityTransform(EntityTransformEvent event)
     {
		 
		 
		 if(event.getTransformReason() == EntityTransformEvent.TransformReason.INFECTION || event.getTransformReason() == EntityTransformEvent.TransformReason.CURED) {
			 Entity villager = event.getTransformedEntity();
			
			 Optional<Entity> nearbyMinecart = villager.getNearbyEntities(1, 1, 1)
			 .stream()
			 .filter(e -> e.getType() == EntityType.MINECART)
			 .min(Comparator.comparing(p -> p.getLocation().distanceSquared(villager.getLocation())));
			 
			 if(nearbyMinecart.isPresent()) {
				 
				 if( !villager.isInsideVehicle() ) {
					 nearbyMinecart.get().addPassenger(villager);
				 }
			 }
		 }
		
     }
}
