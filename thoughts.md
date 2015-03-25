#Better Brewing and Potions mod *(and possibly alchemy!)* mate

##New Object: `Potion`/`Brew`/`Concoction`/`Something`:
- [x] `method` called to do the magic -> entity, level, and others as needed
- [x] `method` called when the effect starts
- [x] `method` called when the effect stops
- [x] `field` with the maximum level of the potion

##The EffectHandler/SomeOtherName:
- [x] `class` implementing `IExtendedProperties`
- [x] Saves a **list**/map of active effects, very much like MC does it --> using `IExtendedProperties`
- [x] Has a method with `@SubscribeEvent`, with the `TickEvent` to call the effect
- [x] `method` to add the effect to an entity with a level and a duration (which is a fancy way to say that we're adding something to the entity's NBT and that the `TickHandler` is reacting accordingly

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

**Done** | **Concoction Name** | **Ability** | **Description** | **Calculation**
------------ | ------------ | ------------- | ------------- | -------------
**Yes** | Fleet Feet | Speed | Increases user's movement speed. | speed + 0.20000000298023224D * level
**Yes** | Brew of Brawn | Strength | Increases user's attack damage. | damage + 1.0D * level
**Yes** | Ghastly Draught | Fireballs | Allows the user to shoot fireballs. | Level 1: Blaze Fireball - Level 2: Ghast fireballs
No | Luman Liquor | Human Torch | Creates light around the player. Higher potency means higher light level.
**Yes** | Blazin' Brew | Fire Resistance | Increases user's protection from fire. | Level 1: Fire - Level 2: Fire + 1/2 damage from lava - Level 3: Lava
**Yes** | Saiyan Serum | Combat Buff | This expensive brew provides Strength, Speed, Resistance, Regeneration, and Jump boosts, making the user a veritable combat machine.  However, the user is hit with Mining Fatigue, Slowness, Blindness, and Hunger debuffs as soon as it wears off, so the imbiber had better make sure the fight will be over by then.
**Yes** | Strong Swimming | Swim Boost | Basically a backport of the 1.8 enchantment. | +0.025F * level while in water (it gets quite fast)
**Yes** | Hell Eyes | Combat | Hurts any mob/entity you're looking at. | 1.0F * level
No | Ghostwalk Ale | Ethereal Shift | Allows the imbiber to pass through enemies and entities without taking damage. Also makes the user immune to suffocation damage for a short period of time. However when applied, a greyscale filter is given to the player and they cannot interact with most blocks or give damage.
**Yes** | Vitality | Regeneration | Grants the user health regeneration. | 1/2 heart * level per 1.5s
**Yes** | Pick-Me-Up Potion | Instant Health | Instantly heals the user. | 1 heart * level
**Yes** | Put-Me-Down Potion | Instant Damage | Instantly damages the user. | 1 heart * level
**Yes** | Concoction (p)| Poison | Poisons the user. As a reference to Runescape, the name has (p) on it. Add a `+` for each level of potency. | 1.0F * level per second
**Yes** | Venomous Vigor | Regeneration | Grants the user very strong Regeneration, but slightly poisons the user afterwards. Meant to get you out of a sticky situation, but not as a "go-to" potion. | Half a heart * level per second, poison effect of the same level and half the duration when it wears off
**Yes** | Binky Boing | Jump Boost | Gives the user a jump boost, more or less powerful depending on the concoction's level. | Jump + 0.1F * level
**Yes** | Graceful Descent | Feather Fall | Removes fall damage, depending on the concoction's level | Distance -1.0F * level
**Yes** | Thick Hide | Resistance | Lowers any type of damage but fire and fall damage, depending on the concoction's level | -0.5F * level
**Yes** | Dead Hands | Mining Slowness | Slows down mining | -0.1F * level
**Yes** | Jumpy Hands | Mining Speedup | Speeds up mining | +0.1F * level

#Ingredients
**Harry Potter inspired**

**Done** | **Ingredient** | **Effect** | **Origin** | **Properties**
------------ | ------------ | ------------ | ------------ | ------------
No | Aconite / Wolfsbane | In magical lore, aconite combined with belladonna was applied as a magical ointment by witches to make themselves fly. Aconite is an extremely poisonous plant (hence its other name of wolfsbane). | Plant | Flight/Poison
No | Asphodel | In Greek mythology, the asphodel plant was thought to be the favourite food of the dead. Greeks often planted asphodel around graveyards. | Plant | 
No | Belladona | In magical lore, aconite combined with belladonna was applied as a magical ointment by witches to make themselves fly. | Plant | Flight
No | Bubotuber | A bubotuber looks like a thick, black, giant slug (it even squirms slightly, although it sticks vertically out of the soil) with many large shiny swellings on it that are filled with a yellow-green pus that smells like petrol. Undiluted, it will raise horribly painful boils on contact. | Plant | Damage
No | Daisy | An old wives’ tale says that daisy roots, boiled in milk and fed to a farm animal, can stop the animal from growing too large. | Plant | Youth
No | Fluxweed | An ingredient in Morphing potions, for which it must be picked at the full moon | Plant | Morphing
No | Gillyweed | Native to the Mediterranean, this water plant looks like a bundle of slimy, greyish-green rat tails. When eaten, gives a person gills to breathe underwater and gives them webbed hands and feet for swimming. | Plant | Water Breathing/Faster Swimming
No | Ginger | The soulless plant. | Plant | Makes Redheads
No | Hellebore | There are several kinds of hellebore. The name comes from the Greek words 'elein' (to injure) and 'bora' (food), indicating that hellebore is poisonous. In some belief systems, it's been believed to be a purgative, sometimes of bad things generally, used for things like protecting livestock from evil spells, and (in powdered form) for invisibility. | Plant | Poison/Damage/Invisibility
No | Knotgrass | Knotgrass is an ingredient in Morphing Potions. | Plant | Morphing
No | Lovage | A potion ingredient used to make Confusing and Befuddlement Draughts | Plant | Confusion
No | Mandrake / Mandragora | The Mandrake root is a powerful restorative. It forms an essential part of most antidotes, including one for Petrification. The Mandrake Restorative Draught returns people who have been Transfigured or cursed to their original state. The cry of the Mandrake is fatal to humans, so special care must be taken when growing them. Even as a baby, the Mandrake's howls can knock a person out for a couple of hours. | Plant | Regeneration/Healing
No | Nettles | This herb is used as a potion ingredient, used dried for making cure potions. | Plant | Regeneration/Healing
No | Peppermint | It is a plant used in potions to control unwelcome side effects. | Plant | Clear Debuffs
No | Phytobezoar | It is a stone that can cure most poisons. Commonly found in the stomach of plant eating animals. | Animal Drop | Antipoison
No | Pomegranate | The juice may be used in Strengthening Solution. | Plant | Strength
No | Scurvy-grass | A potion ingredient used to make Confusing and Befuddlement Draughts. | Plant | Confusion
No | Sneezewort | A potion ingredient used to make Confusing and Befuddlement Draughts. | Plant | Confusion
No | Sophorous Bean | An ingredient used in sleeping potions. | Plant | Sleeping
No | Valerian | In herbal lore, valerian is used as a sedative and a sleep aid. | Plant | Sleeping
No | Wormwood | A potion ingredient, used in the creation of sleeping potions. | Plant | Sleeping
No | Arachnid Venom | Almost impossible to collect from a living Arachnid. A special tool is needed to gather this ingredient | Animal Drop | Poison
No | Human Parts | Some Dark Magic requires human flesh, head or bones. | Player drop | 
No | Fish Parts | Spines of different fish is a very common ingredient. | Mob product | 
No | Spider Parts | These are a commonly used ingredient. | Mob drop | 
No | Zombie Parts | They have the same uses as Human Parts, but they are not as potent. | Mob drop | 

##Alchemy
We'll see later!
