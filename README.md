# Combat Enchantments

## Enchantments
\* - incomplete
### Sword
Dueling(I) - knockaway enemies in a circle around the target enemy
  - Knock away enemies within 6 blocks of the target 
  - Very rare
  - Mutually Exclusive with Sweeping Edge, Inferno

Lethality(I, II, III) - Deal bonus damage to enemies with more armor than you
  - Bonus damage = ((Enemy armor - your armor) / 2) * (level * 0.5)
  - Uncommon
  
Triumph(I, II, III) - regain hp and hunger for kills
  - regain (level) hp and restore (level * 3) hunger for killing a player
  - regain (level / 2) hp and 1 hunger for killing a mob
  - Very Rare
  - Mutually exclusive with Rampage
  
Rampage(I, II, III) - gain strength and speed, and apply slowness on hit for kills
  - gain strength 2 and speed 2 for (4 + level * 2) seconds, apply weakness 1 on hit for (4 + level * 2) seconds for player kills
  - gain strength 1 and speed 1 for (level + 1) seconds, apply weakness 1  on hit for (level + 1) seconds for killing a mob
  - weakness on the target lasts for (level) seconds 
  - Very Rare
  - Mutually exclusive with Triumph
 
Inferno(I, II) - light enemies in a circle around the target on fire
  - Light enemies within ( (level + 1) * 2) blocks of the target for (level + 1) seconds
  - Uncommon
  - Mutually exclusive with Fire Aspect, Dueling

Lifesteal(I, II, III, IV, V) - Damage enemies in a circle around the target, and heal based on the number of enemies hit
  - Circle has radius (2 + level)
  - Damage all enemies in circle for 1 hp, heal for (number of enemies hit + level) hp
  - (15 - level) second cooldown(cooldown only decreases while holding the sword with lifesteal)
  - Uncommon
  - Mutually exclusive with sharpness, bane of arthopods, smite

### Bow
*Bow and crossbow enchantments will not work within roughly sword range*

Hunter(I) - mark enemies you shoot, shoot marked enemies for speed and jump boost
  - mark lasts for 3 seconds, also applies glow effect
  - Gain speed 2 + jump 2 for 10 seconds on hitting a marked enemy
  - Rare
  - Mutually exclusive with Zap, Volley

Zap(I) - send out bolts of lightning from the target that bounce to nearby enemies
  - targets enemies within 4 block circle
  - continuily bounces until no new targets are found(enemies can only be hit once)
  - Deals 2 hp magic damage, except to the original target
  - Very rare
  - mutually exclusive with Hunter, Volley

Volley(I) - arrows fall from the sky around the target
  - arrows with roughly the same speed as a fully charged bow fall in a small area around the target
  - randomized pattern
  - Rare
  - mutually exclusive with Zap, Hunter

### Crossbow

Frost(I, II) - slow and damage enemies
  - slow and damage for (level) hp
  - Rare
  - Mutually exclusive with Tranquilizer

Tranquilizer(I, II) - after a delay, the target goes to sleep
  - Target will gain the sleepy status effect for 10 seconds
  - Target will sleep during the last (level) seconds of sleepy
  - Sleeping targets cannot be damaged directly(swords, arrows)
  - Rare
  - mutually exclusive with Frost

### Armor(all)
Sorcery(I, II, III, IV) - randomly gain status effects upon taking damage
  - Can gain resistance, regeneration, fire resistance, absorption, health boost, jump boost, or speed
  - 1/4 chance(for each piece individually) to gain one of the above status effects randomly
  - status effect last for (level * 2) seconds
  - status effect upgrades if randomly selected again, caps at a strength of 2
  - Uncommon
  - mutually exclusive with protections
  
## License

This template is available under the CC0 license. Feel free to learn from it and incorporate it in your own projects.
