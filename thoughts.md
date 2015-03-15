#Better Brewing and Potions mod *(and possibly alchemy!)* mate

New Object: `Potion`/`Brew`/`Concoction`/`Something`
-
- method called to do the magic -> entity, level, and others as needed
- method called when the effect starts
- method called when the effect stops
- field with the maximum level of the potion

The EffectHandler/SomeOtherName
-
- class implementing IExtendedProperties
- saves a **list**/map of active effects, very much like MC does it --> using IExtProps
- has a method with @SubscribeEvent, with the TickEvent to call the effect
- method to add the effect to an entity with a level and a duration (which is a fancy way to say that we're adding something to the entity's NBT and that the TickHandler is reacting accordingly

The Actual Brewing of Potions:
-
- A map with items/blocks and their properties
- Mixing these different items/blocks gives a potion due to their properties
- Cauldrons can and will blow up
- Random discoveries are important, and some brews can have less potency than others due to the ingredients used
- Potions journal using Guide-API, recording the discoveries and the item/block effects

Alchemy
-
We'll see later!