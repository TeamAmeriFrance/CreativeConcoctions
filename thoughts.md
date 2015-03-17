#Better Brewing and Potions mod *(and possibly alchemy!)* mate

##New Object: `Potion`/`Brew`/`Concoction`/`Something`:
- [ ] `method` called to do the magic -> entity, level, and others as needed
- [ ] `method` called when the effect starts
- [ ] `method` called when the effect stops
- [ ] `field` with the maximum level of the potion

##The EffectHandler/SomeOtherName:
- [ ] `class` implementing `IExtendedProperties`
- [ ] Saves a **list**/map of active effects, very much like MC does it --> using `IExtendedProperties`
- [ ] Has a method with `@SubscribeEvent`, with the `TickEvent` to call the effect
- [ ] `method` to add the effect to an entity with a level and a duration (which is a fancy way to say that we're adding something to the entity's NBT and that the `TickHandler` is reacting accordingly

##The Actual Brewing of Potions:
- [ ] A map with items/blocks and their properties
- [ ] Mixing these different items/blocks gives a potion due to their properties
- [ ] Cauldrons can and will blow up
- [ ] Random discoveries are important, and some brews can have less potency than others due to the ingredients used
- [ ] Potions journal using [Guide-API](https://github.com/TeamAmeriFrance/Guide-API), recording the discoveries and the item/block effects

##Other things to do:

- [ ] Custom NEI Handler

##Effects

Have an effect idea? Fill out [this](http://goo.gl/forms/FiPc7Kkyhs) form. Accepted responses will be added below.

**Concoction Name** | **Ability** | **Description**
------------ | ------------- | -------------
Fleet Feet | Speed | Increases user's movement speed.
Brawn | Strength | Increases user's attack damage.
Ghastly Draught | Fireballs | Allows the user to shoot fireballs. 
Luman Liquor | Human Torch | Creates light around the player. Higher potency means higher light level.
Blazin' Brew | Fire Resistance | Increases user's protection from fire.
Saiyan Serum | Combat Buff | This expensive brew provides Strength, Speed, Resistance, Regeneration, and Jump boosts, making the user a veritable combat machine.  However, the user is hit with Mining Fatigue, Slowness, Blindness, and Hunger debuffs as soon as it wears off, so the imbiber had better make sure the fight will be over by then.
Ghastly Draught | Fireballs | Allows the user to shoot fireballs.
Strong Swimming | Swim Boost | Basically a backport of the 1.8 enchantment.

##Alchemy
We'll see later!
